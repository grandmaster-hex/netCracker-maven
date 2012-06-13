/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

import javax.inject.Named; 
   // or import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped; 
   // or import javax.faces.bean.SessionScoped;

@Named("Login")
@SessionScoped
public class Login implements Serializable {

    String loginAdmin;
    String loginHr;
    String loginInter;
    String passwordAdmin;
    String passwordHr;
    String passwordInter;

    public String getLoginAdmin() {
        return loginAdmin;
    }

    public void setLoginAdmin(String loginAdmin) {
        this.loginAdmin = loginAdmin;
    }

    public String getLoginHr() {
        return loginHr;
    }

    public void setLoginHr(String loginHr) {
        this.loginHr = loginHr;
    }

    public String getLoginInter() {
        return loginInter;
    }

    public void setLoginInter(String loginInter) {
        this.loginInter = loginInter;
    }

    public String getPasswordAdmin() {
        return passwordAdmin;
    }

    public void setPasswordAdmin(String passwordAdmin) {
        this.passwordAdmin = passwordAdmin;
    }

    public String getPasswordHr() {
        return passwordHr;
    }

    public void setPasswordHr(String passwordHr) {
        this.passwordHr = passwordHr;
    }

    public String getPasswordInter() {
        return passwordInter;
    }

    public void setPasswordInter(String passwordInter) {
        this.passwordInter = passwordInter;
    }

    public String checkAdmin() {
        return "index.xhtml";
    }
    public String checkHr() {
        return "index.xhtml";
    }
    public String checkInter() {
        return "index.xhtml";
    }
}
