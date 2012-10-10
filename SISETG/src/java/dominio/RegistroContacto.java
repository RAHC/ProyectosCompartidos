package dominio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

@ManagedBean
@ViewScoped
public class RegistroContacto {

    private String Nombres;
    private String Apellidos;
    private String CodDepartamento;
    private String CodMunicipio;
    private String CodCanton;
    private String Ubicacion;
    private Integer TpInstitucion;
    private Integer Institucion;
    private String TelInst;
    private String Cel;
    private String TelPers;
    private String Fax;
    private String Radio;
    private String CorreoInst;
    private String CorreoPers;
    private String Cargo;
    @Resource(name = "jdbc/sise")
    DataSource dataSource;

    public RegistroContacto() {
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

    public List<Canton> getCantones() throws SQLException {
        List<Canton> resultados = new ArrayList<Canton>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            String query = "SELECT IDUBIC, NOMBUBIC, LATITUDUBIC, LONGITUDUBIC FROM UBICACION WHERE IDUBIC_PADRE = '" + CodMunicipio + "' order by NOMBUBIC";
            PreparedStatement getUbicacion = connection.prepareStatement(query);
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getUbicacion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new Canton(rowSet.getString("IDUBIC"),
                        rowSet.getString("NOMBUBIC"),
                        rowSet.getFloat("LATITUDUBIC"),
                        rowSet.getFloat("LONGITUDUBIC")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }
    public List<Institucion> getInstituciones() throws SQLException {
        List<Institucion> resultados = new ArrayList<Institucion>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        String filtro="";
        if(getCodDepartamento()!=null){
            filtro = " AND IDUBIC LIKE '"+CodDepartamento+"%'";
        }
        try {
            PreparedStatement getInstitucion = connection.prepareStatement(
                    "SELECT IDINST, NOMBINST FROM INSTITUCION WHERE IDTPINST= " + TpInstitucion+""+filtro);
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getInstitucion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new Institucion(rowSet.getInt("IDINST"),
                        rowSet.getString("NOMBINST")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public String guardar() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        
        String Ubic;
        if(getCodDepartamento()!=null){
            this.Ubicacion = CodDepartamento;
        }
        if(getCodMunicipio()!=null){
            this.Ubicacion = CodMunicipio;
        }
        if (getUbicacion() == null) {
            Ubic = "''";
        } else {
            Ubic = getUbicacion();
        }
        
        int inst;
        if (getInstitucion() == null) {
            inst = 0;
        } else {
            inst = getInstitucion();
        }
        CallableStatement cs;
        Integer accion;
        try {
            String sql = "{ call CONTACTO_Add(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            cs = connection.prepareCall(sql);
            cs.setInt(1, inst);
            cs.setString(2, Ubic);
            cs.setString(3, getNombres());
            cs.setString(4, getApellidos());
            cs.setString(5, getTelPers());
            cs.setString(6, getCel());
            cs.setString(7, getCorreoInst());
            cs.setString(8, getCargo());
            cs.setString(9, getTelInst());
            cs.setString(10, getFax());
            cs.setString(11, getCorreoPers());
            cs.setString(12, getRadio());
            cs.registerOutParameter(13, java.sql.Types.INTEGER);
            cs.execute();
            accion = cs.getInt(13);
            
            if (accion == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error el la ejecución, el nuevo contacto no pudo ser registrado", null));
            } else if (accion > 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contacto registrado satisfactotiamente", null));
            }
        } catch (SQLException ex) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, ex.toString(), null));
            System.out.println(ex);
        } finally {
            connection.close();
        }
        return "libretaContactos.xhtml";
    }

    public String getCancelar() {
        return "listadoIncidentes.xhtml";
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public String getCel() {
        return Cel;
    }

    public void setCel(String Cel) {
        this.Cel = Cel;
    }

    public String getCorreoInst() {
        return CorreoInst;
    }

    public void setCorreoInst(String CorreoInst) {
        this.CorreoInst = CorreoInst;
    }

    public String getCorreoPers() {
        return CorreoPers;
    }

    public void setCorreoPers(String CorreoPers) {
        this.CorreoPers = CorreoPers;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String Fax) {
        this.Fax = Fax;
    }

    public String getRadio() {
        return Radio;
    }

    public void setRadio(String Radio) {
        this.Radio = Radio;
    }

    public Integer getInstitucion() {
        return Institucion;
    }

    public void setInstitucion(Integer Institucion) {
        this.Institucion = Institucion;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getTelInst() {
        return TelInst;
    }

    public void setTelInst(String TelInst) {
        this.TelInst = TelInst;
    }

    public String getTelPers() {
        return TelPers;
    }

    public void setTelPers(String TelPers) {
        this.TelPers = TelPers;
    }

    public String getCodCanton() {
        return CodCanton;
    }

    public void setCodCanton(String CodCanton) {
        this.CodCanton = CodCanton;
    }

    public String getCodDepartamento() {
        return CodDepartamento;
    }

    public void setCodDepartamento(String CodDepartamento) {
        this.CodDepartamento = CodDepartamento;
    }

    public String getCodMunicipio() {
        return CodMunicipio;
    }

    public void setCodMunicipio(String CodMunicipio) {
        this.CodMunicipio = CodMunicipio;
    }

    public Integer getTpInstitucion() {
        return TpInstitucion;
    }

    public void setTpInstitucion(Integer TpInstitucion) {
        this.TpInstitucion = TpInstitucion;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }
    
}
