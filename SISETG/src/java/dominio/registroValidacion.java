/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
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

   
    private String Incidente;
    private Date FechaHora;
    private String Acciones;
    private Integer Estado;
    private String corrInc;
    private Integer IdNuevaAccion;
    
    @Resource(name = "jdbc/sise")
    DataSource dataSource;
    
    public registroValidacion() {
    }


    public String guardar() throws SQLException{
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
        int CodAccion = -1;    
        try{
            String sql = "{ call VALIDACION_Add(?,?,?,?,?,?,?)}";
            cs = connection.prepareCall(sql);
                cs.setString(1, nB.getIdEvento());
                cs.setString(2, getCorrInc());
                cs.setInt(3, getEstado());
                cs.setInt(4, nB.getIdCont());
                cs.setString(5,getAcciones());
                cs.setString(6, fecha);
                cs.registerOutParameter(7, java.sql.Types.INTEGER);
                cs.execute();
                //int iUpdCount = cs.getUpdateCount();
                //boolean bMoreResults = true;
                ResultSet rs = null;
                
                    rs = cs.getResultSet();
                    if(rs!=null){
                        rs.next();
                        CodAccion = rs.getInt(1);
                        this.IdNuevaAccion = CodAccion;
                    }
                
                System.out.println("Codigo="+CodAccion);
                cs.close();
                
        }
        finally{
            connection.close();
        }
        return "listadoIncidentes.xhtml?"+IdNuevaAccion;
    }

     public String getIncidente() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        this.Incidente = (String) facesContext.getExternalContext().getRequestParameterMap().get("corrInc");
        
        return this.Incidente;
    }
    public void setIncidente(String Incidente) {
        this.Incidente = Incidente;
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

    public String getCorrInc() {
        return corrInc;
    }

    public void setCorrInc(String corrInc) {
        this.corrInc = corrInc;
    }

    public Integer getIdNuevaAccion() {
        return IdNuevaAccion;
    }

    public void setIdNuevaAccion(Integer IdNuevaAccion) {
        this.IdNuevaAccion = IdNuevaAccion;
    }
    
    
}
