/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

public class Estado {

    private int IdEstado;
    private String nombreEstado;
    private String colorEstado;
    private String imagenEstado;

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

    public String getImagenEstado() {
        if("AZUL".equals(colorEstado)){
            imagenEstado = "/imagen/cuadroAzul.png";
        }
        if("ROJO".equals(colorEstado)){
            imagenEstado = "/imagen/cuadroRojo.png";
        }
        if("NEGRO".equals(colorEstado)){
            imagenEstado = "/imagen/cuadroNegro.png";
        }
        if("AMARILLO".equals(colorEstado)){
            imagenEstado = "/imagen/cuadroAmarillo.png";
        }
        if("NARANJA".equals(colorEstado)){
            imagenEstado = "/imagen/cuadroNaranja.png";
        }
        if("VERDE".equals(colorEstado)){
            imagenEstado = "/imagen/cuadroVerde.png";
        }
        if("GRIS".equals(colorEstado)){
            imagenEstado = "/imagen/cuadroGris.png";
        }
        if("CAFE".equals(colorEstado)){
            imagenEstado = "/imagen/cuadroCafe.png";
        }

        return imagenEstado;
    }

    public void setImagenEstado(String imagenEstado) {
        this.imagenEstado = imagenEstado;
    }
    
}
