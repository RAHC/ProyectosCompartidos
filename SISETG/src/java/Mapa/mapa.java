/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;

import dominio.Canton;
import dominio.Departamento;
import dominio.Municipio;
import dominio.Ubicacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;
import org.primefaces.model.map.Polyline;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author DarkMaster
 */
@ManagedBean
@ViewScoped
public class mapa {
    
    private String CodDepartamento;
    private String CodMunicipio;
    private double Longitud;
    private double Latitud;
    
    @Resource(name = "jdbc/sise")
    DataSource dataSource;
    private String Municipio;

    public mapa() {
    }
    
     public String getCodDepartamento() {
        return CodDepartamento;
    }

    public void setCodDepartamento(String CodDepartamento) {
        this.CodDepartamento = CodDepartamento;
    }

    public double getLongitud() {
        return Longitud;
    }

    public void setLongitud(float Longitud) {
        this.Longitud = Longitud;
    }
    
        public double getLatitud() {
        return Latitud;
    }

    public void setLatitud(float Latitud) {
        this.Latitud = Latitud;
    }
    
    public String getCodMunicipio() {
        return CodMunicipio;
    }

    public void setCodMunicipio(String CodMunicipio) {
        this.CodMunicipio = CodMunicipio;
    }

         public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String Municipio) {
        this.Municipio = Municipio;
    }

     
    /****** Para sacar las coordenadas*/
    public double obtenerLat(String CodDepartamento) throws SQLException{
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            PreparedStatement obtenerLat = connection.prepareStatement(
                    "SELECT LATITUD FROM UBICACION WHERE IDUBIC_PADRE = '" + CodMunicipio+"'");
            ResultSet lati = obtenerLat.executeQuery();
            Latitud = lati.getDouble("LATITUD");
            FacesContext context = FacesContext.getCurrentInstance();  
          
        context.addMessage(null, new FacesMessage("Successful", "Hello " + Latitud));  
            return Latitud;
        } finally {
            connection.close();
        }
        //return 0;
}
    
    public double obtenerLng(String CodDepartamento) throws SQLException{
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            PreparedStatement obtenerLng = connection.prepareStatement(
                    "SELECT LONGITUD FROM UBICACION WHERE IDUBIC_PADRE = '" + CodMunicipio+"'");
            ResultSet longi = obtenerLng.executeQuery();
            Longitud = longi.getDouble("LONGITUD");
            
            return Longitud;
        } finally {
            connection.close();
        }
        //return 0;
}
   /**** fin de coordenadas*/ 
    
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
                    "SELECT IDUBIC, NOMBUBIC FROM UBICACION WHERE IDUBIC_PADRE is NULL order by NOMBUBIC");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getDepartamento.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Departamento(rowSet.getString("IDUBIC"),
                        rowSet.getString("NOMBUBIC")));
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
            /*obtenerLng(CodDepartamento);
            obtenerLat(CodDepartamento);*/
            String query = "SELECT IDUBIC, NOMBUBIC FROM UBICACION WHERE IDUBIC_PADRE = '" + CodDepartamento + "' order by NOMBUBIC";
            PreparedStatement getUbicacion = connection.prepareStatement(query);
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getUbicacion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new Municipio(rowSet.getString("IDUBIC"),
                        rowSet.getString("NOMBUBIC")));
            }
            obtenerLat(CodDepartamento);
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
    

 
    public Map<String, String> getListarMunicipios(String CodigoDep) throws SQLException{
        Map<String, String> mapa = new HashMap();
        ArrayList<Ubicacion> lista = new ArrayList<Ubicacion>();
        
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
         try {
            String query = "SELECT IDUBIC, NOMBUBIC FROM UBICACION WHERE IDUBIC_PADRE = '" + CodigoDep + "' order by NOMBUBIC";
            PreparedStatement getUbicacion = connection.prepareStatement(query);
            //JOptionPane.showMessageDialog(null,query);
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getUbicacion.executeQuery());
            while (rowSet.next()) {
                lista.add(new Ubicacion(rowSet.getString("IDUBIC"),
                        rowSet.getString("NOMBUBIC")));
            }
            
            //JOptionPane.showMessageDialog(null,resultados.size());
                for (Ubicacion object : lista){
                    //Map map = (Map) rowSet;
                   // mapa.put(map.get("NOMBUBIC").toString(), map.get("IDUBIC").toString());
                   Map map = (Map) object;
                   mapa.put(map.get("nombreUbicacion").toString(), map.get("idUbicacion").toString());
                }
                return mapa;
        } finally {
            connection.close();
        }
    }

  
}
