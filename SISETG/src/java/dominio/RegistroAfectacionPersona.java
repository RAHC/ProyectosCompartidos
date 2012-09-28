/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.sql.DataSource;

/**
 *
 * @author Familia Molina
 */
@ManagedBean
@ViewScoped
public class RegistroAfectacionPersona {

    private String nombre;
    private int edad;
    private String genero;
    private String discap;
    private String causaAfec;
    private String centro;
    @Resource(name = "jdbc/sise")
    DataSource dataSource;

    /**
     * Creates a new instance of RegistroAfectacionPersona
     */
    public RegistroAfectacionPersona() {
    }

    public void guardar(ActionEvent actionEvent) throws SQLException {
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            CallableStatement cs;
            String sql;
            sql = "{ call registrarAfectacionAPersona(?,?,?,?,?,?)}";
            cs = connection.prepareCall(sql);

            cs.setString(1, getNombre());
            cs.setInt(2, getEdad());
            cs.setString(3, getGenero());
            cs.setString(4, getDiscap());
            cs.setString(5, getCentro());
            cs.setString(6, getCausaAfec());

            cs.execute();
            cs.close();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Afectacion Agregada", "Afectacion Agregada"));

        } finally {
            connection.close();
        }
        //return "registroIncidente";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDiscap() {
        return discap;
    }

    public void setDiscap(String discap) {
        this.discap = discap;
    }

    public String getCausaAfec() {
        return causaAfec;
    }

    public void setCausaAfec(String causaAfec) {
        this.causaAfec = causaAfec;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }
}
