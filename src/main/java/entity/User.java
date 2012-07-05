/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author dev
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    protected Integer id = null;
    protected String login = null;
    protected String password = null;
    protected Boolean admin = false;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Column(name = "admin")
    public Boolean getAdmin() {
        return admin;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
