
package dominio;

public class TipoEvento {

    private String IdTpEvento;
    private String NombreTpEvento;
    private String DescripTpEvento;
    public TipoEvento(String IdTpEvento, String NombreTpEvento, String DescripTpEvento) {
        this.IdTpEvento = IdTpEvento;
        this.NombreTpEvento = NombreTpEvento;
        this.DescripTpEvento = DescripTpEvento;
    }

    public String getDescripTpEvento() {
        return DescripTpEvento;
    }

    public void setDescripTpEvento(String DescripTpEvento) {
        this.DescripTpEvento = DescripTpEvento;
    }

    public String getIdTpEvento() {
        return IdTpEvento;
    }

    public void setIdTpEvento(String IdTpEvento) {
        this.IdTpEvento = IdTpEvento;
    }

    public String getNombreTpEvento() {
        return NombreTpEvento;
    }

    public void setNombreTpEvento(String NombreTpEvento) {
        this.NombreTpEvento = NombreTpEvento;
    }
    
}
