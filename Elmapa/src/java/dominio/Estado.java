/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author Familia Molina
 */
public class Estado {

    private int IdEstado;
    private String nombreEstado;
    private String colorEstado;

    public Estado(int IdEstado, String nombreEstado, String colorEstado) {
        this.IdEstado = IdEstado;
        this.nombreEstado = nombreEstado;
        this.colorEstado = colorEstado;
    }

    public int getIdEstado() {
        return IdEstado;
    }

    public void setIdEstado(int IdEstado) {
        this.IdEstado = IdEstado;
    }

    public String getColorEstado() {
        return colorEstado;
    }

    public void setColorEstado(String colorEstado) {
        this.colorEstado = colorEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
}
