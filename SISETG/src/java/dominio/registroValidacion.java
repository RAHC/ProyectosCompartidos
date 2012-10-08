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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author Administrador
 */
@ManagedBean
@ViewScoped
public class registroValidacion {
   
    private Date FechaHora;
    private String Acciones;
    private Integer Estado;
    private DatosIncidente selectedIncidente;
    
    @Resource(name = "jdbc/sise")
    DataSource dataSource;
    
    public registroValidacion() {
    }

    public void guardar() throws SQLException{
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String fecha = dateFormat.format(getFechaHora());
        
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        LoginBean nB = (LoginBean) session.getAttribute("loginBean");
        CallableStatement cs;
        Integer accion;  
        try{
            String sql = "{ call VALIDACION_Add(?,?,?,?,?,?,?)}";
            cs = connection.prepareCall(sql);
                cs.setString(1, getSelectedIncidente().getIdEv());
                cs.setString(2, getSelectedIncidente().getCorrInc());
                cs.setInt(3, getEstado());
                cs.setInt(4, nB.getIdCont());
                cs.setString(5,getAcciones());
                cs.setString(6, fecha);
                cs.registerOutParameter(7, java.sql.Types.INTEGER);
                cs.execute();

                accion = cs.getInt(7);
                if (accion>0) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Validación guardada satisfactoriamente", null));
                } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error en la ejecución, intente nuevamente ", null));
               }
        }
        catch (SQLException ex) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ex.toString() , null));
                    System.out.println(ex);
                }
        finally{
            connection.close();
            this.selectedIncidente = null;
            this.Estado = null;
            this.FechaHora = null;
            this.Acciones = null;
        }
    }


    public Date getFechaHora() {
        return FechaHora;
    }

    public void setFechaHora(Date FechaHora) {
        this.FechaHora = FechaHora;
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

    public DatosIncidente getSelectedIncidente() {
        return selectedIncidente;
    }

    public void setSelectedIncidente(DatosIncidente selectedIncidente) {
        this.selectedIncidente = selectedIncidente;
    }
    
}
