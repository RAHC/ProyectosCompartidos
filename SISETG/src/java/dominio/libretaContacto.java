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
        String jointable = "";
        String select = "";
        String sql;
        boolean flag = false;
        if (CodCaserio != null) {
            filtro += " AND C.DUBIC LIKE '" + CodCaserio + "'%";
        } else if (CodCanton != null) {
            filtro += " AND C.IDUBIC LIKE '" + CodCanton + "'%";
        } else if (CodMunicipio != null) {
            filtro += " AND C.IDUBIC LIKE '" + CodMunicipio + "%'";
        } else if (CodDepartamento != null) {
            filtro += " AND C.IDUBIC LIKE '" + CodDepartamento + "%'";
        }
        if (TpInstitucion != null && Institucion == null) {
            select += ", NOMBINST, I.IDTPINST, NOMBTPINST ";
            jointable += " INNER JOIN INSTITUCION I ON C.IDINST=I.IDINST INNER JOIN TIPOINSTITUCION TP ON I.IDTPINST=TP.IDTPINST ";
            filtro += " AND C.IDINST IN (SELECT DISTINCT(IDINST) FROM INSTITUCION WHERE IDTPINST=" + TpInstitucion + ")";
            flag = true;
        }
        if (TpInstitucion != null && Institucion != null) {
            select += ", NOMBINST, I.IDTPINST, NOMBTPINST ";
            jointable += " INNER JOIN INSTITUCION I ON C.IDINST=I.IDINST INNER JOIN TIPOINSTITUCION TP ON I.IDTPINST=TP.IDTPINST ";
            filtro += " AND C.IDINST=" + Institucion + " AND I.IDTPINST=" + TpInstitucion;
            flag = true;
        }
        if (TpInstitucion == null && Institucion != null) {
            select += ", NOMBINST, I.IDTPINST, NOMBTPINST ";
            jointable += " INNER JOIN INSTITUCION I ON C.IDINST=I.IDINST INNER JOIN TIPOINSTITUCION TP ON I.IDTPINST=TP.IDTPINST ";
            filtro += " AND C.IDINST=" + Institucion;
            flag = true;
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
                    + "CARGOCONT, TELINSTCONT, FAXCONT, MAILPERCONT, RADIOCONT, C.IDUBIC, NOMBUBIC, C.IDINST " + select
                    + "FROM CONTACTOS C INNER JOIN UBICACION U ON U.IDUBIC=C.IDUBIC " + jointable + " "
                    + filtro + " ORDER BY NOMBCONT");

            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getContactos.executeQuery());
            while (rowSet.next()) {
                /*Verifica los datos de la institucion si el contacto pertenece a una institucion*/
                Integer idInst = 0;
                String nombInst = "";
                Integer idTpInst = 0;
                String NombTpInst = "";
                if (rowSet.getInt("IDINST") > 0) {
                    idInst = rowSet.getInt("IDINST");
                    PreparedStatement getInst = connection.prepareStatement(
                            "SELECT NOMBINST, I.IDTPINST, NOMBTPINST FROM INSTITUCION I INNER JOIN "
                            + "TIPOINSTITUCION TP ON I.IDTPINST=TP.IDTPINST WHERE I.IDINST = "+rowSet.getInt("IDINST"));
                    CachedRowSet rowSet2 = new com.sun.rowset.CachedRowSetImpl();
                    rowSet2.populate(getInst.executeQuery());
                    rowSet2.next();
                    nombInst = rowSet2.getString("NOMBINST");
                    idTpInst = rowSet2.getInt("IDTPINST");
                    NombTpInst = rowSet2.getString("NOMBTPINST");
                }
                if (flag == true) {
                    if (rowSet.getString("NOMBINST").length() > 0) {
                        nombInst = rowSet.getString("NOMBINST");
                    }
                    if (rowSet.getInt("IDTPINST") > 0) {
                        idTpInst = rowSet.getInt("IDTPINST");
                    }
                    if (rowSet.getString("NOMBTPINST").length() > 0) {
                        NombTpInst = rowSet.getString("NOMBTPINST");
                    }
                }
                /*Obtiene nombre y Id de la Ubicacion*/
                String IdCanton = "";
                String NombCanton ="";
                String IdMunicipio ="";
                String NombMunicipio="";
                String IdDepartamento="";
                String NombDepartamento="";
                Integer count;
                count = rowSet.getString("IDUBIC").length();
                if (count >= 5) {
                    IdCanton = rowSet.getString("IDUBIC");
                    NombCanton = rowSet.getString("NOMBUBIC");
                    sql = "SELECT IDUBIC, NOMBUBIC, IDUBIC_PADRE FROM UBICACION WHERE IDUBIC=(SELECT IDUBIC_PADRE FROM "
                            + "UBICACION I WHERE I.IDUBIC=" + rowSet.getString("IDUBIC")+")";
                    PreparedStatement datoMunicipio = connection.prepareStatement(sql);
                    CachedRowSet rowSet3 = new com.sun.rowset.CachedRowSetImpl();
                    rowSet3.populate(datoMunicipio.executeQuery());
                    rowSet3.next();
                    IdMunicipio = rowSet3.getString("IDUBIC");
                    NombMunicipio = rowSet3.getString("NOMBUBIC");

                    sql = "SELECT IDUBIC, NOMBUBIC FROM UBICACION WHERE IDUBIC=" + rowSet3.getString("IDUBIC_PADRE");
                    PreparedStatement datoDepartamento = connection.prepareStatement(sql);
                    CachedRowSet rowSet4 = new com.sun.rowset.CachedRowSetImpl();
                    rowSet4.populate(datoDepartamento.executeQuery());
                    rowSet4.next();
                    IdDepartamento = rowSet4.getString("IDUBIC");
                    NombDepartamento = rowSet4.getString("NOMBUBIC");
                } 
                else if (count >= 3) {
                    IdMunicipio = rowSet.getString("IDUBIC");
                    NombMunicipio = rowSet.getString("NOMBUBIC");
                    sql = "SELECT IDUBIC, NOMBUBIC FROM UBICACION WHERE IDUBIC=(SELECT IDUBIC_PADRE FROM "
                            + "UBICACION WHERE IDUBIC=" + rowSet.getString("IDUBIC")+")";
                    PreparedStatement datoDepartamento = connection.prepareStatement(sql);
                    CachedRowSet rowSet3 = new com.sun.rowset.CachedRowSetImpl();
                    rowSet3.populate(datoDepartamento.executeQuery());
                    rowSet3.next();
                    IdDepartamento = rowSet3.getString("IDUBIC");
                    NombDepartamento = rowSet3.getString("NOMBUBIC");
                } 
                else if (count >= 1) {
                    IdDepartamento = rowSet.getString("IDUBIC");
                    NombDepartamento = rowSet.getString("NOMBUBIC");
                }
                
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
                        IdDepartamento,
                        NombDepartamento,
                        IdMunicipio,
                        NombMunicipio,
                        IdCanton,
                        NombCanton,
                        idInst,
                        nombInst,
                        idTpInst,
                        NombTpInst));
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
                    if (accion > 0) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Edición realizada satisfactoriamente" + obj.getIdInst(), null));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error en la ejecución, intente nuevamente ", null));
                    }
                } catch (SQLException ex) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ex.toString(), null));
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
