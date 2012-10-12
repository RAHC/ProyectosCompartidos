package dominio;

/**
 *
 * @author J@RG
 */
import java.lang.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import org.primefaces.model.DualListModel;

@ManagedBean
@ViewScoped
//@RequestScoped
public class SeguimientoBean {

    @Resource(name = "jdbc/sise")
    DataSource dataSource;
    //private int estadoSeguimiento;
    private int estado;
    private Date fechaHora;
    private Date duracion;
    private String responsable;
    private String descripcion;
    private DualListModel<Institucion> Instituciones;
    private String corrInc;

    public DualListModel<Institucion> getInstituciones() throws SQLException {
        if (Instituciones == null) {
            List<Institucion> institucionesSource = new ArrayList<Institucion>();
            List<Institucion> institucionesTarget = new ArrayList<Institucion>();

            if (dataSource == null) {
                throw new SQLException("No se pudo tener acceso a la fuente de datos");
            }
            Connection connection = dataSource.getConnection();

            if (connection == null) {
                throw new SQLException("No se pudo conectar a la fuente de datos");
            }
            PreparedStatement getInstitucion = connection.prepareStatement(
                    "SELECT IDINST, NOMBINST FROM INSTITUCION ");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getInstitucion.executeQuery());
            while (rowSet.next()) {
                institucionesSource.add(new Institucion(rowSet.getInt("IDINST"),
                        rowSet.getString("NOMBINST")));
            }
            Instituciones = new DualListModel<Institucion>(institucionesSource, institucionesTarget);

            connection.close();
        }
        return Instituciones;
    }

    public void setInstituciones(DualListModel<Institucion> Instituciones) {
        this.Instituciones = Instituciones;
    }

    public String getCorrInc() {
        return corrInc;
    }

    public void setCorrInc(String corrInc) {
        this.corrInc = corrInc;
    }

    
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Date getDuracion() {
        return duracion;
    }

    public void setDuracion(Date duracion) {
        this.duracion = duracion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String navegar() {

        return "index";
    }
    
    public List<Acciones> getAccionesRealizadas() throws SQLException {

        List<Acciones> resultados = new ArrayList<Acciones>();

        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            
             
            /* String idEv = new String();
            String corrInc = new String();
            idEv = "TT1201";
            corrInc = "0000001";
            */

            PreparedStatement getAccionesRealizadas = connection.prepareStatement(
                    "SELECT A.RESPCOORDACC, A.DESCACC, A.FECHORAREALACC,  CONVERT(VARCHAR,A.DURACACC,108) AS DURACION, E.NOMBESTADO "
                    + "FROM ACCIONES AS A,ESTADO AS E "
                    + "WHERE A.IDESTADO = E.IDESTADO AND"
                    + " A.IDEV = ? AND"
                    + " A.CORRINC = ? AND"
                    + " A.ESTADOACC = 'H'"
                    + "ORDER BY IDACC DESC");
            
            FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            LoginBean nB = (LoginBean) session.getAttribute("loginBean");/*para obtener el identificador del usuario*/

            
            getAccionesRealizadas.setString(1, nB.getIdEvento());
            getAccionesRealizadas.setString(2, getCorrInc());           
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getAccionesRealizadas.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Acciones(
                        rowSet.getString("RESPCOORDACC"),
                        rowSet.getString("DESCACC"),
                        rowSet.getDate("FECHORAREALACC"),
                        rowSet.getString("DURACION"),
                        rowSet.getString("NOMBESTADO")));
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

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String fecha = dateFormat.format(getFechaHora());

            DateFormat dateFormathora = new SimpleDateFormat("HH:mm");
            String horaDuracion = dateFormathora.format(getDuracion());

            String institucion = new String();

            Iterator<Institucion> inst = getInstituciones().getTarget().iterator();
            while (inst.hasNext()) {
                //System.out.println(inst.next());
                institucion = institucion + inst.next() + ",";

            }

            //System.out.println("valor :"+institucion);

            FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            LoginBean nB = (LoginBean) session.getAttribute("loginBean");/*para obtener el identificador del usuario*/

            CallableStatement addAccionSeg;
            String sql = "{ call SEGUIMIENTOYCONTROL_ADD(?,?,?,?,?,?,?,?,?)}";

            addAccionSeg = connection.prepareCall(sql);
            
            addAccionSeg.setString(1, nB.getIdEvento());
            addAccionSeg.setString(2, getCorrInc());
            addAccionSeg.setInt(1, getEstado());
            addAccionSeg.setInt(4, nB.getIdCont());
            addAccionSeg.setString(2, getDescripcion());
            addAccionSeg.setString(3, getResponsable());
            addAccionSeg.setString(4, fecha);
            addAccionSeg.setString(5, horaDuracion);
            addAccionSeg.setString(6, institucion);
            addAccionSeg.execute();

            /*
             PreparedStatement addAccionSeg = connection.prepareStatement("INSERT INTO ACCIONES(IDEV, CORRINC, IDESTADO, IDCONT, DESCACC, RESPCOORDACC, FECHORAREALACC, DURACACC, ESTADOACTACC, FECHORAALMACC, ESTADOACC)"
             + "VALUES('TM1201','000001', ?, 1, ?, ?, ?, ?, '', '2012/08/03', 'A')");
             //addAccionSeg.setInt( , );
             //addAccionSeg.setInt( , );
             addAccionSeg.setInt(1, getEstado());
             //addAccionSeg.setInt( , );
             addAccionSeg.setString(2, getDescripcion());
             addAccionSeg.setString(3, getResponsable());
             addAccionSeg.setDate(4, utilToSql(getFechaHora()));
             addAccionSeg.setDate(5, utilToSql(getDuracion()));
             //addAccionSeg.setString(10, fecha2);
             //addAccionSeg.setString(11, ());

             addAccionSeg.executeQuery();
             */

        } finally {
            connection.close();
        }
        return "seguimientoycontrol";
    }
}
