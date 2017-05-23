/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cuk3t
 */
@Entity
@Table(name = "tblprofessional", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "professional.findAll", query = "SELECT p FROM professional p")
    , @NamedQuery(name = "professional.findByNameProfessional", query = "SELECT p FROM professional p WHERE p.nameProfessional = :nameProfessional")
    , @NamedQuery(name = "professional.findByAddress", query = "SELECT p FROM professional p WHERE p.address = :address")
    , @NamedQuery(name = "professional.findByUserID", query = "SELECT p FROM professional p WHERE p.userID = :userID")})
public class professional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "nameProfessional", length = 45)
    private String nameProfessional;
    @Column(name = "address", length = 45)
    private String address;
    @Id
    @Basic(optional = false)
    @Column(name = "userID", nullable = false)
    private Integer userID;
    @JoinColumn(name = "tblTypeOfWork_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private typeofwork tblTypeOfWorkid;
    @JoinColumn(name = "userID", referencedColumnName = "userID", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private user user;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "professional")
    private seller seller;

    public professional() {
    }

    public professional(Integer userID) {
        this.userID = userID;
    }

    public String getNameProfessional() {
        return nameProfessional;
    }

    public void setNameProfessional(String nameProfessional) {
        this.nameProfessional = nameProfessional;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public typeofwork getTblTypeOfWorkid() {
        return tblTypeOfWorkid;
    }

    public void setTblTypeOfWorkid(typeofwork tblTypeOfWorkid) {
        this.tblTypeOfWorkid = tblTypeOfWorkid;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public seller getSeller() {
        return seller;
    }

    public void setSeller(seller seller) {
        this.seller = seller;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof professional)) {
            return false;
        }
        professional other = (professional) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.professional[ userID=" + userID + " ]";
    }
    
}
