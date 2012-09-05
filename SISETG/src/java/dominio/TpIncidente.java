
package dominio;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class TpIncidente {
    private String IdTpIncidente;
    private String NombreTpIncidente;
    private String DescTpIncidente;
    
    public TpIncidente(String IdTpIncidente, String NombreTpIncidente, String DescTpIncidente) {
        this.IdTpIncidente = IdTpIncidente;
        this.NombreTpIncidente = NombreTpIncidente;
        this.DescTpIncidente = DescTpIncidente;
    }

    public String getIdTpIncidente() {
        return IdTpIncidente;
    }

    public void setIdTpIncidente(String IdTpIncidente) {
        this.IdTpIncidente = IdTpIncidente;
    }

    public String getNombreTpIncidente() {
        return NombreTpIncidente;
    }

    public void setNombreTpIncidente(String NombreTpIncidente) {
        this.NombreTpIncidente = NombreTpIncidente;
    }

    public String getDescTpIncidente() {
        return DescTpIncidente;
    }

    public void setDescTpIncidente(String DescTpIncidente) {
        this.DescTpIncidente = DescTpIncidente;
    }
    
}
