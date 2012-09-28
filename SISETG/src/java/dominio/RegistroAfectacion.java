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
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Familia Molina
 */
@ManagedBean
@SessionScoped
public class RegistroAfectacion implements Serializable {

    private Date fechaHora;
    private String TipoAfectacion;
    private int cantidad;
    private String Descripcion;
    private String danosMateriales;
    private String Direccion;
    private String Referencia;
    @Resource(name = "jdbc/sise")
    DataSource dataSource;

    /**
     * Creates a new instance of RegistroAfectacion
     */
    public RegistroAfectacion() {
    }

    public void onEdit(RowEditEvent event) throws SQLException {
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        if (event != null) {
            AfectacionAPersona obj = (AfectacionAPersona) event.getObject();
            if (obj == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Edición Realizada", "Valimos con el objeto Afectacion a Personas"));
            } else {
                try {
                    PreparedStatement cs;
                    String sql = "UPDATE afectacionapersona SET IDTPAFEC=?, NOMBAFECPER=?, EDADAFECPER=?, GENEROAFECPER=?,DISCAFECPER=?, CENTROASISTAFECPER=?,IDAFEC=? where IDAFECPER=?";
                    cs = connection.prepareStatement(sql);

                    cs.setString(1, obj.getIdTPAfectacion());
                    cs.setString(2, obj.getNombreAfectado());
                    //cs.setString(3, obj.getNombreAfectado());
                    cs.setInt(3, obj.getEdadAfectado());
                    cs.setString(4, obj.getGeneroAfectado());//Posiblemente de error
                    cs.setString(5, obj.getEsDiscapacitado());
                    cs.setString(6, obj.getCentroAsistencial());
                    cs.setString(7, obj.getIdTPAfectacion());
                    cs.setInt(8, obj.getIdAfecPer());

                    boolean res = cs.execute(); 
                    cs.close();

                    if (res == false) {
                        FacesMessage msg = new FacesMessage("Edición Realizada", "" + obj.getIdAfecPer()+res);
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    }

                } finally {
                    connection.close();
                }

            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Edición Realizada", "Valimos con el objeto RowEditEvent"));
        }

    }

    public void onCancel(RowEditEvent event) {
        //FacesMessage msg = new FacesMessage("Edición Cancelada", ((AfectacionAPersona) event.getObject()).getNombreAfectado());

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Edición Cancelada", "Edición Cancelada"));
    }

    public String guardar() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            CallableStatement cs;
            String sql = "{ call registrarAfectacion(?,?,?,?,?,?,?,?,?,?,?,?)}";
            cs = connection.prepareCall(sql);

            FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            LoginBean nB = (LoginBean) session.getAttribute("loginBean");

            Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
            init in = (init) viewMap.get("init");

            cs.setString(1, nB.getIdEvento());
            cs.setString(2, getTipoAfectacion());
            cs.setString(3, "0301");
            cs.setString(4, getDescripcion());
            cs.setDate(5, new java.sql.Date(getFechaHora().getTime()));
            cs.setString(6, getDireccion());
            cs.setInt(7, getCantidad());
            cs.setString(8, getDanosMateriales());
            cs.setDouble(9, 13.0000);
            cs.setDouble(10, 13.0000);
            cs.setDouble(11, 13.0000);
            cs.setString(12, getReferencia());
            //cs.setString(13, "H");

            cs.execute();
            cs.close();

        } finally {
            connection.close();
        }
        return "registroIncidente";
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTipoAfectacion() {
        return TipoAfectacion;
    }

    public void setTipoAfectacion(String TipoAfectacion) {
        this.TipoAfectacion = TipoAfectacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getDanosMateriales() {
        return danosMateriales;
    }

    public void setDanosMateriales(String danosMateriales) {
        this.danosMateriales = danosMateriales;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String Referencia) {
        this.Referencia = Referencia;
    }
}
