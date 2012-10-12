package dominio;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class TipoAmenaza {
    private int idTipoAmenaza;
    private String nombTipoAmenaza;

    public TipoAmenaza(int idTipoAmenaza, String nombTipoAmenaza) {
        this.idTipoAmenaza = idTipoAmenaza;
        this.nombTipoAmenaza = nombTipoAmenaza;
    }

    public int getIdTipoAmenaza() {
        return idTipoAmenaza;
    }

    public void setIdTipoAmenaza(int idTipoAmenaza) {
        this.idTipoAmenaza = idTipoAmenaza;
    }

    public String getNombTipoAmenaza() {
        return nombTipoAmenaza;
    }

    public void setNombTipoAmenaza(String nombTipoAmenaza) {
        this.nombTipoAmenaza = nombTipoAmenaza;
    }
    
}
