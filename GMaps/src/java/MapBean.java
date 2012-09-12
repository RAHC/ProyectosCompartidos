/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javax.faces.bean.ManagedBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Familia Molina
 */
@ManagedBean
@SessionScoped
public class MapBean implements Serializable {

    private MapModel draggableModel;
    private String title;
    private double lat;
    private double lng;
    private String Saludo="Hola!!!";

    public String getSaludo() {
        return Saludo;
    }

    public void setSaludo(String Saludo) {
        this.Saludo = Saludo;
    }
    
    

    public MapBean() {
        draggableModel = new DefaultMapModel();
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addMarker(ActionEvent actionEvent) {
        Marker marker = new Marker(new LatLng(lat, lng), title);
        marker.setDraggable(true);
        draggableModel.addOverlay(marker);
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca agregada", "Lat:" + lat + ", Lng:" + lng));
        for(Marker marker1 : draggableModel.getMarkers()) {  
            marker1.setDraggable(true);  
        } 
       
        
    }
    

    public void onMarkerDrag(MarkerDragEvent event) {
        Marker marker = event.getMarker();  
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca trasladada", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        Marker marker = (Marker) event.getOverlay();
        
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca Seleccionada", marker.getTitle()));
    }
    
    /*public void onPointSelect(PointSelectEvent event) {  
        LatLng latlng = event.getLatLng();  
          
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Point Selected", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng()));  
    }  */

    public MapModel getDraggableModel() {
        return draggableModel;
    }

    public void setDraggableModel(MapModel draggableModel) {
        this.draggableModel = draggableModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
