package Mapa;

import dominio.Departamento;
import dominio.Municipio;

import dominio.init;

import dominio.Canton;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
@ViewScoped
public class DatosMapa implements Serializable {

    private String CodDepartamento;
    private String CodMunicipio;
    private String CodCanton;
    private String CodCaserio;
    private double lat;
    private double lng;
    private double altu;
    private MapModel draggableModel;
    private String title;
    Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
    init in = (init) viewMap.get("init");
    @Resource(name = "jdbc/sise")
    DataSource dataSource;

    public DatosMapa() {
        draggableModel = new DefaultMapModel();
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addMarker(ActionEvent actionEvent) {
        Marker marker = new Marker(new LatLng(lat, lng), title);
        marker.setDraggable(false);
        draggableModel.addOverlay(marker);
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca agregada", "Lat:" + lat + ", Lng:" + lng + ", \nAltura: " + altu + " Metros Sobre el Nivel del Mar"));
        for (Marker marker1 : draggableModel.getMarkers()) {
            marker1.setDraggable(false);
        }


    }

    public void onMarkerDrag(MarkerDragEvent event) {
        Marker marker = event.getMarker();
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca trasladada", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng() + ", \nAltura: "+altu+" Metros"));
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        Marker marker = (Marker) event.getOverlay();

        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca Seleccionada", marker.getTitle()));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MapModel getDraggableModel() {
        return draggableModel;
    }

    public void setDraggableModel(MapModel draggableModel) {
        this.draggableModel = draggableModel;
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
    public double getAltu() {
        return altu;
    }

    public void setAltu(double altu) {
        this.altu = altu;
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
            String query = "SELECT IDUBIC, NOMBUBIC, LATITUDUBIC, LONGITUDUBIC FROM UBICACION WHERE IDUBIC_PADRE = '" + CodMunicipio + "' order by NOMBUBIC";
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

    public Double getLatitud() throws SQLException {
        Double Latitud;

        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        if (in.getCodDepartamento() == null) {
            Latitud = 13.70;
        } else {
            String id = null;
            if (in.getCodCaserio() != null) {
                id = in.getCodCaserio();
            } else if (in.getCodCanton() != null) {
                id = in.getCodCanton();
            } else if (in.getCodMunicipio() != null) {
                id = in.getCodMunicipio();
            } else if (in.getCodDepartamento() != null) {
                id = in.getCodDepartamento();
            }

            PreparedStatement getLatitudes = connection.prepareStatement(
                    "SELECT LATITUDUBIC FROM UBICACION WHERE IDUBIC='" + id + "'");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getLatitudes.executeQuery());
            rowSet.next();
            Latitud = Double.parseDouble(rowSet.getString("LATITUDUBIC"));

            connection.close();
        }
        return Latitud;
    }

    public Double getLongitud() throws SQLException {
        Double Longitud;

        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        if (in.getCodDepartamento() == null) {
            Longitud = 13.70;
        } else {
            String id = null;
            if (in.getCodCaserio() != null) {
                id = in.getCodCaserio();
            } else if (in.getCodCanton() != null) {
                id = in.getCodCanton();
            } else if (in.getCodMunicipio() != null) {
                id = in.getCodMunicipio();
            } else if (in.getCodDepartamento() != null) {
                id = in.getCodDepartamento();
            }

            PreparedStatement getLongitudes = connection.prepareStatement(
                    "SELECT LONGITUDUBIC FROM UBICACION WHERE IDUBIC='" + id + "'");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getLongitudes.executeQuery());
            rowSet.next();
            Longitud = Double.parseDouble(rowSet.getString("LONGITUDUBIC"));
            connection.close();
        }
        return Longitud;
    }

    public Integer getZoomUbic() {
        Integer z;
        if (in.getCodCaserio() != null) {
            z = 15;
        } else if (in.getCodCanton() != null) {
            z = 13;
        } else if (in.getCodMunicipio() != null) {
            z = 12;
        } else if (in.getCodDepartamento() != null) {
            z = 10;
        } else {
            z = 8;
        }
        return z;
    }
}
