
package dominio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.sql.DataSource;

@ManagedBean
@RequestScoped
public class RegistroContacto {
    private String Nombres;
    private String Apellidos;
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
    public String guardar() throws SQLException{
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        CallableStatement cs;
        try{
            String sql="{ call sp_registroContacto(?,?,?,?,?,?,?,?,?,?,?)}";
            cs = connection.prepareCall(sql);
                cs.setInt(1, getInstitucion());
                cs.setString(2, getNombres());
                cs.setString(3, getApellidos());
                cs.setString(4, getTelPers());
                cs.setString(5, getCel());
                cs.setString(6, getCorreoInst());
                cs.setString(7, getCargo());
                cs.setString(8, getTelInst());
                cs.setString(9, getFax());
                cs.setString(10, getCorreoPers());
                cs.setString(11, getRadio());
                
                cs.execute();
        }
        finally{
            connection.close();
        }
        return "index";
    }
    public String getCancelar(){
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
    
}
