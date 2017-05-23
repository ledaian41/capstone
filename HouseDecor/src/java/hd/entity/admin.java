/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cuk3t
 */
@Entity
@Table(name = "tbladmin", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "admin.findAll", query = "SELECT a FROM admin a")
    , @NamedQuery(name = "admin.findById", query = "SELECT a FROM admin a WHERE a.id = :id")
    , @NamedQuery(name = "admin.findByPassword", query = "SELECT a FROM admin a WHERE a.password = :password")
    , @NamedQuery(name = "admin.findByEmail", query = "SELECT a FROM admin a WHERE a.email = :email")})
public class admin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "password", length = 45)
    private String password;
    @Column(name = "email", length = 115)
    private String email;

    public admin() {
    }

    public admin(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof admin)) {
            return false;
        }
        admin other = (admin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.admin[ id=" + id + " ]";
    }
    
}
