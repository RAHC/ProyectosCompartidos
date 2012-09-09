/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;


import dominio.Departamento;
import dominio.Municipio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author DarkMaster
 */
@ManagedBean
@ViewScoped
public class mapa {
    
    private String CodDepartamento;
    private String CodMunicipio;
    private Double Longitud;
    private Double Latitud;
    
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

    public Double getLongitud() {
        return Longitud;
    }

    public void setLongitud(Double Longitud) {
        this.Longitud = Longitud;
    }
    
        public Double getLatitud() {
        return Latitud;
    }

    public void setLatitud(Double Latitud) {
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
    public Double obtenerLat(String Seleccion) throws SQLException{
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            PreparedStatement obtenerLat = connection.prepareStatement(
                    "SELECT LATITUD FROM UBICACION WHERE IDUBIC = '" + Seleccion+"'");
            ResultSet lati = obtenerLat.executeQuery();
            Latitud = lati.getDouble("LATITUD");
            
            return Latitud;
        } finally {
            connection.close();
        }
        //return 0;
}
    
    public Double obtenerLng(String Seleccion) throws SQLException{
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            PreparedStatement obtenerLng = connection.prepareStatement(
                    "SELECT LONGITUD FROM UBICACION WHERE IDUBIC = '" + Seleccion+"'");
            ResultSet longi = obtenerLng.executeQuery();
            Longitud = longi.getDouble("LONGITUD");
            
            return Longitud;
        } finally {
            connection.close();
        }
        //return 0;
}
   /**** fin de coordenadas*/ 
    
}
