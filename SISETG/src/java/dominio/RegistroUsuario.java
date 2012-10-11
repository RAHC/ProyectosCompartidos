/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
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

/**
 *
 * @author Administrador
 */
@ManagedBean
@ViewScoped
public class RegistroUsuario implements Serializable {

    private String Alias;
    private String Password;
    private Integer Nivel;
    private String CodJurisdiccion;
    private String Ubicacion;
    private String CodDepartamento;
    private String CodMunicipio;
    private String CodCanton;
    private Integer TpInstitucion;
    private Integer Institucion;
    private Integer Contacto;
    private String Nombres;
    private String Apellidos;
    private String TelInst;
    private String Cel;
    private String TelPers;
    private String Fax;
    private String Radio;
    private String CorreoInst;
    private String CorreoPers;
    private String Cargo;
    private String Direccion;
    @Resource(name = "jdbc/sise")
    DataSource dataSource;
    FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();

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

    public RegistroUsuario() {
    }

    public Integer getContacto() {
        return Contacto;
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
        String filtro = "";
        if (CodMunicipio != null) {
            filtro = " AND (IDUBIC LIKE '" + CodMunicipio + "' or IDUBIC LIKE '" + CodDepartamento + "')";
        } else if ((CodDepartamento != null) && (!"''".equals(CodDepartamento))) {
            filtro += " AND IDUBIC LIKE '" + CodDepartamento + "'";
        }
        try {
            PreparedStatement getInstitucion = connection.prepareStatement(
                    "SELECT IDINST, NOMBINST FROM INSTITUCION WHERE IDTPINST= " + TpInstitucion + "" + filtro);
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

    public void setContacto(Integer Contacto) throws SQLException {
        this.Contacto = Contacto;
        if (Contacto > 0) {
            String sql;
            Integer count;
            sql = "SELECT IDCONT, IDINST, IDUBIC, NOMBCONT, APELLCONT, TELCONT, CELCONT,"
                    + " MAILINSTCONT, DIRCONT, CARGOCONT, TELINSTCONT, FAXCONT, MAILPERCONT, RADIOCONT"
                    + " FROM CONTACTOS WHERE IDCONT=" + Contacto;
            if (dataSource == null) {
                throw new SQLException("No se pudo tener acceso a la fuente de datos");
            }
            Connection connection = dataSource.getConnection();

            if (connection == null) {
                throw new SQLException("No se pudo conectar a la fuente de datos");
            }
            try {
                PreparedStatement datosContacto = connection.prepareStatement(sql);
                CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
                rowSet.populate(datosContacto.executeQuery());
                rowSet.next();

                count = rowSet.getString("IDUBIC").length();
                if (count >= 5) {
                    this.CodCanton = rowSet.getString("IDUBIC");
                    sql = "SELECT IDUBIC_PADRE FROM UBICACION WHERE IDUBIC=" + rowSet.getString("IDUBIC");
                    PreparedStatement datoMunicipio = connection.prepareStatement(sql);
                    CachedRowSet rowSet2 = new com.sun.rowset.CachedRowSetImpl();
                    rowSet2.populate(datoMunicipio.executeQuery());
                    rowSet2.next();
                    this.CodMunicipio = rowSet2.getString("IDUBIC_PADRE");

                    sql = "SELECT IDUBIC_PADRE FROM UBICACION WHERE IDUBIC=" + rowSet2.getString("IDUBIC_PADRE");
                    PreparedStatement datoDepartamento = connection.prepareStatement(sql);
                    CachedRowSet rowSet3 = new com.sun.rowset.CachedRowSetImpl();
                    rowSet3.populate(datoDepartamento.executeQuery());
                    rowSet3.next();
                    this.CodDepartamento = rowSet3.getString("IDUBIC_PADRE");
                } else if (count >= 3) {
                    this.CodMunicipio = rowSet.getString("IDUBIC");
                    sql = "SELECT IDUBIC_PADRE FROM UBICACION WHERE IDUBIC=" + rowSet.getString("IDUBIC");
                    PreparedStatement datoDepartamento = connection.prepareStatement(sql);
                    CachedRowSet rowSet2 = new com.sun.rowset.CachedRowSetImpl();
                    rowSet2.populate(datoDepartamento.executeQuery());
                    rowSet2.next();
                    this.CodDepartamento = rowSet2.getString("IDUBIC_PADRE");
                } else if (count >= 1) {
                    this.CodDepartamento = rowSet.getString("IDUBIC");
                }

                //this.CodMunicipio = rowSet.getString("IDUBIC");
                if (rowSet.getString("IDINST") != null) {
                    this.Institucion = rowSet.getInt("IDINST");

                    sql = "SELECT IDTPINST FROM INSTITUCION WHERE IDINST = " + rowSet.getInt("IDINST");
                    PreparedStatement datosInstitucion = connection.prepareStatement(sql);
                    CachedRowSet rowSet4 = new com.sun.rowset.CachedRowSetImpl();
                    rowSet4.populate(datosInstitucion.executeQuery());
                    rowSet4.next();
                    this.TpInstitucion = rowSet4.getInt("IDTPINST");

                } else {
                    this.Institucion = null;
                    this.TpInstitucion = null;
                }

                this.Nombres = rowSet.getString("NOMBCONT");
                this.Apellidos = rowSet.getString("APELLCONT");
                this.TelPers = rowSet.getString("TELCONT");
                this.Cel = rowSet.getString("CELCONT");
                this.CorreoInst = rowSet.getString("MAILINSTCONT");
                this.Direccion = rowSet.getString("DIRCONT");
                this.Cargo = rowSet.getString("CARGOCONT");
                this.TelInst = rowSet.getString("TELINSTCONT");
                this.Fax = rowSet.getString("FAXCONT");
                this.CorreoPers = rowSet.getString("MAILPERCONT");
                this.Radio = rowSet.getString("RADIOCONT");

            } finally {
                connection.close();
            }
        }
        else{
            this.CodDepartamento = null;
            this.CodMunicipio = null;
            this.CodCanton = null;
            this.Institucion = null;
            this.TpInstitucion = null;
            this.Nombres = null;
            this.Apellidos = null;
            this.TelPers = null;
            this.Cel = null;
            this.CorreoInst = null;
            this.Direccion = null;
            this.Cargo = null;
            this.TelInst = null;
            this.Fax = null;
            this.CorreoPers = null;
            this.Radio = null;
        }
    }

    public List<Contacto> getContactos() throws SQLException {
        List<Contacto> resultados = new ArrayList<Contacto>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            PreparedStatement getContacto = connection.prepareStatement(
                    "SELECT * FROM CONTACTOS WHERE IDCONT NOT IN(SELECT IDCONT FROM USUARIO)");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getContacto.executeQuery());
            while (rowSet.next()) {
                resultados.add(new Contacto(rowSet.getInt("IDCONT"),
                        rowSet.getString("NOMBCONT"),
                        rowSet.getString("APELLCONT"),
                        rowSet.getString("TELCONT"),
                        rowSet.getString("CELCONT"),
                        rowSet.getString("MAILINSTCONT"),
                        rowSet.getString("DIRCONT"),
                        rowSet.getString("CARGOCONT"),
                        rowSet.getString("TELINSTCONT"),
                        rowSet.getString("FAXCONT"),
                        rowSet.getString("MAILPERCONT"),
                        rowSet.getString("RADIOCONT")));
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
        if (getCodDepartamento() != null) {
            this.Ubicacion = CodDepartamento;
        }
        if (getCodMunicipio() != null) {
            this.Ubicacion = CodMunicipio;
        }
        if (getUbicacion() == null) {
            Ubic = "''";
        } else {
            Ubic = getUbicacion();
        }

        int contact;
        if (getContacto() == null) {
            contact = 0;
        } else {
            contact = getContacto();
        }
        CallableStatement cs;
        Integer accion;
        
        int inst;
        if (getInstitucion() == null) {
            inst = 0;
        } else {
            inst = getInstitucion();
        }

        try {
            String sql = "{ call USUARIO_Add(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

            cs = connection.prepareCall(sql);
            cs.setString(1, getAlias());
            cs.setString(2, getPassword());
            cs.setInt(3, getNivel());
            cs.setString(4, getCodJurisdiccion());
            cs.setString(5, Ubic);
            cs.setInt(6, contact);
            cs.setInt(7, inst);
            cs.setString(8, getNombres());
            cs.setString(9, getApellidos());
            cs.setString(10, getTelPers());
            cs.setString(11, getCel());
            cs.setString(12, getCorreoInst());
            cs.setString(13, getDireccion());
            cs.setString(14, getCargo());
            cs.setString(15, getTelInst());
            cs.setString(16, getFax());
            cs.setString(17, getCorreoPers());
            cs.setString(18, getRadio());
            cs.registerOutParameter(19, java.sql.Types.INTEGER);
            cs.execute();
            accion = cs.getInt(19);
            if (accion == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error el la ejecuci&oacute;n, el nuevo usuario no pudo ser registrado", null));
            } else if (accion > 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario registrado satisfactotiamente", null));
            }

        } catch (NumberFormatException ex) {
            System.out.println(ex);
        } finally {
            connection.close();
        }
        return "listadoIncidentes.xhtml";
    }

    public Integer getInstitucion() {
        return Institucion;
    }

    public void setinstitucion(Integer Institucion) {
        this.Institucion = Institucion;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String Alias) {
        this.Alias = Alias;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Integer getNivel() {
        return Nivel;
    }

    public void setNivel(Integer Nivel) {
        this.Nivel = Nivel;
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

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getTelInst() {
        return TelInst;
    }

    public void setTelInst(String TelInst) {
        this.TelInst = TelInst;
    }

    public String getCel() {
        return Cel;
    }

    public void setCel(String Cel) {
        this.Cel = Cel;
    }

    public String getTelPers() {
        return TelPers;
    }

    public void setTelPers(String TelPers) {
        this.TelPers = TelPers;
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

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public Integer getTpInstitucion() {
        return TpInstitucion;
    }

    public void setTpInstitucion(Integer TpInstitucion) {
        this.TpInstitucion = TpInstitucion;
    }

    public String getCodJurisdiccion() {
        return CodJurisdiccion;
    }

    public void setCodJurisdiccion(String CodJurisdiccion) {
        this.CodJurisdiccion = CodJurisdiccion;
    }

    public String getCodCanton() {
        return CodCanton;
    }

    public void setCodCanton(String CodCanton) {
        this.CodCanton = CodCanton;
    }
}
