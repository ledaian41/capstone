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
 * @author cuk3t
 */
@Entity
@Table(name = "tblorder", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "orders.findAll", query = "SELECT o FROM orders o")
    , @NamedQuery(name = "orders.findByOrderID", query = "SELECT o FROM orders o WHERE o.orderID = :orderID")
    , @NamedQuery(name = "orders.findByCreatedTime", query = "SELECT o FROM orders o WHERE o.createdTime = :createdTime")
    , @NamedQuery(name = "orders.findByStatus", query = "SELECT o FROM orders o WHERE o.status = :status")})
public class orders implements Serializable {

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
    private user userID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderID")
    private List<orderdetail> orderdetailList;

    public orders() {
    }

    public orders(Integer orderID) {
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

    public user getUserID() {
        return userID;
    }

    public void setUserID(user userID) {
        this.userID = userID;
    }

    @XmlTransient
    public List<orderdetail> getOrderdetailList() {
        return orderdetailList;
    }

    public void setOrderdetailList(List<orderdetail> orderdetailList) {
        this.orderdetailList = orderdetailList;
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
        if (!(object instanceof orders)) {
            return false;
        }
        orders other = (orders) object;
        if ((this.orderID == null && other.orderID != null) || (this.orderID != null && !this.orderID.equals(other.orderID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.orders[ orderID=" + orderID + " ]";
    }
    
}
