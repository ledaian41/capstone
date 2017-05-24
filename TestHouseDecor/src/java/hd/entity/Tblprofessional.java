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
 * @author Lê Đại An
 */
@Entity
@Table(name = "tblprofessional", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblprofessional.findAll", query = "SELECT t FROM Tblprofessional t")
    , @NamedQuery(name = "Tblprofessional.findByNameProfessional", query = "SELECT t FROM Tblprofessional t WHERE t.nameProfessional = :nameProfessional")
    , @NamedQuery(name = "Tblprofessional.findByAddress", query = "SELECT t FROM Tblprofessional t WHERE t.address = :address")
    , @NamedQuery(name = "Tblprofessional.findByUserID", query = "SELECT t FROM Tblprofessional t WHERE t.userID = :userID")})
public class Tblprofessional implements Serializable {

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
    private Tbltypeofwork tblTypeOfWorkid;
    @JoinColumn(name = "userID", referencedColumnName = "userID", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Tbluser tbluser;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tblprofessional")
    private Tblseller tblseller;

    public Tblprofessional() {
    }

    public Tblprofessional(Integer userID) {
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

    public Tbltypeofwork getTblTypeOfWorkid() {
        return tblTypeOfWorkid;
    }

    public void setTblTypeOfWorkid(Tbltypeofwork tblTypeOfWorkid) {
        this.tblTypeOfWorkid = tblTypeOfWorkid;
    }

    public Tbluser getTbluser() {
        return tbluser;
    }

    public void setTbluser(Tbluser tbluser) {
        this.tbluser = tbluser;
    }

    public Tblseller getTblseller() {
        return tblseller;
    }

    public void setTblseller(Tblseller tblseller) {
        this.tblseller = tblseller;
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
        if (!(object instanceof Tblprofessional)) {
            return false;
        }
        Tblprofessional other = (Tblprofessional) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Tblprofessional[ userID=" + userID + " ]";
    }
    
}
