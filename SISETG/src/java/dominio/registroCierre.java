/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.sql.DataSource;

/**
 *
 * @author Administrador
 */
@ManagedBean
@ViewScoped
public class registroCierre {

     private String Evento;
    private String Incidente;
    private Date Fecha;
    private String Acciones;
    private Integer Estado;
    private Integer Usuario;
    
    @Resource(name = "jdbc/sise")
    DataSource dataSource;
    public registroCierre() {
    }

    public String getEvento() {
        return Evento;
    }

    public void setEvento(String Evento) {
        this.Evento = Evento;
    }

    public String getIncidente() {
        return Incidente;
    }

    public void setIncidente(String Incidente) {
        this.Incidente = Incidente;
    }

    public Integer getUsuario() {
        return Usuario;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public String getAcciones() {
        return Acciones;
    }

    public void setAcciones(String Acciones) {
        this.Acciones = Acciones;
    }

    public Integer getEstado() {
        return Estado;
    }

    public void setEstado(Integer Estado) {
        this.Estado = Estado;
    }
     public void guardar() throws SQLException{
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        CallableStatement cs;
        try{
            String sql = "{ call sp_registroValidacion(?,?,?,?,?,?)}";
            cs = connection.prepareCall(sql);
                cs.setString(1, getEvento());
                cs.setString(2, getIncidente());
                cs.setInt(3, getEstado());
                cs.setInt(4, getUsuario());
                cs.setString(5,getAcciones());
                cs.setDate(6, utilToSql(getFecha()));
                
                cs.executeQuery();
        }
        finally{
            connection.close();
        }
    }
    private java.sql.Date utilToSql(java.util.Date fecha) {
        DateFormat sqlDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        return java.sql.Date.valueOf(sqlDateFormatter.format(fecha));   
    }
    
}
