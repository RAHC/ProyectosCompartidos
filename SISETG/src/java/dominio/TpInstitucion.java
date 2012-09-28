/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.sql.DataSource;

/**
 *
 * @author Administrador
 */
@ManagedBean
@RequestScoped
public class TpInstitucion {

    private int IdTpInstitucion;
    private String NombreTpInstitucion;
    private String DescTpInstitucion;
    
    public TpInstitucion(int IdTpInstitucion, String NombreTpInstitucion, String DescTpInstitucion){
        this.IdTpInstitucion = IdTpInstitucion;
        this.NombreTpInstitucion = NombreTpInstitucion;
        this.DescTpInstitucion = DescTpInstitucion;
    }
        
    public int getIdTpInstitucion() {
        return IdTpInstitucion;
    }

    public void setIdTpInstitucion(int IdTpInstitucion) {
        this.IdTpInstitucion = IdTpInstitucion;
    }
    public String getNombreTpInstitucion() {
        return NombreTpInstitucion;
    }

    public void setNombreTpInstitucion(String NombreTpInstitucion) {
        this.NombreTpInstitucion = NombreTpInstitucion;
    }
    public String getDescTpInstitucion() {
        return DescTpInstitucion;
    }

    public void setDescTpInstitucion(String DescTpInstitucion) {
        this.DescTpInstitucion = DescTpInstitucion;
    }
}
