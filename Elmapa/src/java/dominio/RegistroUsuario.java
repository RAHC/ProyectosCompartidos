/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
@ManagedBean
@RequestScoped
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
    
    public int getContacto() {
        return Contacto;
    }

    public void setContacto(int Contacto) {
        this.Contacto = Contacto;
    }

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
        JOptionPane.showMessageDialog(null, "aqui estoy");
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            PreparedStatement addContacto = connection.prepareStatement(
                    "INSERT INTO CONTACTOS(IDCONT, IDINST, NOMBCONT, APELLCONT, TELCONT, CELCONT, MAILINSTCONT, DIRCONT, CARGOCONT, TELINSTCONT, FAXCONT, MAILPERCONT, RADIOCONT)"
                    + "VALUES((SELECT MAX(IDCONT) FROM CONTACTOS)+1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    addContacto.setInt(2, getInstitucion() );
                    addContacto.setString(3, getNombres() );
                    addContacto.setString(4, getApellidos());
                    addContacto.setString(5, getTelPers());
                    addContacto.setString(6, getCel());
                    addContacto.setString(7, getCorreoInst());
                    addContacto.setString(8, getDireccion());
                    addContacto.setString(9, getCargo());
                    addContacto.setString(10, getTelInst());
                    addContacto.setString(11, getFax());
                    addContacto.setString(12, getCorreoPers());
                    addContacto.setString(13, getRadio());
            
                    addContacto.executeUpdate();
        }
        finally{
            connection.close();
        }
        try{   
           PreparedStatement addUsuario = connection.prepareStatement(
                   "INSERT INTO USUARIO(IDCONT, IDROL, IDUBIC, USERNAME, USERPASS, USERESTADO)"
                   + "VALUES((SELECT MAX(IDCONT) FROM CONTACTOS), ?, ?, ?, ENCRYPTBYPASSPHRASE(?,'123'), 'A')");
                   addUsuario.setInt(2, getNivel());
                   addUsuario.setString(3, getMunicipio());
                   addUsuario.setString(4, getAlias());
                   addUsuario.setString(5, getPassword());
                   
                   addUsuario.executeUpdate();
                   
        }
        finally{
            connection.close();
        }
        return "index";
    }
    
}
