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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lê Đại An
 */
@Entity
@Table(name = "tblrole", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblrole.findAll", query = "SELECT t FROM Tblrole t")
    , @NamedQuery(name = "Tblrole.findByRoleID", query = "SELECT t FROM Tblrole t WHERE t.roleID = :roleID")
    , @NamedQuery(name = "Tblrole.findByRoleName", query = "SELECT t FROM Tblrole t WHERE t.roleName = :roleName")})
public class Tblrole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "roleID", nullable = false)
    private Integer roleID;
    @Column(name = "roleName", length = 50)
    private String roleName;
    @JoinColumn(name = "userID", referencedColumnName = "userID", nullable = false)
    @ManyToOne(optional = false)
    private Tbluser userID;

    public Tblrole() {
    }

    public Tblrole(Integer roleID) {
        this.roleID = roleID;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Tbluser getUserID() {
        return userID;
    }

    public void setUserID(Tbluser userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleID != null ? roleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblrole)) {
            return false;
        }
        Tblrole other = (Tblrole) object;
        if ((this.roleID == null && other.roleID != null) || (this.roleID != null && !this.roleID.equals(other.roleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Tblrole[ roleID=" + roleID + " ]";
    }
    
}
