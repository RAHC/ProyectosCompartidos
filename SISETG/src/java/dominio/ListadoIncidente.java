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
    private String CodDepartamento;
    private String CodMunicipio;
    
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

    public String getCodDepartamento() {
        return CodDepartamento;
    }

    public void setCodDepartamento(String CodDepartamento) {
        this.CodDepartamento = CodDepartamento;
    }

    public Integer getEstado() {
        return Estado;
    }

    public void setEstado(Integer Estado) {
        this.Estado = Estado;
    }

    public String getCodMunicipio() {
        return CodMunicipio;
    }

    public void setCodMunicipio(String CodMunicipio) {
        this.CodMunicipio = CodMunicipio;
    }
        public List<Departamento> getDepartamentos() throws SQLException {
        List<Departamento> resultados = new ArrayList<Departamento>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            PreparedStatement getDepartamento = connection.prepareStatement(
                    "SELECT IDUBIC, NOMBUBIC, LATITUDUBIC, LONGITUDUBIC FROM UBICACION WHERE IDUBIC_PADRE is NULL order by NOMBUBIC");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getDepartamento.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Departamento(rowSet.getString("IDUBIC"),
                        rowSet.getString("NOMBUBIC"),
                        rowSet.getFloat("LATITUDUBIC"),
                        rowSet.getFloat("LONGITUDUBIC")));
            }
            return resultados;
        } finally {
            connection.close();
        }
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
            String query = "SELECT IDUBIC, NOMBUBIC, LATITUDUBIC, LONGITUDUBIC FROM UBICACION WHERE IDUBIC_PADRE = '" + CodDepartamento + "' order by NOMBUBIC";
            PreparedStatement getUbicacion = connection.prepareStatement(query);
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getUbicacion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new Municipio(rowSet.getString("IDUBIC"),
                        rowSet.getString("NOMBUBIC"),
                        rowSet.getFloat("LATITUDUBIC"),
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
        if(CodDepartamento != null && CodMunicipio != null){
            filtro += " AND I.IDUBIC LIKE '"+CodMunicipio+"%'"; 
        }
        else if(CodDepartamento != null){
            filtro += " AND I.IDUBIC LIKE '"+CodDepartamento+"%'";
        }
        if(FechaIni != null){
            if(FechaFin != null){
                filtro += " AND FECHORAINIINC BETWEEN DATE "+ utilToSql(getFechaIni())+" 00:00:00 AND "+utilToSql(getFechaFin())+" 23:59:59";
            }
        }
        try{
            PreparedStatement getIncidentes = connection.prepareStatement(
                    "SELECT I.IDEV, I.CORRINC, I.IDPRIOR, I.IDESTADO, I.IDTPINC, LL.IDINFOR, "
                    + "I.IDUBIC, convert(varchar, FECHORAINIINC,101) as f1, convert(varchar, FECHORAINIINC,108) as h1, LATITUDINC, LONGITUDINC, ALTIMETRIAINC, "
                    + " DESCINC, DIRINC, PTOREFINC, P.NOMBPRIOR, U.NOMBUBIC, "
                    + " E.NOMBESTADO, TI.NOMBTPINC,  LL.NOMBINFOR, LL.APELLINFOR, LL.TELINFOR FROM INCIDENTE I "
                    + " INNER JOIN ESTADO E ON I.IDESTADO=E.IDESTADO INNER JOIN TIPOINCIDENTE TI "
                    + " ON I.IDTPINC=TI.IDTPINC INNER JOIN LLAMADA LL ON I.IDINFOR = LL.IDINFOR "
                    + " INNER JOIN UBICACION U ON I.IDUBIC=U.IDUBIC INNER JOIN PRIORIDADINCIDENTE "
                    + "P ON P.IDPRIOR = I.IDPRIOR "+filtro);
            
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getIncidentes.executeQuery());
            
            while(rowSet.next()){
                PreparedStatement getFechaNotificacion = connection.prepareStatement(
                        "SELECT convert(varchar, FECHORAALMACC,101) as f1,convert(varchar, FECHORAALMACC,108) as h1 "
                        + "FROM ACCIONES WHERE IDEV='"+rowSet.getString("IDEV")+"' AND CORRINC='"+rowSet.getString("CORRINC")+"' "
                        + "AND IDACC = (SELECT MIN(IDACC) FROM ACCIONES A where A.IDEV='"+rowSet.getString("IDEV")+"' AND"
                        + " A.CORRINC='"+rowSet.getString("CORRINC")+"')");
                CachedRowSet rowSet2 = new com.sun.rowset.CachedRowSetImpl();
                rowSet2.populate(getFechaNotificacion.executeQuery());
                rowSet2.next();
                
                PreparedStatement getListAcciones = connection.prepareStatement(
                        "SELECT * FROM ACCIONES WHERE IDEV='"+rowSet.getString("IDEV")+"' AND CORRINC='"+rowSet.getString("CORRINC")+"'");
                CachedRowSet rowSet3 = new com.sun.rowset.CachedRowSetImpl();
                rowSet3.populate(getListAcciones.executeQuery());
                //rowSet3.next();
                
                resultados.add(new DatosIncidente(rowSet.getString("IDEV"),
                        rowSet.getString("CORRINC"),
                        rowSet.getInt("IDPRIOR"),
                        rowSet.getInt("IDESTADO"),
                        rowSet.getString("IDTPINC"),
                        rowSet.getInt("IDINFOR"),
                        rowSet.getString("IDUBIC"),
                        rowSet.getString("f1"),
                        rowSet.getString("h1"),
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
                        rowSet.getString("TELINFOR"),
                        rowSet2.getString("f1"),
                        rowSet2.getString("h1")));
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
