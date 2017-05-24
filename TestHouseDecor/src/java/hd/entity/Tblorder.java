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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "tblorder", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblorder.findAll", query = "SELECT t FROM Tblorder t")
    , @NamedQuery(name = "Tblorder.findByOrderID", query = "SELECT t FROM Tblorder t WHERE t.orderID = :orderID")
    , @NamedQuery(name = "Tblorder.findByCreatedTime", query = "SELECT t FROM Tblorder t WHERE t.createdTime = :createdTime")
    , @NamedQuery(name = "Tblorder.findByStatus", query = "SELECT t FROM Tblorder t WHERE t.status = :status")})
public class Tblorder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "orderID", nullable = false)
    private Integer orderID;
    @Column(name = "createdTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "userID", referencedColumnName = "userID", nullable = false)
    @ManyToOne(optional = false)
    private Tbluser userID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderID")
    private Collection<Tblorderdetail> tblorderdetailCollection;

    public Tblorder() {
    }

    public Tblorder(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Tbluser getUserID() {
        return userID;
    }

    public void setUserID(Tbluser userID) {
        this.userID = userID;
    }

    @XmlTransient
    public Collection<Tblorderdetail> getTblorderdetailCollection() {
        return tblorderdetailCollection;
    }

    public void setTblorderdetailCollection(Collection<Tblorderdetail> tblorderdetailCollection) {
        this.tblorderdetailCollection = tblorderdetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderID != null ? orderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblorder)) {
            return false;
        }
        Tblorder other = (Tblorder) object;
        if ((this.orderID == null && other.orderID != null) || (this.orderID != null && !this.orderID.equals(other.orderID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Tblorder[ orderID=" + orderID + " ]";
    }
    
}
