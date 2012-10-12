package dominio;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Administrador
 */
@ManagedBean
//@RequestScoped
@ViewScoped
public class AlbergueBean {

    @Resource(name = "jdbc/sise")
    DataSource dataSource;
    private String nombreAlb;
    private String tpAlbergue;
    private int idEntiResp;
    private float areaTotalMts;
    private float areaTotalTech;
    private int capacidadPers;
    private int idTpCondAcc;
    private int hayAcceAdecuado;
    private String ExpAccAdecuado;
    private int servEnerElect;
    private int servAguaPot;
    private int numSanitario;
    private int numDucha;
    private int bodegaEmer;
    private int areaCocinar;
    private int servTrenAseo;
    private int frecuenciaTren;
    private int espacioRecreacion;
    private int ventilacionNat;
    private int iluminacionNat;
    private int equipoNecesarioEmer;
    private int salidaEvacuar;
    private int areaTechadaIlum;
    private int iluminacionNocturna;
    private int muroProteccion;
    private int espaciosPeligrosos;
    private String observacion;
    private Date fechaEva;
    private String nombLleno;
    private String telLleno;
    private String nombccmp;
    private String telccmp;
    private String nombcmpc;
    private String telcmpc;
    private DualListModel<Servicio> Servicios;
    private DualListModel<OtroServicioAgua> OtroServicioAguas;
    private DualListModel<TpSanitario> Sanitarios;
    private DualListModel<TipoAmenaza> Amenazas;
    private String ptoReferencia;
    private String Direccion;
    private double lat;
    private double lng;
    

    public DualListModel<Servicio> getServicios() throws SQLException {
        if (Servicios == null) {
            List<Servicio> serviciosSource = new ArrayList<Servicio>();
            List<Servicio> serviciosTarget = new ArrayList<Servicio>();

            if (dataSource == null) {
                throw new SQLException("No se pudo tener acceso a la fuente de datos");
            }
            Connection connection = dataSource.getConnection();

            if (connection == null) {
                throw new SQLException("No se pudo conectar a la fuente de datos");
            }

            PreparedStatement getServicio = connection.prepareStatement(
                    "SELECT IDSERV, NOMBSERV FROM SERVICIO ");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getServicio.executeQuery());

            while (rowSet.next()) {
                serviciosSource.add(new Servicio(rowSet.getInt("IDSERV"),
                        rowSet.getString("NOMBSERV")));
            }
            Servicios = new DualListModel<Servicio>(serviciosSource, serviciosTarget);

            connection.close();
        }
        return Servicios;
    }

    public void setServicios(DualListModel<Servicio> Servicios) {
        this.Servicios = Servicios;
    }

    public DualListModel<OtroServicioAgua> getOtroServicioAguas() throws SQLException {
        if (Sanitarios == null) {
            List<OtroServicioAgua> otroServicioAguasSource = new ArrayList<OtroServicioAgua>();
            List<OtroServicioAgua> otroServicioAguasTarget = new ArrayList<OtroServicioAgua>();

            if (dataSource == null) {
                throw new SQLException("No se pudo tener acceso a la fuente de datos");
            }
            Connection connection = dataSource.getConnection();

            if (connection == null) {
                throw new SQLException("No se pudo conectar a la fuente de datos");
            }

            PreparedStatement getOtroServicioAgua = connection.prepareStatement(
                    "SELECT IDOTROSERVAGUA, NOMBOTROSERVICIOAGUA FROM OTROSERVICIOAGUA ");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getOtroServicioAgua.executeQuery());

            while (rowSet.next()) {
                otroServicioAguasSource.add(new OtroServicioAgua(rowSet.getInt("IDOTROSERVAGUA"),
                        rowSet.getString("NOMBOTROSERVICIOAGUA")));
            }
            OtroServicioAguas = new DualListModel<OtroServicioAgua>(otroServicioAguasSource, otroServicioAguasTarget);

            connection.close();
        }
        return OtroServicioAguas;
    }

    public void setOtroServicioAguas(DualListModel<OtroServicioAgua> OtroServicioAguas) {
        this.OtroServicioAguas = OtroServicioAguas;
    }

    public DualListModel<TpSanitario> getSanitarios() throws SQLException {
        if (Sanitarios == null) {
            List<TpSanitario> sanitariosSource = new ArrayList<TpSanitario>();
            List<TpSanitario> sanitariosTarget = new ArrayList<TpSanitario>();

            if (dataSource == null) {
                throw new SQLException("No se pudo tener acceso a la fuente de datos");
            }
            Connection connection = dataSource.getConnection();

            if (connection == null) {
                throw new SQLException("No se pudo conectar a la fuente de datos");
            }

            PreparedStatement getTpSanitario = connection.prepareStatement(
                    "SELECT IDTPSANITARIO, NOMBTPSANITARIO FROM TIPOSANITARIO ");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getTpSanitario.executeQuery());

            while (rowSet.next()) {
                sanitariosSource.add(new TpSanitario(rowSet.getInt("IDTPSANITARIO"),
                        rowSet.getString("NOMBTPSANITARIO")));
            }
            Sanitarios = new DualListModel<TpSanitario>(sanitariosSource, sanitariosTarget);

            connection.close();
        }
        return Sanitarios;
    }

    public void setSanitarios(DualListModel<TpSanitario> Sanitarios) {
        this.Sanitarios = Sanitarios;
    }

    public DualListModel<TipoAmenaza> getAmenazas() throws SQLException {
        if (Amenazas == null) {
            List<TipoAmenaza> amenazasSource = new ArrayList<TipoAmenaza>();
            List<TipoAmenaza> amenazasTarget = new ArrayList<TipoAmenaza>();

            if (dataSource == null) {
                throw new SQLException("No se pudo tener acceso a la fuente de datos");
            }
            Connection connection = dataSource.getConnection();

            if (connection == null) {
                throw new SQLException("No se pudo conectar a la fuente de datos");
            }

            PreparedStatement getTipoAmenaza = connection.prepareStatement(
                    "SELECT IDTPAMENAZA, NOMBTPAMENAZA FROM TIPOAMENAZA ");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getTipoAmenaza.executeQuery());

            while (rowSet.next()) {
                amenazasSource.add(new TipoAmenaza(rowSet.getInt("IDTPAMENAZA"),
                        rowSet.getString("NOMBTPAMENAZA")));
            }
            Amenazas = new DualListModel<TipoAmenaza>(amenazasSource, amenazasTarget);

            connection.close();
        }
        return Amenazas;
    }

    public void setAmenazas(DualListModel<TipoAmenaza> Amenazas) {
        this.Amenazas = Amenazas;
    }

    public String getPtoReferencia() {
        return ptoReferencia;
    }

    public void setPtoReferencia(String ptoReferencia) {
        this.ptoReferencia = ptoReferencia;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }
    
    

    public int getSalidaEvacuar() {
        return salidaEvacuar;
    }

    public void setSalidaEvacuar(int salidaEvacuar) {
        this.salidaEvacuar = salidaEvacuar;
    }

    public int getAreaTechadaIlum() {
        return areaTechadaIlum;
    }

    public void setAreaTechadaIlum(int areaTechadaIlum) {
        this.areaTechadaIlum = areaTechadaIlum;
    }

    public int getIluminacionNocturna() {
        return iluminacionNocturna;
    }

    public void setIluminacionNocturna(int iluminacionNocturna) {
        this.iluminacionNocturna = iluminacionNocturna;
    }

    public int getMuroProteccion() {
        return muroProteccion;
    }

    public void setMuroProteccion(int muroProteccion) {
        this.muroProteccion = muroProteccion;
    }

    public int getEspaciosPeligrosos() {
        return espaciosPeligrosos;
    }

    public void setEspaciosPeligrosos(int espaciosPeligrosos) {
        this.espaciosPeligrosos = espaciosPeligrosos;
    }

    public int getBodegaEmer() {
        return bodegaEmer;
    }

    public void setBodegaEmer(int bodegaEmer) {
        this.bodegaEmer = bodegaEmer;
    }

    public int getAreaCocinar() {
        return areaCocinar;
    }

    public void setAreaCocinar(int areaCocinar) {
        this.areaCocinar = areaCocinar;
    }

    public int getServTrenAseo() {
        return servTrenAseo;
    }

    public void setServTrenAseo(int servTrenAseo) {
        this.servTrenAseo = servTrenAseo;
    }

    public int getEspacioRecreacion() {
        return espacioRecreacion;
    }

    public void setEspacioRecreacion(int espacioRecreacion) {
        this.espacioRecreacion = espacioRecreacion;
    }

    public int getVentilacionNat() {
        return ventilacionNat;
    }

    public void setVentilacionNat(int ventilacionNat) {
        this.ventilacionNat = ventilacionNat;
    }

    public int getIluminacionNat() {
        return iluminacionNat;
    }

    public void setIluminacionNat(int iluminacionNat) {
        this.iluminacionNat = iluminacionNat;
    }

    public int getEquipoNecesarioEmer() {
        return equipoNecesarioEmer;
    }

    public void setEquipoNecesarioEmer(int equipoNecesarioEmer) {
        this.equipoNecesarioEmer = equipoNecesarioEmer;
    }

    public int getServEnerElect() {
        return servEnerElect;
    }

    public void setServEnerElect(int servEnerElect) {
        this.servEnerElect = servEnerElect;
    }

    public int getServAguaPot() {
        return servAguaPot;
    }

    public void setServAguaPot(int servAguaPot) {
        this.servAguaPot = servAguaPot;
    }

    public float getAreaTotalMts() {
        return areaTotalMts;
    }

    public void setAreaTotalMts(float areaTotalMts) {
        this.areaTotalMts = areaTotalMts;
    }

    public float getAreaTotalTech() {
        return areaTotalTech;
    }

    public void setAreaTotalTech(float areaTotalTech) {
        this.areaTotalTech = areaTotalTech;
    }

    public int getCapacidadPers() {
        return capacidadPers;
    }

    public void setCapacidadPers(int capacidadPers) {
        this.capacidadPers = capacidadPers;
    }

    public int getHayAcceAdecuado() {
        return hayAcceAdecuado;
    }

    public void setHayAcceAdecuado(int hayAcceAdecuado) {
        this.hayAcceAdecuado = hayAcceAdecuado;
    }

    public String getExpAccAdecuado() {
        return ExpAccAdecuado;
    }

    public void setExpAccAdecuado(String ExpAccAdecuado) {
        this.ExpAccAdecuado = ExpAccAdecuado;
    }

    public int getIdTpCondAcc() {
        return idTpCondAcc;
    }

    public void setIdTpCondAcc(int idTpCondAcc) {
        this.idTpCondAcc = idTpCondAcc;
    }

    public int getIdEntiResp() {
        return idEntiResp;
    }

    public void setIdEntiResp(int idEntiResp) {
        this.idEntiResp = idEntiResp;
    }

    public String getNombLleno() {
        return nombLleno;
    }

    public void setNombLleno(String nombLleno) {
        this.nombLleno = nombLleno;
    }

    public String getTelLleno() {
        return telLleno;
    }

    public void setTelLleno(String telLleno) {
        this.telLleno = telLleno;
    }

    public String getNombccmp() {
        return nombccmp;
    }

    public void setNombccmp(String nombccmp) {
        this.nombccmp = nombccmp;
    }

    public String getTelccmp() {
        return telccmp;
    }

    public void setTelccmp(String telccmp) {
        this.telccmp = telccmp;
    }

    public String getNombcmpc() {
        return nombcmpc;
    }

    public void setNombcmpc(String nombcmpc) {
        this.nombcmpc = nombcmpc;
    }

    public String getTelcmpc() {
        return telcmpc;
    }

    public void setTelcmpc(String telcmpc) {
        this.telcmpc = telcmpc;
    }

    public Date getFechaEva() {
        return fechaEva;
    }

    public void setFechaEva(Date fechaEva) {
        this.fechaEva = fechaEva;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getFrecuenciaTren() {
        return frecuenciaTren;
    }

    public void setFrecuenciaTren(int frecuenciaTren) {
        this.frecuenciaTren = frecuenciaTren;
    }

    public int getNumDucha() {
        return numDucha;
    }

    public void setNumDucha(int numDucha) {
        this.numDucha = numDucha;
    }

    public int getNumSanitario() {
        return numSanitario;
    }

    public void setNumSanitario(int numSanitario) {
        this.numSanitario = numSanitario;
    }

    public String getTpAlbergue() {
        return tpAlbergue;
    }

    public void setTpAlbergue(String tpAlbergue) {
        this.tpAlbergue = tpAlbergue;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getNombreAlb() {
        return nombreAlb;
    }

    public void setNombreAlb(String nombreAlb) {
        this.nombreAlb = nombreAlb;
    }

    public String guardar() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String fecha = dateFormat.format(getFechaEva());
            /*
             DateFormat dateFormathora = new SimpleDateFormat("HH:mm");
             String horaDuracion = dateFormathora.format(getDuracion());
            
             String institucion =new String(); 
             */

            /*
             Iterator<Institucion> inst = getInstituciones().getTarget().iterator();
             while(inst.hasNext()) {
             //System.out.println(inst.next());
             institucion = institucion + inst.next()+",";
             }
             System.out.println("valor :"+institucion);
             */
            String servicios = new String();
            String otrosServiciosAguas = new String();
            String sanitarios = new String();
            String amenazas = new String();


            Iterator<Servicio> serv = getServicios().getTarget().iterator();
            while (serv.hasNext()) {
                //System.out.println(inst.next());
                servicios = servicios + serv.next() + ",";
            }
            
            Iterator<OtroServicioAgua> otroServAguas = getOtroServicioAguas().getTarget().iterator();
            while (otroServAguas.hasNext()) {
                //System.out.println(inst.next());
                otrosServiciosAguas = otrosServiciosAguas + otroServAguas.next() + ",";
            }
            
            Iterator<TpSanitario> sanita = getSanitarios().getTarget().iterator();
            while (sanita.hasNext()) {
                //System.out.println(inst.next());
                sanitarios = sanitarios + sanita.next() + ",";
            }
            
            Iterator<TipoAmenaza> TpAmenaza = getAmenazas().getTarget().iterator();
            while (TpAmenaza.hasNext()) {
                //System.out.println(inst.next());
                amenazas = amenazas + TpAmenaza.next() + ",";
            }
            /*
            System.out.println("valor :" + servicios);
            System.out.println("valor :" + otrosServiciosAguas);
            System.out.println("valor :" + sanitarios);
            System.out.println("valor :" + amenazas); 
            */
             
            CallableStatement addAlbergue;
            String sql = "{ call ALBERGUE_ADD(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0.0,?,?,?,?,?)}";
            System.out.println("slq: " + sql);
            addAlbergue = connection.prepareCall(sql);
            
            Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
            init in = (init) viewMap.get("init");

            if (!(in == null)) {

                /*if (!in.getCodCaserio().equals("")) {
                 cs.setString(4, in.getCodCaserio());
                 } else {*/
                if (!in.getCodCanton().equals("")) {
                    addAlbergue.setString(2, in.getCodCanton());
                } else {
                    addAlbergue.setString(2, in.getCodMunicipio());
                }
            }
           
            addAlbergue.setString(1, getTpAlbergue());
            addAlbergue.setInt(3, getIdEntiResp());
            addAlbergue.setString(4, getNombreAlb());
            addAlbergue.setFloat(5, getAreaTotalMts());
            addAlbergue.setString(6, getDireccion());
            addAlbergue.setString(7, getPtoReferencia());
            addAlbergue.setFloat(8, getAreaTotalTech());
            addAlbergue.setInt(9, getCapacidadPers());
            addAlbergue.setInt(10, getHayAcceAdecuado());
            addAlbergue.setString(11, getExpAccAdecuado());
            addAlbergue.setInt(12, getServEnerElect());
            addAlbergue.setInt(13, getServAguaPot());
            addAlbergue.setInt(14, getNumSanitario());
            addAlbergue.setInt(15, getNumDucha());
            addAlbergue.setInt(16, getBodegaEmer());
            addAlbergue.setString(17, getNombLleno());
            addAlbergue.setString(18, getTelLleno());
            addAlbergue.setString(19, getNombcmpc());
            addAlbergue.setString(20, getTelcmpc());
            addAlbergue.setString(21, getNombccmp());
            addAlbergue.setString(22, getTelccmp());
            addAlbergue.setInt(23, getAreaCocinar());
            addAlbergue.setInt(24, getServTrenAseo());
            addAlbergue.setInt(25, getFrecuenciaTren());
            addAlbergue.setInt(26, getEspacioRecreacion());
            addAlbergue.setInt(27, getVentilacionNat());
            addAlbergue.setInt(28, getIluminacionNat());
            addAlbergue.setInt(29, getEquipoNecesarioEmer());
            addAlbergue.setInt(30, getSalidaEvacuar());
            addAlbergue.setInt(31, getAreaTechadaIlum());
            addAlbergue.setInt(32, getIluminacionNocturna());
            addAlbergue.setInt(33, getMuroProteccion());
            addAlbergue.setInt(34, getEspaciosPeligrosos());
            addAlbergue.setString(35, getObservacion());
            addAlbergue.setString(36, fecha);
            addAlbergue.setDouble(37, in.getLat());
            addAlbergue.setDouble(38, in.getLng());
            addAlbergue.setString(39, servicios);
            addAlbergue.setString(40, otrosServiciosAguas);
            addAlbergue.setString(41, sanitarios);
            addAlbergue.setString(42, amenazas);
            addAlbergue.setInt(43, getIdTpCondAcc());
            
            /*
             addAlbergue.setFloat(35,getAreaTotalMts());
             addAlbergue.setFloat(36,getAreaTotalTech());
             addAlbergue.setFloat(37,getAreaTotalTech());
             */
            addAlbergue.execute();
            /*
             PreparedStatement addAccionSeg = connection.prepareStatement("INSERT INTO ACCIONES(IDEV, CORRINC, IDESTADO, IDCONT, DESCACC, RESPCOORDACC, FECHORAREALACC, DURACACC, ESTADOACTACC, FECHORAALMACC, ESTADOACC)"
             + "VALUES('TM1201','000001', ?, 1, ?, ?, ?, ?, '', '2012/08/03', 'A')");
             addAccionSeg.setInt( 1, );
             addAccionSeg.setString(2, );
             
             addAccionSeg.executeQuery();
             */

        } finally {
            connection.close();
        }
        return "SisePlantilla";
    }

    /**
     * Creates a new instance of AlbergueBean
     */
    public AlbergueBean() {
    }
}
