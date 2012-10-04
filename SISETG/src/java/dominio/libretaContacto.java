package dominio;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import org.primefaces.event.RowEditEvent;

@ManagedBean
@ViewScoped
public class libretaContacto implements Serializable {

    private String CodDepartamento;
    private String CodMunicipio;
    private String CodCanton;
    private String CodCaserio;
    private String Ubicacion;
    private Integer TpInstitucion;
    private Integer Institucion;
    private String NombreContacto;
    private String LetraContacto;
    @Resource(name = "jdbc/sise")
    DataSource dataSource;

    public List<Municipio> getMunicipios() throws SQLException {
        List<Municipio> resultados = new ArrayList<Municipio>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        String Filtro = "where IDUBIC_PADRE LIKE '__'";
        if (CodDepartamento != null) {
            Filtro = "where IDUBIC_PADRE='" + CodDepartamento + "'";
        }
        try {
            String query = "SELECT IDUBIC, NOMBUBIC, LATITUDUBIC, LONGITUDUBIC FROM UBICACION " + Filtro + " order by NOMBUBIC";
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
        String Filtro = "where IDUBIC_PADRE LIKE '____'";
        if (CodMunicipio != null) {
            Filtro = "where IDUBIC_PADRE='" + CodMunicipio + "'";
        }
        try {
            String query = "SELECT IDUBIC, NOMBUBIC, LATITUDUBIC, LONGITUDUBIC FROM UBICACION " + Filtro + " order by NOMBUBIC";
            PreparedStatement getUbicacion = connection.prepareStatement(query);
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getUbicacion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new Canton(rowSet.getString("IDUBIC"),
                        rowSet.getString("NOMBUBIC"),
                        rowSet.getFloat("LATITUDUBIC"),
                        rowSet.getFloat("LONGITUDUBIC")));
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
        String Filtro = "where IDUBIC_PADRE LIKE '______'";
        if (CodCanton != null) {
            Filtro = "where IDUBIC_PADRE='" + CodCanton + "'";
        }
        try {
            String query = "SELECT IDUBIC, NOMBUBIC, LATITUDUBIC, LONGITUDUBIC FROM UBICACION " + Filtro + " order by NOMBUBIC";
            PreparedStatement getUbicacion = connection.prepareStatement(query);
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getUbicacion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new Caserio(rowSet.getString("IDUBIC"),
                        rowSet.getString("NOMBUBIC"),
                        rowSet.getFloat("LONGITUDUBIC"),
                        rowSet.getFloat("LATITUDUBIC")));
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
        String Filtro = "";
        if (TpInstitucion != null) {
            Filtro = " WHERE IDTPINST= " + TpInstitucion;
        }
        try {
            PreparedStatement getInstitucion = connection.prepareStatement(
                    "SELECT IDINST, NOMBINST FROM INSTITUCION " + Filtro);
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

    public List<Institucion> getTodasInstituciones() throws SQLException {
        List<Institucion> resultados = new ArrayList<Institucion>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            PreparedStatement getTodasInstitucion = connection.prepareStatement(
                    "SELECT IDINST, NOMBINST FROM INSTITUCION ");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getTodasInstitucion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new Institucion(rowSet.getInt("IDINST"),
                        rowSet.getString("NOMBINST")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public void order(String letra) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, letra, null));
        if ("0".equals(letra)) {
            this.LetraContacto = null;
        } else {
            this.LetraContacto = letra;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, letra, null));
        }
    }

    public List<DatosContactos> getLoadContactos() throws SQLException {
        List<DatosContactos> resultados = new ArrayList<DatosContactos>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        String filtro = " WHERE 1=1";
        if (CodCaserio != null) {
            filtro += " AND I.IDUBIC LIKE '" + CodCaserio + "'";
        } else if (CodCanton != null) {
            filtro += " AND I.IDUBIC LIKE '" + CodCanton + "'";
        } else if (CodMunicipio != null) {
            filtro += " AND I.IDUBIC LIKE '" + CodMunicipio + "'";
        } else if (CodDepartamento != null) {
            filtro += " AND I.IDUBIC LIKE '" + CodDepartamento + "'";
        }
        if (TpInstitucion != null) {
            filtro += " AND TP.IDTPINST=" + TpInstitucion;
        }
        if (Institucion != null) {
            filtro += " AND I.IDINST=" + Institucion;
        }
        if (NombreContacto != null) {
            filtro += " AND (NOMBCONT LIKE '%" + NombreContacto + "%' or APELLCONT LIKE '%" + NombreContacto + "%')";
        }
        if (LetraContacto != null) {
            filtro += " AND NOMBCONT LIKE '" + LetraContacto + "%'";
        }
        try {
            PreparedStatement getContactos = connection.prepareStatement(
                    "SELECT IDCONT, NOMBCONT, APELLCONT, TELCONT, CELCONT, MAILINSTCONT, DIRCONT, "
                    + "CARGOCONT, S.TELINSTCONT, FAXCONT, MAILPERCONT, RADIOCONT, U.IDUBIC, NOMBUBIC, "
                    + "I.IDINST, NOMBINST, I.IDTPINST, NOMBTPINST FROM CONTACTOS S INNER JOIN INSTITUCION I "
                    + "ON S.IDINST=I.IDINST INNER JOIN TIPOINSTITUCION TP ON I.IDTPINST=TP.IDTPINST "
                    + "INNER JOIN UBICACION U ON U.IDUBIC=I.IDUBIC "
                    + filtro + " ORDER BY NOMBCONT");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getContactos.executeQuery());
            while (rowSet.next()) {
                resultados.add(new DatosContactos(
                        rowSet.getInt("IDCONT"),
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
                        rowSet.getString("RADIOCONT"),
                        rowSet.getString("IDUBIC"),
                        rowSet.getString("NOMBUBIC"),
                        rowSet.getInt("IDINST"),
                        rowSet.getString("NOMBINST"),
                        rowSet.getInt("IDTPINST"),
                        rowSet.getString("NOMBTPINST")));
            }
            return resultados;
        } finally {
            connection.close();
        }
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
            DatosContactos obj = (DatosContactos) event.getObject();
            if (obj == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Problema en la ejecución, intente nuevamente"));
            } else {
                try {
                    CallableStatement cs;
                    Integer accion;
                    String sql = "{ call CONTACTOS_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                    cs = connection.prepareCall(sql);

                    cs.setInt(1, obj.getIdInst());
                    cs.setString(2, obj.getNombCont());
                    cs.setString(3, obj.getApellCont());
                    cs.setString(4, obj.getTelCont());
                    cs.setString(5, obj.getCelCont());
                    cs.setString(6, obj.getMailInstCont());
                    cs.setString(7, obj.getDirCont());
                    cs.setString(8, obj.getCargoCont());
                    cs.setString(9, obj.getTelInstCont());
                    cs.setString(10, obj.getFaxCont());
                    cs.setString(11, obj.getMailPerCon());
                    cs.setString(12, obj.getRadioCont());
                    cs.setInt(13, obj.getIdCont());
                    cs.registerOutParameter(14, java.sql.Types.INTEGER);

                    cs.execute();
                    accion = cs.getInt(14);
                    cs.close();
                    if (accion>0) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Edición realizada satisfactoriamente"+obj.getIdInst(), null));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error en la ejecución, intente nuevamente ", null));
                    }
                } catch (SQLException ex) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ex.toString() , null));
                    System.out.println(ex);
                } finally {
                    connection.close();
                }
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en la ejecución, Intente nuevamente", null));
        }
    }

    public void onCancel(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Edición Cancelada", "Edición Cancelada"));
    }

    public libretaContacto() {
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

    public Integer getInstitucion() {
        return Institucion;
    }

    public void setInstitucion(Integer Institucion) {
        this.Institucion = Institucion;
    }

    public String getLetraContacto() {
        return LetraContacto;
    }

    public void setLetraContacto(String LetraContacto) {
        this.LetraContacto = LetraContacto;
    }

    public String getNombreContacto() {
        return NombreContacto;
    }

    public void setNombreContacto(String NombreContacto) {
        this.NombreContacto = NombreContacto;
    }

    public Integer getTpInstitucion() {
        return TpInstitucion;
    }

    public void setTpInstitucion(Integer TpInstitucion) {
        this.TpInstitucion = TpInstitucion;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }
}
