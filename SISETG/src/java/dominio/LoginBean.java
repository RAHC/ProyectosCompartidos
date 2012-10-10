package dominio;
/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String IdEvento;
    private String IdUbic;
    private String Jurisdiccion;
    private String opcion;
    private Integer IdCont;
    private Integer IdRol;
    private boolean isLoggedIn;
    private StringBuffer sb;
    @Resource(name = "jdbc/sise")
    DataSource dataSource;

    public void verifyUseLogin(ComponentSystemEvent event) {
        if (!isLoggedIn) {
            doRedirect("login.xhtml");
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
            this.sb = origRequest.getRequestURL();
        }
    }

    public String login() {

        String url = "login.xhtml";

        if (validar()) {
            //changed the state to true
            isLoggedIn = true;

            if (sb == null) {
                url = "listadoIncidentes.xhtml";
                //doRedirect(url);

            } else {
                //doRedirect(sb.toString());
                url = sb.toString();
            }
        } else {
            //set the message to display when authentication fails
            FacesContext.getCurrentInstance().addMessage("frmLogin:btnLogin", new FacesMessage("Usuario o contraseña no validos"));
        }
        doRedirect(url);
        return url;
    }

    private void doRedirect(String url) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validar() {

        boolean resultado = false;
        String pass;

        try {
            Connection connection = dataSource.getConnection();
            if (dataSource == null) {
                throw new SQLException("No se pudo tener acceso a la fuente de datos");
            }

            if (connection == null) {
                throw new SQLException("No se pudo conectar a la fuente de datos");
            }

            PreparedStatement getUsuario = connection.prepareStatement(
                    "SELECT username, IDCONT, IDROL, JURISDICCION_ FROM usuario WHERE username = ? AND USERESTADO='A'");
            getUsuario.setString(1, getUsername());

            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getUsuario.executeQuery());

            if (rowSet.next()) {
                PreparedStatement getPassword = connection.prepareStatement(
                        "SELECT Convert(varchar(25), DecryptbyPassPhrase('12345', USERPASS)) as P from USUARIO WHERE username = ?");
                getPassword.setString(1, getUsername());

                CachedRowSet rowSet2 = new com.sun.rowset.CachedRowSetImpl();
                rowSet2.populate(getPassword.executeQuery());
                if (rowSet2.next()) {
                    pass = rowSet2.getString("P");
                    if (pass.equals(getPassword())) {
                        setIdCont(rowSet.getInt("IDCONT"));
                        setIdRol(rowSet.getInt("IDROL"));
                        setJurisdiccion(rowSet.getString("JURISDICCION_"));
                        if ("N".equals(rowSet.getString("JURISDICCION_"))) {
                            setIdUbic("00");
                            
                        } else if ("D".equals(rowSet.getString("JURISDICCION_"))) {
                            PreparedStatement getUbic = connection.prepareStatement(
                                    " SELECT SUBSTRING(idubic,0,5) as UBIC from CONTACTOS where IDCONT ="+rowSet.getInt("IDCONT"));
                           CachedRowSet rowSet3 =  new com.sun.rowset.CachedRowSetImpl();
                           rowSet3.populate(getUbic.executeQuery());
                           rowSet3.next();
                           setIdUbic(rowSet3.getString("UBIC"));
                        }
                        
                        else if ("M".equals(rowSet.getString("JURISDICCION_"))) {
                            PreparedStatement getUbic = connection.prepareStatement(
                                    " SELECT SUBSTRING(idubic,0,7) as UBIC from CONTACTOS where IDCONT ="+rowSet.getInt("IDCONT"));
                           CachedRowSet rowSet3 =  new com.sun.rowset.CachedRowSetImpl();
                           rowSet3.populate(getUbic.executeQuery());
                           rowSet3.next();
                           setIdUbic(rowSet3.getString("UBIC"));
                        }
                        
                        resultado = true;
                    } else {
                        resultado = false;
                    }
                } else {
                    resultado = rowSet.next();
                }
            } else {
                resultado = rowSet.next();
            }

        } catch (SQLException exc) {
        }

        return resultado;
    }

    public String cerrarSesion() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) context.getExternalContext().getSession(false);
        httpSession.invalidate();
        httpSession.invalidate();
        return "login";
        /*
         * FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().getSessionMap().remove("loginBean");
         */

        /*
         * FacesContext context = FacesContext.getCurrentInstance();
         * ExternalContext externalContext = context.getExternalContext();
         * Object session = externalContext.getSession(false); HttpSession
         * httpSession = (HttpSession) session;
         */

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdEvento() {
        return IdEvento;
    }

    public void setIdEvento(String IdEvento) {
        this.IdEvento = IdEvento;
    }

    public String getIdUbic() {
        return IdUbic;
    }

    public void setIdUbic(String IdUbic) {
        this.IdUbic = IdUbic;
    }

    public Integer getIdCont() {
        return IdCont;
    }

    public void setIdCont(Integer IdCont) {
        this.IdCont = IdCont;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    public Integer getIdRol() {
        return IdRol;
    }

    public void setIdRol(Integer IdRol) {
        this.IdRol = IdRol;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public String getJurisdiccion() {
        return Jurisdiccion;
    }

    public void setJurisdiccion(String Jurisdiccion) {
        this.Jurisdiccion = Jurisdiccion;
    }
}
