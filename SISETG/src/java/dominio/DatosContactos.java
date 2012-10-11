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
   private String IdDepartamento;
   private String NombDepartamento;
   private String IdMunicipio;
   private String NombMunicipio;
   private String IdCanton;
   private String NombCanton;
   private Integer IdInst;
   private String NombInst;
   private Integer IdTpInst;
   private String NombTpInst;
   
   public DatosContactos(Integer IdCont, String NombCont, String ApellCont, String TelCont, String CelCont, String MailInstCont,
           String DirCont, String CargoCont, String TelInstCont, String FaxCont, String MailPerCon, String RadioCont,
           String IdDepartamento, String NombDepartamento, String IdMunicipio, String NombMunicipio, String IdCanton, 
           String NombCanton, Integer IdInst, String NombInst, Integer IdTpInst, String NombTpInst){
   
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
       this.IdDepartamento = IdDepartamento;
       this.NombDepartamento = NombDepartamento;
       this.IdMunicipio = IdMunicipio;
       this.NombMunicipio = NombMunicipio;
       this.IdCanton = IdCanton;
       this.NombCanton = NombCanton;
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

    public String getIdCanton() {
        return IdCanton;
    }

    public void setIdCanton(String IdCanton) {
        this.IdCanton = IdCanton;
    }

    public String getIdDepartamento() {
        return IdDepartamento;
    }

    public void setIdDepartamento(String IdDepartamento) {
        this.IdDepartamento = IdDepartamento;
    }

    public String getIdMunicipio() {
        return IdMunicipio;
    }

    public void setIdMunicipio(String IdMunicipio) {
        this.IdMunicipio = IdMunicipio;
    }

    public String getNombCanton() {
        return NombCanton;
    }

    public void setNombCanton(String NombCanton) {
        this.NombCanton = NombCanton;
    }

    public String getNombDepartamento() {
        return NombDepartamento;
    }

    public void setNombDepartamento(String NombDepartamento) {
        this.NombDepartamento = NombDepartamento;
    }

    public String getNombMunicipio() {
        return NombMunicipio;
    }

    public void setNombMunicipio(String NombMunicipio) {
        this.NombMunicipio = NombMunicipio;
    }
   
}
