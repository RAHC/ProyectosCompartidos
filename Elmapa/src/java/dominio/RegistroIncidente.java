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

/**
 *
 * @author Familia Molina
 */
@ManagedBean
@RequestScoped
public class RegistroIncidente {

    private String Nombres;
    private String Apellidos;
    private String telefono;
    //private String Departamento;
    private String Municipio;
    private String Canton;
    private String Caserio;
    private String Direccion;
    private String Referencia;
    private String Estado;
    private String Prioridad;
    @Resource(name = "jdbc/sise")
    DataSource dataSource;

    public String getEstado() {
        return Estado;
    }

    public String getPrioridad() {
        return Prioridad;
    }

    public void setPrioridad(String Prioridad) {
        this.Prioridad = Prioridad;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getCanton() {
        return Canton;
    }

    public void setCanton(String Canton) {
        this.Canton = Canton;
    }

    public String getCaserio() {
        return Caserio;
    }

    public void setCaserio(String Caserio) {
        this.Caserio = Caserio;
    }

    /*public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String Departamento) {
        this.Departamento = Departamento;
    }*/

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
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

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String Referencia) {
        this.Referencia = Referencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

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

    public String guardar() throws SQLException  {
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            PreparedStatement addLlamada = connection.prepareStatement(
                    "INSERT INTO LLAMADA"+ 
                    "(TELINFOR,NOMBINFOR,APELLINFOR)" +
                    "VALUES (?,?,?)");
            
            addLlamada.setString(1, getTelefono());
            addLlamada.setString(2, getNombres());
            addLlamada.setString(3, getMunicipio());
            
            addLlamada.executeUpdate();
            
        } finally {
            connection.close();
        }
        return "index";
    }
}
