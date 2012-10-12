package dominio;

/**
 *
 * @author J@RG
 */

import java.util.Date;
import java.lang.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
            
            String institucion =new String(); 
            
            Iterator<Institucion> inst = getInstituciones().getTarget().iterator();
            while(inst.hasNext()) {
	            //System.out.println(inst.next());
                    institucion = institucion + inst.next()+",";
                    
	        }
            
            //System.out.println("valor :"+institucion);
                 
            CallableStatement addAccionSeg;
            String sql = "{ call SEGUIMIENTOYCONTROL_ADD('TT1201','0000001',?,1,?,?,?,?,?)}";
            
            addAccionSeg = connection.prepareCall(sql);
            
            addAccionSeg.setInt(1, getEstado());
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
