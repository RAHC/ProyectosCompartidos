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
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Familia Molina
 */
@ManagedBean
@RequestScoped
public class RegistroIncidente {

    private Date fechaHora;
    private String Descripcion;
    private String IdTipoIncidente;
    private String Nombres;
    private String Apellidos;
    private String telefono;
    //private String Departamento;
    /*private String Municipio;
     private String Canton;
     private String Caserio;*/
    private String IdUbicacion = "0301";
    private String ptoReferencia;
    private String Direccion;
    private String Referencia;
    private int Estado;
    private int Prioridad;
    private String IdEvento;
    private double lat;
    private double lng;
    @Resource(name = "jdbc/sise")
    DataSource dataSource;

    /**
     * Creates a new instance of RegistroIncidente
     */
    public RegistroIncidente() {
    }

    public List<Prioridad> getPrioridades() throws SQLException {

        List<Prioridad> resultados = new ArrayList<Prioridad>();

        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            PreparedStatement getPrioridad = connection.prepareStatement(
                    "SELECT idprior, nombprior,descprior FROM PRIORIDADINCIDENTE");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getPrioridad.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Prioridad(rowSet.getInt("IDPRIOR"),
                        rowSet.getString("NOMBPRIOR"),
                        rowSet.getString("DESCPRIOR")));
            }
            //return rowSet;
            return resultados;
        } finally {
            connection.close();
        }

    }

    public List<Estado> getEstados() throws SQLException {

        List<Estado> resultados = new ArrayList<Estado>();

        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            PreparedStatement getPrioridad = connection.prepareStatement(
                    "SELECT IDESTADO, NOMBESTADO,COLORESTADO FROM ESTADO");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getPrioridad.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Estado(rowSet.getInt("IDESTADO"),
                        rowSet.getString("NOMBESTADO"),
                        rowSet.getString("COLORESTADO")));
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

        try {
            CallableStatement cs;
            String sql = "{ call registrarIncidente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            cs = connection.prepareCall(sql);

            FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            LoginBean nB = (LoginBean) session.getAttribute("loginBean");

            Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
            init in = (init) viewMap.get("init");


            if (!(in == null)) {

                /*if (!in.getCodCaserio().equals("")) {
                 cs.setString(4, in.getCodCaserio());
                 } else {*/
                if (!in.getCodCanton().equals("")) {
                    cs.setString(4, in.getCodCanton());
                } else {
                    cs.setString(4, in.getCodMunicipio());
                }
            }
            //}

            cs.setString(1, getNombres());
            cs.setString(2, getApellidos());
            cs.setString(3, getTelefono());
            cs.setString(5, getDireccion());
            cs.setString(6, getPtoReferencia());
            cs.setDouble(7, in.getLat());
            cs.setDouble(8, in.getLng());
            cs.setDouble(9, 0);
            cs.setString(10, in.getIdTipoIncidente());
            cs.setDate(11, new java.sql.Date(getFechaHora().getTime()));
            cs.setString(12, getDescripcion());
            cs.setInt(13, getEstado());
            cs.setInt(14, getPrioridad());
            cs.setString(15, nB.getIdEvento());

            cs.execute();

            //FacesContext.getCurrentInstance().addMessage("f1:guardar", new FacesMessage("Incidente agregado"));

            cs.close();




            //connection.commit();

        } finally {
            connection.close();
        }
        return "registroIncidente";
    }

    public String getIdUbicacion() {
        return IdUbicacion;
    }

    public void setIdUbicacion(String IdUbicacion) {
        this.IdUbicacion = IdUbicacion;
    }

    public String getPtoReferencia() {
        return ptoReferencia;
    }

    public void setPtoReferencia(String ptoReferencia) {
        this.ptoReferencia = ptoReferencia;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getIdTipoIncidente() {
        return IdTipoIncidente;
    }

    public void setIdTipoIncidente(String IdTipoIncidente) {
        this.IdTipoIncidente = IdTipoIncidente;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    public int getPrioridad() {
        return Prioridad;
    }

    public void setPrioridad(int Prioridad) {
        this.Prioridad = Prioridad;
    }

    public String getIdEvento() {
        return IdEvento;
    }

    public void setIdEvento(String IdEvento) {
        this.IdEvento = IdEvento;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
    
    
}
