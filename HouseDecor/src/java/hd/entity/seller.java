/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
 * @author cuk3t
 */
@Entity
@Table(name = "tblseller", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seller.findAll", query = "SELECT s FROM Seller s")
    , @NamedQuery(name = "Seller.findByTaxNumber", query = "SELECT s FROM Seller s WHERE s.taxNumber = :taxNumber")
    , @NamedQuery(name = "Seller.findByStoreAddress", query = "SELECT s FROM Seller s WHERE s.storeAddress = :storeAddress")
    , @NamedQuery(name = "Seller.findBySellerName", query = "SELECT s FROM Seller s WHERE s.sellerName = :sellerName")
    , @NamedQuery(name = "Seller.findByStartDate", query = "SELECT s FROM Seller s WHERE s.startDate = :startDate")
    , @NamedQuery(name = "Seller.findByDueDate", query = "SELECT s FROM Seller s WHERE s.dueDate = :dueDate")
    , @NamedQuery(name = "Seller.findByPhone", query = "SELECT s FROM Seller s WHERE s.phone = :phone")
    , @NamedQuery(name = "Seller.findByUserID", query = "SELECT s FROM Seller s WHERE s.userID = :userID")})
public class Seller implements Serializable {

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
    private Professional professional;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblSelleruserID")
    private List<Product> productList;

    public Seller() {
    }

    public Seller(Integer userID) {
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

    public Professional getProfessional() {
        return professional;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
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
        if (!(object instanceof Seller)) {
            return false;
        }
        Seller other = (Seller) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Seller[ userID=" + userID + " ]";
    }
    
}
