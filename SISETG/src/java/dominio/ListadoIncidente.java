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
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;


@ManagedBean
@ViewScoped
public class ListadoIncidente implements Serializable {

    private Date FechaInicial;
    private Date FechaFinal;
    private String TpIncidente;
    private Integer Prioridad;
    private Integer Estado;
    private String CodDepartamento;
    private String CodMunicipio;
    private DatosIncidente selectedMapa;
    private String NombreEvento;
    
    @Resource(name = "jdbc/sise")
    DataSource dataSource;
    
    FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        LoginBean nB = (LoginBean) session.getAttribute("loginBean");

     public List<Departamento> getDepartamentos() throws SQLException {
        List<Departamento> resultados = new ArrayList<Departamento>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        String Filtro="";
        if(!"''".equals(nB.getIdUbic())){
            String codDep;
            codDep = nB.getIdUbic().substring(0, 2); 
            Filtro += " AND IDUBIC LIKE '"+codDep+"%'";
            this.CodDepartamento = codDep; 
        }
        try {
            PreparedStatement getDepartamento = connection.prepareStatement(
                    "SELECT IDUBIC, NOMBUBIC, LATITUDUBIC, LONGITUDUBIC FROM UBICACION WHERE IDUBIC_PADRE is NULL "+Filtro+" order by NOMBUBIC");
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
        String Filtro = "";
        if(nB.getIdUbic().length()>=4){
            String codMuni;
            codMuni  = nB.getIdUbic().substring(0,4);
            Filtro += " AND IDUBIC LIKE '"+codMuni+"%'";
            this.CodMunicipio = codMuni;
        }
        try {
            String query = "SELECT IDUBIC, NOMBUBIC, LATITUDUBIC, LONGITUDUBIC FROM UBICACION WHERE IDUBIC_PADRE = '" + CodDepartamento + "' "+Filtro+" order by NOMBUBIC";
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
    public List<Estado> getEstados() throws SQLException{
        List<Estado> resultados = new ArrayList<Estado>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        String Filtro = " WHERE 1 = 1 AND IDESTADO!=3 AND IDESTADO!=7 ";
        if(nB.getIdRol()==1){
            Filtro += " AND IDESTADO IN (1,2)";
        }
        else if(nB.getIdRol()==2){
            Filtro += " AND IDESTADO = 1";
        }
        else if(nB.getIdRol()==3){
            Filtro += " AND IDESTADO IN (2,4,5,8)";
        }
        else if(nB.getIdRol()==4){
            Filtro += " AND IDESTADO IN (6)";
        }

        try {
            PreparedStatement getEstado = connection.prepareStatement(
                    "SELECT IDESTADO, NOMBESTADO,COLORESTADO FROM ESTADO "+ Filtro +" ORDER BY IDESTADO");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getEstado.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Estado(rowSet.getInt("IDESTADO"),
                        rowSet.getString("NOMBESTADO"),
                        rowSet.getString("COLORESTADO")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }
    
    public List<DatosIncidente> getLoadIncidentes() throws SQLException{
        Marker marker;
        MapModel simpleModel = new DefaultMapModel();
        List<DatosIncidente> resultados = new ArrayList<DatosIncidente>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        String filtro = " WHERE 1=1 AND I.IDEV='"+nB.getIdEvento()+"'";
        if(TpIncidente != null){
            filtro += " AND I.IDTPINC="+TpIncidente;
        }
        if(Prioridad != null){
           filtro += " AND I.IDPRIOR="+Prioridad; 
        }
        if(Estado != null){
            filtro += " AND I.IDESTADO="+Estado;
        }
        else{
            filtro +=" AND I.IDESTADO!=3 AND I.IDESTADO!=7";
            if(nB.getIdRol()==1){
                filtro += " AND I.IDESTADO IN (1,2)";
             }
            else if(nB.getIdRol()==2){
                filtro += " AND I.IDESTADO = 1";
            }
            else if(nB.getIdRol()==3){
                filtro += " AND I.IDESTADO IN (2,4,5,8)";
            }
            else if(nB.getIdRol()==4){
                filtro += " AND I.IDESTADO IN (6)";
            }
        }
        if(CodDepartamento != null && CodMunicipio != null){
            filtro += " AND I.IDUBIC LIKE '"+CodMunicipio+"%'"; 
        }
        else if(CodDepartamento != null){
            if(nB.getIdUbic().length()>=4){
                String codUbic;
                codUbic = nB.getIdUbic().substring(0,4);
                filtro += " AND IDUBIC LIKE '"+codUbic+"%'";
            }
            else{
                filtro += " AND I.IDUBIC LIKE '"+CodDepartamento+"%'";
            }
        }
        else{
            String codUbic;
            if(nB.getIdUbic().length()>=4){
                codUbic = nB.getIdUbic().substring(0,4);
                filtro += " AND IDUBIC LIKE '"+codUbic+"%'";
            }
            else if(!"00".equals(nB.getIdUbic())){
                codUbic = nB.getIdUbic().substring(0,2);
                filtro += " AND IDUBIC LIKE '"+codUbic+"%'";
            }
        }
        if(FechaInicial !=null){
            if(FechaFinal !=null){
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                String fecIni = dateFormat.format(getFechaInicial());
                String fecFin = dateFormat.format(getFechaFinal());
                filtro += " AND FECHORAINIINC BETWEEN DATE '"+ fecIni+":00' AND '"+fecFin+":00'";
            }
        }
        try{
            PreparedStatement getIncidentes = connection.prepareStatement(
                    "SELECT I.IDEV, I.CORRINC, P.IDPRIOR, I.IDESTADO, I.IDTPINC, LL.IDINFOR, "
                    + "I.IDUBIC, convert(varchar, FECHORAINIINC,103) as f1, convert(varchar, FECHORAINIINC,108) as h1, LATITUDINC, LONGITUDINC, ALTIMETRIAINC, "
                    + " DESCINC, DIRINC, PTOREFINC, P.NOMBPRIOR, U.NOMBUBIC, U.LATITUDUBIC, U.LONGITUDUBIC, "
                    + " E.NOMBESTADO, TI.NOMBTPINC,  LL.NOMBINFOR, LL.APELLINFOR, LL.TELINFOR FROM INCIDENTE I "
                    + " INNER JOIN ESTADO E ON I.IDESTADO=E.IDESTADO INNER JOIN TIPOINCIDENTE TI "
                    + " ON I.IDTPINC=TI.IDTPINC INNER JOIN LLAMADA LL ON I.IDINFOR = LL.IDINFOR "
                    + " INNER JOIN UBICACION U ON I.IDUBIC=U.IDUBIC INNER JOIN PRIORIDADINCIDENTE "
                    + "P ON P.IDPRIOR = I.IDPRIOR "+filtro);
            
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getIncidentes.executeQuery());
            
            while(rowSet.next()){
                PreparedStatement getFechaNotificacion = connection.prepareStatement(
                        "SELECT convert(varchar, FECHORAALMACC,103) as f1,convert(varchar, FECHORAALMACC,108) as h1 "
                        + "FROM ACCIONES WHERE IDEV='"+rowSet.getString("IDEV")+"' AND CORRINC='"+rowSet.getString("CORRINC")+"' "
                        + "AND IDACC = (SELECT MIN(IDACC) FROM ACCIONES A where A.IDEV='"+rowSet.getString("IDEV")+"' AND"
                        + " A.CORRINC='"+rowSet.getString("CORRINC")+"')");
                CachedRowSet rowSet2 = new com.sun.rowset.CachedRowSetImpl();
                rowSet2.populate(getFechaNotificacion.executeQuery());
                rowSet2.next();
                
                List<Acciones> resultados2 = new ArrayList<Acciones>();
                PreparedStatement getListAcciones = connection.prepareStatement(
                        "SELECT *, convert(varchar, FECHORAREALACC,103) as FR, convert(varchar, FECHORAALMACC,103) as FA, "
                        + "convert(varchar, DURACACC,108) as DR "
                        + "FROM ACCIONES WHERE IDEV='"+rowSet.getString("IDEV")+"' AND CORRINC='"+rowSet.getString("CORRINC")+"' AND ESTADOACC='H' ORDER BY IDACC");
                CachedRowSet rowSet3 = new com.sun.rowset.CachedRowSetImpl();
                rowSet3.populate(getListAcciones.executeQuery());
                while(rowSet3.next()){
                    resultados2.add( new Acciones(rowSet3.getInt("IDACC"),
                            rowSet3.getString("IDEV"),
                            rowSet3.getString("CORRINC"),
                            rowSet3.getInt("IDESTADO"),
                            rowSet3.getInt("IDCONT"),
                            rowSet3.getString("DESCACC"),
                            rowSet3.getString("RESPCOORDACC"),
                            rowSet3.getString("FR"),
                            rowSet3.getString("DR"),
                            rowSet3.getString("FA"),
                            rowSet3.getString("ESTADOACC")));  
                }
                marker = new Marker(new LatLng(rowSet.getFloat("LATITUDINC"), rowSet.getFloat("LONGITUDINC")),rowSet.getString("DIRINC") );
                simpleModel.addOverlay(marker);
                
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
                        rowSet.getFloat("LATITUDUBIC"),
                        rowSet.getFloat("LONGITUDUBIC"),
                        rowSet.getString("NOMBESTADO"),
                        rowSet.getString("NOMBTPINC"),
                        rowSet.getString("NOMBINFOR"),
                        rowSet.getString("APELLINFOR"),
                        rowSet.getString("TELINFOR"),
                        rowSet2.getString("f1"),
                        rowSet2.getString("h1"),
                        resultados2,
                        simpleModel));
            }           
            return resultados;
        }finally {
            connection.close();
        }
        
    }

    public String getNombreEvento() throws SQLException{
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try{
            PreparedStatement getNombEvento = connection.prepareStatement("SELECT NOMBEV FROM EVENTO "
                    + "WHERE IDEV='"+nB.getIdEvento()+"'");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getNombEvento.executeQuery());
            rowSet.next();
            NombreEvento = rowSet.getString("NOMBEV");
            return NombreEvento;
           }
        finally {
            connection.close();
        } 
    }

    public void setNombreEvento(String NombreEvento) {
        this.NombreEvento = NombreEvento;
    }
        
    public ListadoIncidente() {
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
    
    public DatosIncidente getSelectedMapa() {
        return selectedMapa;
    }

    public void setSelectedMapa(DatosIncidente selectedMapa) {
        this.selectedMapa = selectedMapa;
    }
    
    public void setCodMunicipio(String CodMunicipio) {
        this.CodMunicipio = CodMunicipio;
    }

    public Date getFechaFinal() {
        return FechaFinal;
    }

    public void setFechaFinal(Date FechaFinal) {
        this.FechaFinal = FechaFinal;
    }

    public Date getFechaInicial() {
        return FechaInicial;
    }

    public void setFechaInicial(Date FechaInicial) {
        this.FechaInicial = FechaInicial;
    }

}
