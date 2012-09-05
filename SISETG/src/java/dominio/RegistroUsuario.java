/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Administrador
 */
@ManagedBean
@ViewScoped
public class RegistroUsuario {

    private String Alias;
    private String Password;
    private int Nivel;
    private String Municipio;
    private int Institucion;
    private int Contacto;
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

    public int getInstitucion() {
        return Institucion;
    }

    public void setinstitucion(int Institucion) {
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
        public int getNivel() {
        return Nivel;
    }

    public void setNivel(int Nivel) {
        this.Nivel = Nivel;
    }
     public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String Municipio) {
        this.Municipio = Municipio;
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
    public int getContacto() {
        return Contacto;
    }
    public void setContacto(int Contacto){
        this.Contacto = Contacto;
    }
    
    public void DatosContacto() throws SQLException{

        if (dataSource == null) {
             throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try{
            PreparedStatement datosContacto = connection.prepareStatement(
                    "SELECT * FROM CONTACTOS WHERE IDCONT=" + Contacto);
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(datosContacto.executeQuery());
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
    public RegistroUsuario() {
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
                        "SELECT * FROM CONTACTOS WHERE IDINST =" + Institucion);
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
        CallableStatement cs = null;
        try {
            String sql = "{ call sp_registroUsuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            cs = connection.prepareCall(sql);
                    cs.setString(1, getAlias());
                    cs.setString(2, getPassword());
                    cs.setInt(3, getNivel());
                    cs.setString(4,getMunicipio());
                    cs.setInt(5, getContacto());
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
        finally{
            connection.close();
        }
        return "index";
    }
    
}
