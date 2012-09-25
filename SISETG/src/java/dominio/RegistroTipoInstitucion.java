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
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.sql.DataSource;

/**
 *
 * @author DarkMaster
 */
@ManagedBean
@ViewScoped
public class RegistroTipoInstitucion {
    
    @Resource(name = "jdbc/sise")
    DataSource dataSource;
    private String NombreTpInstitucion;
    private String DescTpInstitucion;
    
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
    public String guardarTpInst() throws SQLException{
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        CallableStatement tpinst;
        try{
            String sql="{ call TIPOINSTITUCION_Add(?,?)}";
            tpinst = connection.prepareCall(sql);
                tpinst.setString(1, getNombreTpInstitucion());
                tpinst.setString(2, getDescTpInstitucion());
                
                tpinst.execute();
        }
        finally{
            connection.close();
        }
        return "pruebaRegistroInstitucion.xhtml";
    }
    
}
