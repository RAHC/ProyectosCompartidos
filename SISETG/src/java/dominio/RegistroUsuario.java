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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
@ManagedBean
@ViewScoped
public class RegistroUsuario implements Serializable{

    private String Alias;
    private String Password;
    private Integer Nivel;
    private String Ubicacion;
    private String CodDepartamento;
    private String CodMunicipio;
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
   
    public RegistroUsuario() {
    }
    
    public Integer getContacto() {
        return Contacto;
    }
    public void setContacto(Integer Contacto) throws SQLException {
        this.Contacto = Contacto;
        if(Contacto>0){
            String sql;
            sql = "SELECT * FROM CONTACTOS WHERE IDCONT=" + Contacto;
            if (dataSource == null) {
                throw new SQLException("No se pudo tener acceso a la fuente de datos");
            }
            Connection connection = dataSource.getConnection();

            if (connection == null) {
                throw new SQLException("No se pudo conectar a la fuente de datos");
            }
            try{
                PreparedStatement datosContacto = connection.prepareStatement(sql);
                CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
                rowSet.populate(datosContacto.executeQuery());
                rowSet.next();
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
                
            }  
            finally{
                connection.close();
            }
        }
    }
    
    public List<Contacto> getContactos() throws SQLException{
            List<Contacto> resultados = new ArrayList<Contacto>();
            if (dataSource == null) {
                throw new SQLException("No se pudo tener acceso a la fuente de datos");
            }
            Connection connection = dataSource.getConnection();

            if (connection == null) {
                throw new SQLException("No se pudo conectar a la fuente de datos");
            }
            try{
                PreparedStatement getContacto = connection.prepareStatement(
                        "SELECT * FROM CONTACTOS WHERE IDINST =" + Institucion + ""
                        + " AND IDCONT NOT IN(SELECT IDCONT FROM USUARIO)");
                CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
                rowSet.populate(getContacto.executeQuery());
                while (rowSet.next()){
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
            }
            finally{
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
        if(getUbicacion()==null){
            Ubic = "00";
        }
        else{
            Ubic = getUbicacion();
        }
        int contact;
        if(getContacto()==null){
            contact = 0;
        }
        else{
            contact = getContacto();
        }

        CallableStatement cs;
        try {
            String sql = "{ call USUARIO_Add(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            
            cs = connection.prepareCall(sql);
                    cs.setString(1, getAlias());
                    cs.setString(2, getPassword());
                    cs.setInt(3, getNivel());
                    cs.setString(4,Ubic);
                    cs.setInt(5, contact);
                    cs.setInt(6, getInstitucion());
                    cs.setString(7,getNombres());
                    cs.setString(8, getApellidos());
                    cs.setString(9, getTelPers());
                    cs.setString(10,getCel());
                    cs.setString(11, getCorreoInst());
                    cs.setString(12, getDireccion());
                    cs.setString(13,getCargo());
                    cs.setString(14, getTelInst());
                    cs.setString(15, getFax());
                    cs.setString(16, getCorreoPers());
                    cs.setString(17, getRadio());    
            
                    cs.execute();
        }
         catch(NumberFormatException ex){
             System.out.println(ex);
        }
        finally{
            connection.close();
        }
        return "index";
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
        this.Ubicacion = CodDepartamento;
    }

    public String getCodMunicipio() {
        return CodMunicipio;
    }

    public void setCodMunicipio(String CodMunicipio) {
        this.CodMunicipio = CodMunicipio;
        this.Ubicacion = CodMunicipio;
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
    
}
