/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;

public class DatosContactos implements Serializable{
   private Integer IdCont;
   private String NombCont;
   private String ApellCont;
   private String TelCont;
   private String CelCont;
   private String MailInstCont;
   private String DirCont;
   private String CargoCont;
   private String TelInstCont;
   private String FaxCont;
   private String MailPerCon;
   private String RadioCont;
   private String IdUbic;
   private String NombUbic;
   private Integer IdInst;
   private String NombInst;
   private Integer IdTpInst;
   private String NombTpInst;
   
   public DatosContactos(Integer IdCont, String NombCont, String ApellCont, String TelCont, String CelCont, String MailInstCont,
           String DirCont, String CargoCont, String TelInstCont, String FaxCont, String MailPerCon, String RadioCont,
           String IdUbic, String NombUbic, Integer IdInst, String NombInst, Integer IdTpInst, String NombTpInst){
   
       this.IdCont = IdCont;
       this.NombCont = NombCont;
       this.ApellCont = ApellCont;
       this.TelCont = TelCont;
       this.CelCont = CelCont;
       this.MailInstCont = MailInstCont;
       this.DirCont = DirCont;
       this.CargoCont = CargoCont;
       this.TelInstCont = TelInstCont;
       this.FaxCont = FaxCont;
       this.MailPerCon = MailPerCon;
       this.RadioCont = RadioCont;
       this.IdUbic = IdUbic;
       this.NombUbic = NombUbic;
       this.IdInst =IdInst;
       this.NombInst = NombInst;
       this.IdTpInst = IdTpInst;
       this.NombTpInst = NombTpInst;
   }

    public String getApellCont() {
        return ApellCont;
    }

    public void setApellCont(String ApellCont) {
        this.ApellCont = ApellCont;
    }

    public String getCargoCont() {
        return CargoCont;
    }

    public void setCargoCont(String CargoCont) {
        this.CargoCont = CargoCont;
    }

    public String getCelCont() {
        return CelCont;
    }

    public void setCelCont(String CelCont) {
        this.CelCont = CelCont;
    }

    public String getDirCont() {
        return DirCont;
    }

    public void setDirCont(String DirCont) {
        this.DirCont = DirCont;
    }

    public String getFaxCont() {
        return FaxCont;
    }

    public void setFaxCont(String FaxCont) {
        this.FaxCont = FaxCont;
    }

    public Integer getIdCont() {
        return IdCont;
    }

    public void setIdCont(Integer IdCont) {
        this.IdCont = IdCont;
    }

    public Integer getIdInst() {
        return IdInst;
    }

    public void setIdInst(Integer IdInst) {
        this.IdInst = IdInst;
    }

    public Integer getIdTpInst() {
        return IdTpInst;
    }

    public void setIdTpInst(Integer IdTpInst) {
        this.IdTpInst = IdTpInst;
    }

    public String getIdUbic() {
        return IdUbic;
    }

    public void setIdUbic(String IdUbic) {
        this.IdUbic = IdUbic;
    }

    public String getMailInstCont() {
        return MailInstCont;
    }

    public void setMailInstCont(String MailInstCont) {
        this.MailInstCont = MailInstCont;
    }

    public String getMailPerCon() {
        return MailPerCon;
    }

    public void setMailPerCon(String MailPerCon) {
        this.MailPerCon = MailPerCon;
    }

    public String getNombCont() {
        return NombCont;
    }

    public void setNombCont(String NombCont) {
        this.NombCont = NombCont;
    }

    public String getNombInst() {
        return NombInst;
    }

    public void setNombInst(String NombInst) {
        this.NombInst = NombInst;
    }

    public String getNombTpInst() {
        return NombTpInst;
    }

    public void setNombTpInst(String NombTpInst) {
        this.NombTpInst = NombTpInst;
    }

    public String getNombUbic() {
        return NombUbic;
    }

    public void setNombUbic(String NombUbic) {
        this.NombUbic = NombUbic;
    }

    public String getRadioCont() {
        return RadioCont;
    }

    public void setRadioCont(String RadioCont) {
        this.RadioCont = RadioCont;
    }

    public String getTelCont() {
        return TelCont;
    }

    public void setTelCont(String TelCont) {
        this.TelCont = TelCont;
    }

    public String getTelInstCont() {
        return TelInstCont;
    }

    public void setTelInstCont(String TelInstCont) {
        this.TelInstCont = TelInstCont;
    }
   
}
