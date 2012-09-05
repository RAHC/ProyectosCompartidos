/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean
@RequestScoped
public class PasswordBean {
    
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public PasswordBean() {
    }
}
