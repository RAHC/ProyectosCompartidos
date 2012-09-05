
public class EstadoColor {

    private int IdEstado;
    private String NombreEstado;
    private String ColorEstado;
    
    public EstadoColor(int IdEstado, String NombreEstado, String ColorEstado) {
        this.IdEstado = IdEstado;
        this.NombreEstado = NombreEstado;
        this.ColorEstado = ColorEstado;
    }

    public int getIdEstado() {
        return IdEstado;
    }

    public void setIdEstado(int IdEstado) {
        this.IdEstado = IdEstado;
    }

    public String getColorEstado() {
        return ColorEstado;
    }

    public void setColorEstado(String ColorEstado) {
        this.ColorEstado = ColorEstado;
    }

    public String getNombreEstado() {
        return NombreEstado;
    }

    public void setNombreEstado(String NombreEstado) {
        this.NombreEstado = NombreEstado;
    }
    
}
