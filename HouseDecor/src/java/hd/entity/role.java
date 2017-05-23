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
 * @author cuk3t
 */
@Entity
@Table(name = "tblrole", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "role.findAll", query = "SELECT r FROM role r")
    , @NamedQuery(name = "role.findByRoleID", query = "SELECT r FROM role r WHERE r.roleID = :roleID")
    , @NamedQuery(name = "role.findByRoleName", query = "SELECT r FROM role r WHERE r.roleName = :roleName")})
public class role implements Serializable {

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
    private user userID;

    public role() {
    }

    public role(Integer roleID) {
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

    public user getUserID() {
        return userID;
    }

    public void setUserID(user userID) {
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
        if (!(object instanceof role)) {
            return false;
        }
        role other = (role) object;
        if ((this.roleID == null && other.roleID != null) || (this.roleID != null && !this.roleID.equals(other.roleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.role[ roleID=" + roleID + " ]";
    }
    
}
