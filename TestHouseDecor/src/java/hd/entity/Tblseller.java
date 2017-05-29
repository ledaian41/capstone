/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lê Đại An
 */
@Entity
@Table(name = "tblseller", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblseller.findAll", query = "SELECT t FROM Tblseller t")
    , @NamedQuery(name = "Tblseller.findByTaxNumber", query = "SELECT t FROM Tblseller t WHERE t.taxNumber = :taxNumber")
    , @NamedQuery(name = "Tblseller.findByStoreAddress", query = "SELECT t FROM Tblseller t WHERE t.storeAddress = :storeAddress")
    , @NamedQuery(name = "Tblseller.findBySellerName", query = "SELECT t FROM Tblseller t WHERE t.sellerName = :sellerName")
    , @NamedQuery(name = "Tblseller.findByStartDate", query = "SELECT t FROM Tblseller t WHERE t.startDate = :startDate")
    , @NamedQuery(name = "Tblseller.findByDueDate", query = "SELECT t FROM Tblseller t WHERE t.dueDate = :dueDate")
    , @NamedQuery(name = "Tblseller.findByPhone", query = "SELECT t FROM Tblseller t WHERE t.phone = :phone")
    , @NamedQuery(name = "Tblseller.findByUserID", query = "SELECT t FROM Tblseller t WHERE t.userID = :userID")})
public class Tblseller implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "taxNumber", length = 45)
    private String taxNumber;
    @Column(name = "storeAddress", length = 245)
    private String storeAddress;
    @Column(name = "sellerName", length = 245)
    private String sellerName;
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "dueDate")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Column(name = "phone")
    private Integer phone;
    @Id
    @Basic(optional = false)
    @Column(name = "userID", nullable = false)
    private Integer userID;
    @JoinColumn(name = "userID", referencedColumnName = "userID", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Tblprofessional tblprofessional;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblSelleruserID")
    private Collection<Tblproduct> tblproductCollection;

    public Tblseller() {
    }

    public Tblseller(Integer userID) {
        this.userID = userID;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Tblprofessional getTblprofessional() {
        return tblprofessional;
    }

    public void setTblprofessional(Tblprofessional tblprofessional) {
        this.tblprofessional = tblprofessional;
    }

    @XmlTransient
    public Collection<Tblproduct> getTblproductCollection() {
        return tblproductCollection;
    }

    public void setTblproductCollection(Collection<Tblproduct> tblproductCollection) {
        this.tblproductCollection = tblproductCollection;
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
        if (!(object instanceof Tblseller)) {
            return false;
        }
        Tblseller other = (Tblseller) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Tblseller[ userID=" + userID + " ]";
    }
    
}
