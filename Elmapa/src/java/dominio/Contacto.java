/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

public class Contacto {

    private int IdContacto;
    private String NombreContacto;
    private String ApellidoContacto;
    private String TelContacto;
    private String CelContacto;
    private String MailInstContacto;
    private String DirContacto;
    private String CargoContacto;
    private String TelInstContacto;
    private String FaxContacto;
    private String MailPersContacto;
    private String RadioContacto;

    public Contacto(int IdContacto, String NombreContacto, String ApellidoContacto, String TelContacto, String CelContacto, String MailInstContacto, 
            String DirContacto, String CargoContacto, String TelInstContacto, String FaxContacto, String MailPersContacto, String RadioContacto) {
        this.IdContacto = IdContacto;
        this.NombreContacto = NombreContacto;
        this.ApellidoContacto = ApellidoContacto;
        this.TelContacto = TelContacto;
        this.CelContacto = CelContacto;
        this.MailInstContacto = MailInstContacto;
        this.DirContacto = DirContacto;
        this.CargoContacto = CargoContacto;
        this.TelInstContacto = TelInstContacto;
        this.FaxContacto = FaxContacto;
        this.MailPersContacto = MailPersContacto;
        this.RadioContacto = RadioContacto;
    }
    public int getIdContacto() {
        return IdContacto;
    }

    public void setIdContacto(int IdContacto) {
        this.IdContacto = IdContacto;
    }
    public String getNombreContacto() {
        return NombreContacto;
    }

    public void setNombreContacto(String NombreContacto) {
        this.NombreContacto = NombreContacto;
    }
    public String getApellidoContacto() {
        return ApellidoContacto;
    }

    public void setApellidoContacto(String ApellidoContacto) {
        this.ApellidoContacto = ApellidoContacto;
    }
    public String getTelContacto() {
        return TelContacto;
    }

    public void setTelContacto(String TelContacto) {
        this.TelContacto = TelContacto;
    }
    public String getCelContacto() {
        return CelContacto;
    }

    public void setCelContacto(String CelContacto) {
        this.CelContacto = CelContacto;
    }
    public String getMailInstContacto() {
        return MailInstContacto;
    }

    public void setMailInstContacto(String MailInstContacto) {
        this.MailInstContacto = MailInstContacto;
    }
    public String getDirContacto() {
        return DirContacto;
    }

    public void setDirContacto(String DirContacto) {
        this.DirContacto = DirContacto;
    }
    public String getCargoContacto() {
        return CargoContacto;
    }

    public void setCargoContacto(String CargoContacto) {
        this.CargoContacto = CargoContacto;
    }
    public String getTelInstContacto() {
        return TelInstContacto;
    }

    public void setTelInstContacto(String TelInstContacto) {
        this.TelInstContacto = TelInstContacto;
    }
    public String getFaxContacto() {
        return FaxContacto;
    }

    public void setFaxContacto(String FaxContacto) {
        this.FaxContacto = FaxContacto;
    }
    public String getMailPersContacto() {
        return MailPersContacto;
    }

    public void setMailPersContacto(String MailPersContacto) {
        this.MailPersContacto = MailPersContacto;
    }
    public String getRadioContacto() {
        return RadioContacto;
    }

    public void setRadioContacto(String RadioContacto) {
        this.RadioContacto = RadioContacto;
    }

}
