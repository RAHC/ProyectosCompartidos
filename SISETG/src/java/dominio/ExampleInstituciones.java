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
import org.primefaces.model.DualListModel;

@ManagedBean
@ViewScoped
public class ExampleInstituciones {

    private DualListModel<Institucion> Instituciones;
    @Resource(name = "jdbc/sise")
    DataSource dataSource;

    public ExampleInstituciones(){
    }

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
}
