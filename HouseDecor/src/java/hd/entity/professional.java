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
    @NamedQuery(name = "Professional.findAll", query = "SELECT p FROM Professional p")
    , @NamedQuery(name = "Professional.findByNameProfessional", query = "SELECT p FROM Professional p WHERE p.nameProfessional = :nameProfessional")
    , @NamedQuery(name = "Professional.findByAddress", query = "SELECT p FROM Professional p WHERE p.address = :address")
    , @NamedQuery(name = "Professional.findByUserID", query = "SELECT p FROM Professional p WHERE p.userID = :userID")})
public class Professional implements Serializable {

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
    private Typeofwork tblTypeOfWorkid;
    @JoinColumn(name = "userID", referencedColumnName = "userID", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "professional")
    private Seller seller;

    public Professional() {
    }

    public Professional(Integer userID) {
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

    public Typeofwork getTblTypeOfWorkid() {
        return tblTypeOfWorkid;
    }

    public void setTblTypeOfWorkid(Typeofwork tblTypeOfWorkid) {
        this.tblTypeOfWorkid = tblTypeOfWorkid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
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
        if (!(object instanceof Professional)) {
            return false;
        }
        Professional other = (Professional) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Professional[ userID=" + userID + " ]";
    }
    
}
