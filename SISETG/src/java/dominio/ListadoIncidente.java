/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;


@ManagedBean
@ViewScoped
public class ListadoIncidente implements Serializable {

    private Date FechaIni;
    private Date FechaFin;
    private String TpIncidente;
    private Integer Prioridad;
    private Integer Estado;
    private String Departamento;
    private String Municipio;
    
    @Resource(name = "jdbc/sise")
    DataSource dataSource;
    public ListadoIncidente() {
    }

    public Date getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(Date FechaFin) {
        this.FechaFin = FechaFin;
    }

    public Date getFechaIni() {
        return FechaIni;
    }

    public void setFechaIni(Date FechaIni) {
        this.FechaIni = FechaIni;
    }

    public String getTpIncidente() {
        return TpIncidente;
    }

    public void setTpIncidente(String TpIncidente) {
        this.TpIncidente = TpIncidente;
    }

    public Integer getPrioridad() {
        return Prioridad;
    }

    public void setPrioridad(Integer Prioridad) {
        this.Prioridad = Prioridad;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String Departamento) {
        this.Departamento = Departamento;
    }

    public Integer getEstado() {
        return Estado;
    }

    public void setEstado(Integer Estado) {
        this.Estado = Estado;
    }

    public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String Municipio) {
        this.Municipio = Municipio;
    }
   
    public List<Municipio> getMunicipios() throws SQLException {
        List<Municipio> resultados = new ArrayList<Municipio>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            String query = "SELECT IDUBIC, NOMBUBIC, LATITUDUBIC, LONGITUDUBIC FROM UBICACION WHERE IDUBIC_PADRE = '" + Departamento + "' order by NOMBUBIC";
            PreparedStatement getUbicacion = connection.prepareStatement(query);
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getUbicacion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new Municipio(rowSet.getString("IDUBIC"),
                        rowSet.getString("NOMBUBIC"),
                        rowSet.getFloat("LATIDUDUBIC"),
                        rowSet.getFloat("LONGITUDUBIC")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }
    public List<DatosIncidente> getLoadIncidentes() throws SQLException{
        List<DatosIncidente> resultados = new ArrayList<DatosIncidente>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        String filtro = " WHERE 1=1 ";
        if(TpIncidente != null){
            filtro += " AND I.IDTPINC="+TpIncidente;
        }
        if(Prioridad != null){
           filtro += " AND I.IDPRIOR="+Prioridad; 
        }
        if(Estado != null){
            filtro += " AND I.IDESTADO="+Estado;
        }
        if(Departamento != null && Municipio != null){
            filtro += " AND I.IDUBIC IN ("+Departamento+" ,"+Municipio+")"; 
        }
        else if(Departamento != null){
            filtro += " AND I.IDUBIC="+Departamento;
        }
        else if(Municipio != null){
            filtro += " AND I.IDUBIC="+Municipio;
        }
        if(FechaIni != null){
            if(FechaFin != null){
                filtro += " AND FECHORAINIINC BETWEEN DATE "+ utilToSql(getFechaIni())+" 00:00:00 AND "+utilToSql(getFechaFin())+" 23:59:59";
            }
        }
        try{
            PreparedStatement getIncidentes = connection.prepareStatement(
                    "SELECT I.IDEV, I.CORRINC, I.IDPRIOR, I.IDESTADO, I.IDTPINC, LL.IDINFOR, "
                    + "I.IDUBIC, FECHORAINIINC, LATITUDINC, LONGITUDINC, ALTIMETRIAINC, "
                    + " DESCINC, DIRINC, PTOREFINC, P.NOMBPRIOR, U.NOMBUBIC, "
                    + " E.NOMBESTADO, TI.NOMBTPINC,  LL.NOMBINFOR, LL.APELLINFOR, LL.TELINFOR FROM INCIDENTE I "
                    + " INNER JOIN ESTADO E ON I.IDESTADO=E.IDESTADO INNER JOIN TIPOINCIDENTE TI "
                    + " ON I.IDTPINC=TI.IDTPINC INNER JOIN LLAMADA LL ON I.IDINFOR = LL.IDINFOR "
                    + " INNER JOIN UBICACION U ON I.IDUBIC=U.IDUBIC INNER JOIN PRIORIDADINCIDENTE "
                    + "P ON P.IDPRIOR = I.IDPRIOR "+filtro);
            
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getIncidentes.executeQuery());
            
            while(rowSet.next()){
                resultados.add(new DatosIncidente(rowSet.getString("IDEV"),
                        rowSet.getString("CORRINC"),
                        rowSet.getInt("IDPRIOR"),
                        rowSet.getInt("IDESTADO"),
                        rowSet.getString("IDTPINC"),
                        rowSet.getInt("IDINFOR"),
                        rowSet.getString("IDUBIC"),
                        rowSet.getString("FECHORAINIINC"),
                        rowSet.getFloat("LATITUDINC"),
                        rowSet.getFloat("LONGITUDINC"),
                        rowSet.getFloat("ALTIMETRIAINC"),
                        rowSet.getString("DESCINC"),
                        rowSet.getString("DIRINC"),
                        rowSet.getString("PTOREFINC"),
                        rowSet.getString("NOMBPRIOR"),
                        rowSet.getString("NOMBUBIC"),
                        rowSet.getString("NOMBESTADO"),
                        rowSet.getString("NOMBTPINC"),
                        rowSet.getString("NOMBINFOR"),
                        rowSet.getString("APELLINFOR"),
                        rowSet.getString("TELINFOR")));
            }
            return resultados;
        }finally {
            connection.close();
        }
        
    }
    private java.sql.Date utilToSql(java.util.Date fecha) {
        DateFormat sqlDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
         return java.sql.Date.valueOf(sqlDateFormatter.format(fecha));
    }
   
    
}
