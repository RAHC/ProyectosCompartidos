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
import javax.faces.bean.ViewScoped;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

//import model.GeneralModel;
/**
 *
 * @author Administrador
 */
@ManagedBean
@ViewScoped
public class init {

    private String IdTipoIncidente = "18";
    private String CodDepartamento;
    private String CodMunicipio;
    private String CodCanton;
    private String CodCaserio;
    private int TpInstitucion;
    private String IdUbicacion;
    @Resource(name = "jdbc/sise")
    DataSource dataSource;

    public init() {
    }

    public String getCodCanton() {
        return CodCanton;
    }

    public void setCodCanton(String CodCanton) {
        this.CodCanton = CodCanton;
    }

    public String getCodCaserio() {
        return CodCaserio;
    }

    public void setCodCaserio(String CodCaserio) {
        this.CodCaserio = CodCaserio;
    }

    public String getIdTipoIncidente() {
        return IdTipoIncidente;
    }

    public void setIdTipoIncidente(String IdTipoIncidente) {
        this.IdTipoIncidente = IdTipoIncidente;
    }

    public String getIdUbicacion() {
        return IdUbicacion;
    }

    public void setIdUbicacion(String IdUbicacion) {
        this.IdUbicacion = IdUbicacion;
    }

    public int getTpInstitucion() {
        return TpInstitucion;
    }

    public void setTpInstitucion(int TpInstitucion) {
        this.TpInstitucion = TpInstitucion;
    }

    public String getCodDepartamento() {
        return CodDepartamento;
    }

    public void setCodDepartamento(String CodDepartamento) {
        this.CodDepartamento = CodDepartamento;
    }

    public String getCodMunicipio() {
        return CodMunicipio;
    }

    public void setCodMunicipio(String CodMunicipio) {
        this.CodMunicipio = CodMunicipio;
    }

    public List<Rol> getRoles() throws SQLException {
        List<Rol> resultados = new ArrayList<Rol>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            PreparedStatement getRol = connection.prepareStatement(
                    "SELECT IDROL, NOMBROL, DESCROL FROM ROL");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getRol.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Rol(rowSet.getInt("IDROL"),
                        rowSet.getString("NOMBROL"),
                        rowSet.getString("DESCROL")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<TpInstitucion> getTpInstituciones() throws SQLException {
        List<TpInstitucion> resultados = new ArrayList<TpInstitucion>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            PreparedStatement getTpInstitucion = connection.prepareStatement(
                    "SELECT IDTPINST, NOMBTPINST, DESCTPINST FROM TIPOINSTITUCION");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getTpInstitucion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new TpInstitucion(rowSet.getInt("IDTPINST"),
                        rowSet.getString("NOMBTPINST"),
                        rowSet.getString("DESCTPINST")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<Institucion> getInstituciones() throws SQLException {
        List<Institucion> resultados = new ArrayList<Institucion>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            PreparedStatement getInstitucion = connection.prepareStatement(
                    "SELECT IDINST, NOMBINST FROM INSTITUCION WHERE IDTPINST= " + TpInstitucion);
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getInstitucion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new Institucion(rowSet.getInt("IDINST"),
                        rowSet.getString("NOMBINST")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<Estado> getEstadosValidacion() throws SQLException {

        List<Estado> resultados = new ArrayList<Estado>();

        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            PreparedStatement getEstado = connection.prepareStatement(
                    "SELECT IDESTADO, NOMBESTADO,COLORESTADO FROM ESTADO WHERE IDESTADO NOT IN (1, 8)");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getEstado.executeQuery());

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
            PreparedStatement getEstado = connection.prepareStatement(
                    "SELECT IDESTADO, NOMBESTADO,COLORESTADO FROM ESTADO");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getEstado.executeQuery());

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

    public List<Estado> getEstadoCierre() throws SQLException {

        List<Estado> resultados = new ArrayList<Estado>();

        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            PreparedStatement getEstado = connection.prepareStatement(
                    "SELECT IDESTADO, NOMBESTADO,COLORESTADO FROM ESTADO WHERE IDESTADO NOT IN(1,2,3) ORDER BY IDESTADO DESC");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getEstado.executeQuery());

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

    public List<Departamento> getDepartamentos() throws SQLException {
        List<Departamento> resultados = new ArrayList<Departamento>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            PreparedStatement getDepartamento = connection.prepareStatement(
                    "SELECT IDUBIC, NOMBUBIC, LATITUDUBIC, LONGITUDUBIC FROM UBICACION WHERE IDUBIC_PADRE is NULL order by NOMBUBIC");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getDepartamento.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Departamento(rowSet.getString("IDUBIC"),
                        rowSet.getString("NOMBUBIC"),
                        rowSet.getFloat("LATITUDUBIC"),
                        rowSet.getFloat("LONGITUDUBIC")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<Municipio> getMunicipios() throws SQLException {
        List<Municipio> resultados = new ArrayList<Municipio>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            String query = "SELECT IDUBIC, NOMBUBIC, LATITUDUBIC, LONGITUDUBIC FROM UBICACION WHERE IDUBIC_PADRE = '" + CodDepartamento + "' order by NOMBUBIC";
            PreparedStatement getUbicacion = connection.prepareStatement(query);
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getUbicacion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new Municipio(rowSet.getString("IDUBIC"),
                        rowSet.getString("NOMBUBIC"),
                        rowSet.getFloat("LATITUDUBIC"),
                        rowSet.getFloat("LONGITUDUBIC")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<Canton> getCantones() throws SQLException {
        List<Canton> resultados = new ArrayList<Canton>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            String query = "SELECT IDUBIC, NOMBUBIC FROM UBICACION WHERE IDUBIC_PADRE = '" + CodMunicipio + "' order by NOMBUBIC";
            PreparedStatement getUbicacion = connection.prepareStatement(query);
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getUbicacion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new Canton(rowSet.getString("IDUBIC"),
                        rowSet.getString("NOMBUBIC")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<Caserio> getCaserios() throws SQLException {
        List<Caserio> resultados = new ArrayList<Caserio>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            String query = "SELECT IDUBIC, NOMBUBIC FROM UBICACION WHERE IDUBIC_PADRE = '" + CodCanton + "' order by NOMBUBIC";
            PreparedStatement getUbicacion = connection.prepareStatement(query);
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getUbicacion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new Caserio(rowSet.getString("IDUBIC"),
                        rowSet.getString("NOMBUBIC")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<TpIncidente> getTpIncidentes() throws SQLException {

        List<TpIncidente> resultados = new ArrayList<TpIncidente>();

        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            PreparedStatement getTpIncidente = connection.prepareStatement(
                    "SELECT idtpinc, nombtpinc, desctpinc FROM TIPOINCIDENTE WHERE estadotpinc='H'");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getTpIncidente.executeQuery());

            while (rowSet.next()) {
                resultados.add(new TpIncidente(rowSet.getString("IDTPINC"),
                        rowSet.getString("NOMBTPINC"),
                        rowSet.getString("DESCTPINC")));
            }
            return resultados;
        } finally {
            connection.close();
        }

    }

    public List<TipoIncidente> getIncidentes() throws SQLException {
        List<TipoIncidente> resultados = new ArrayList<TipoIncidente>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            PreparedStatement getTpInstitucion = connection.prepareStatement(
                    "SELECT IDTPINC, NOMBTPINC FROM TIPOINCIDENTE WHERE IDTPINCPADRE =" + IdTipoIncidente);
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getTpInstitucion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new TipoIncidente(rowSet.getString("IDTPINC"),
                        rowSet.getString("NOMBTPINC")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<Incidente> getIncidentesActuales() throws SQLException {

        List<Incidente> resultados = new ArrayList<Incidente>();

        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            /* PreparedStatement getPrioridad = connection.prepareStatement(
             "SELECT TI.NOMBTPINC, I.FECHORAINIINC,I.DIRINC, I.PTOREFINC FROM INCIDENTE I JOIN TIPOINCIDENTE TI ON TI.IDTPINC=I.IDTPINC "
             + "WHERE I.IDUBIC=" + IdUbicacion + " OR I.IDTPINC=" + IdTipoIncidente);*/

            PreparedStatement getPrioridad = connection.prepareStatement(
                    "SELECT TI.NOMBTPINC, I.FECHORAINIINC,I.DIRINC, I.PTOREFINC, I.IDESTADO FROM INCIDENTE I JOIN TIPOINCIDENTE TI ON TI.IDTPINC=I.IDTPINC ORDER BY I.IDPRIOR "
                   );
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getPrioridad.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Incidente(rowSet.getInt("IDESTADO"),
                        rowSet.getString("NOMBTPINC"),
                        rowSet.getDate("FECHORAINIINC"),
                        rowSet.getString("DIRINC"),
                        rowSet.getString("PTOREFINC")));
            }
            return resultados;
        } finally {
            connection.close();
        }

    }

    public List<TipoIncidente> getTipoIncidente() throws SQLException {
        List<TipoIncidente> resultados = new ArrayList<TipoIncidente>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            PreparedStatement getTpInstitucion = connection.prepareStatement(
                    "SELECT IDTPINC, NOMBTPINC FROM TIPOINCIDENTE WHERE IDTPINCPADRE is null");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getTpInstitucion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new TipoIncidente(rowSet.getString("IDTPINC"),
                        rowSet.getString("NOMBTPINC")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<Evento> getEventos() throws SQLException {
        List<Evento> resultados = new ArrayList<Evento>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            PreparedStatement getRol = connection.prepareStatement(
                    "SELECT IDEV, NOMBEV FROM EVENTO WHERE ESTADOEV='H'");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getRol.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Evento(rowSet.getString("IDEV"),
                        rowSet.getString("NOMBEV")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }
}
