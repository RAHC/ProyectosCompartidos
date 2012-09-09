package Mapa;

import dominio.Departamento;
import dominio.Municipio;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;

@ManagedBean
@ViewScoped
public class DatosMapa implements Serializable{

    private String CodDepartamento;
    private String CodMunicipio;
    private String CodCanton;
    private String CodCaserio;
    private Float lat;
    private Float lng;

    @Resource(name = "jdbc/sise")
    DataSource dataSource;

    public DatosMapa() {
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

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
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
    
    public Float getLatitud() throws SQLException {
        Float Latitud;
        if (CodDepartamento == null) {
            Latitud = (float) 13.70;
        } else {
            String id = null;
            if (CodCaserio !=null) {
                id = CodCaserio;
            } else if (CodCanton !=null) {
                id = CodCanton;
            } else if (CodMunicipio !=null) {
                id = CodMunicipio;
            } else if (CodDepartamento != null) {
                id = CodDepartamento;
            }
            if (dataSource == null) {
                throw new SQLException("No se pudo tener acceso a la fuente de datos");
            }
            Connection connection = dataSource.getConnection();
            if (connection == null) {
                throw new SQLException("No se pudo conectar a la fuente de datos");
            }
            PreparedStatement getLatitudes = connection.prepareStatement(
                    "SELECT LATITUDUBIC FROM UBICACION WHERE IDUBIC='" + id + "'");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getLatitudes.executeQuery());
            rowSet.next();
            Latitud = Float.parseFloat(rowSet.getString("LATITUDUBIC"));

            connection.close();
        }
        return Latitud;
    }

    public Float getLongitud() throws SQLException{
        Float Longitud;
        if (CodDepartamento == null) {
            Longitud = (float) -88.91;
        } else {
            String id = null;
            if (CodCaserio != null) {
                id = CodCaserio;
            } else if (CodCanton != null) {
                id = CodCanton;
            } else if (CodMunicipio != null) {
                id = CodMunicipio;
            } else if (CodDepartamento !=null) {
                id = CodDepartamento;
            }
            if (dataSource == null) {
                throw new SQLException("No se pudo tener acceso a la fuente de datos");
            }
            Connection connection = dataSource.getConnection();
            if (connection == null) {
                throw new SQLException("No se pudo conectar a la fuente de datos");
            }
            PreparedStatement getLongitudes = connection.prepareStatement(
                    "SELECT LONGITUDUBIC FROM UBICACION WHERE IDUBIC='" + id + "'");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getLongitudes.executeQuery());
            rowSet.next();
            Longitud = Float.parseFloat(rowSet.getString("LONGITUDUBIC"));
            connection.close();
        }
        return Longitud;
    }
    public Integer getZoomUbic(){
        Integer z;
        if (CodCaserio != null) {
                z = 15;
            } else if (CodCanton != null) {
                z = 13;
            } else if (CodMunicipio != null) {
                z = 12;
            } else if (CodDepartamento !=null) {
                z = 10;
            }
            else{
                z = 8;
            }
        return z;
    }
    public void addMarker(ActionEvent actionEvent){
        Marker marker = new Marker(new LatLng(lat, lng));
        /*emptyModel.addOverlay(marker);
		
		addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
                */
   }
}
