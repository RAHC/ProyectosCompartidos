/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private boolean isLoggedIn;
    @Resource(name = "jdbc/sise")
    DataSource dataSource;

    public String login() {
        //custom member manager class
        //RegistroIncidente memberManager = new RegistroIncidente();
        //default url in case of login failure;
        String url = "login.xhtml";

        //user a custom method to authenticate a user
        if (validar()) {
            //changed the state to true
            isLoggedIn = true;
            url = "forum.xhtml";
        } else {
            //set the message to display when authentication fails
            FacesContext.getCurrentInstance().addMessage("frmLogin:btnLogin", new FacesMessage("Usuario o contraseña no validos"));
        }
        return url;
    }

    /**
     * An event listener for redirecting the user to login page if he/she is not
     * currently logged in
     *
     * @param event
     */
    /*public boolean validar() {

     boolean resultado = false;



     try {
     Connection connection = dataSource.getConnection();
     if (dataSource == null) {
     throw new SQLException("No se pudo tener acceso a la fuente de datos");
     }

     if (connection == null) {
     throw new SQLException("No se pudo conectar a la fuente de datos");
     }


     PreparedStatement getUsuario = connection.prepareStatement(
     "SELECT username, userpass FROM usuario WHERE username = ? AND userpass = ? ");
     //Convert(varchar(25), DecryptbyPassPhrase('12345', USERPASS))
     //select username, Convert(varchar(25), DecryptbyPassPhrase('12345', USERPASS)) from USUARIO WHERE username = ? AND userpass = ?

     getUsuario.setString(1, getUsername());
     getUsuario.setString(2, getPassword());

     CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
     rowSet.populate(getUsuario.executeQuery());

     resultado = rowSet.next();

     } catch (SQLException exc) {
     } 

     return resultado;
     }*/
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
                    "SELECT username FROM usuario WHERE username = ? ");
            getUsuario.setString(1, getUsername());
            /*getUsuario.setString(2, getPassword()); */

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
            /*resultado = rowSet.next();

             if (rowSet.next()) {
             return "index";
             } else {
             return "login";
             }*/

        } catch (SQLException exc) {
        } /*finally {
         try{
         connection.close();
         }
         catch(SQLException exc){
            
         }
         }*/

        return resultado;
    }

    public void verifyUseLogin(ComponentSystemEvent event) {
        if (!isLoggedIn) {
            doRedirect("login.xhtml");
        }
    }

    /**
     * Method for redirecting a request
     *
     * @param url
     */
    private void doRedirect(String url) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String cerrarSesion() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("loginBean");
        return "login";

        /*
         FacesContext context = FacesContext.getCurrentInstance();

         ExternalContext externalContext = context.getExternalContext();

         Object session = externalContext.getSession(false);

         HttpSession httpSession = (HttpSession) session;

         httpSession.invalidate();
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

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
}
