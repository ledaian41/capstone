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
@Table(name = "tblorderdetail", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblorderdetail.findAll", query = "SELECT t FROM Tblorderdetail t")
    , @NamedQuery(name = "Tblorderdetail.findByOrderDetailID", query = "SELECT t FROM Tblorderdetail t WHERE t.orderDetailID = :orderDetailID")
    , @NamedQuery(name = "Tblorderdetail.findByProductID", query = "SELECT t FROM Tblorderdetail t WHERE t.productID = :productID")
    , @NamedQuery(name = "Tblorderdetail.findByQuantity", query = "SELECT t FROM Tblorderdetail t WHERE t.quantity = :quantity")
    , @NamedQuery(name = "Tblorderdetail.findByPrice", query = "SELECT t FROM Tblorderdetail t WHERE t.price = :price")
    , @NamedQuery(name = "Tblorderdetail.findByStoreID", query = "SELECT t FROM Tblorderdetail t WHERE t.storeID = :storeID")
    , @NamedQuery(name = "Tblorderdetail.findByStatus", query = "SELECT t FROM Tblorderdetail t WHERE t.status = :status")})
public class Tblorderdetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "orderDetailID", nullable = false)
    private Integer orderDetailID;
    @Column(name = "productID")
    private Integer productID;
    @Column(name = "quantity")
    private Integer quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price", precision = 12)
    private Float price;
    @Column(name = "storeID")
    private Integer storeID;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "orderID", referencedColumnName = "orderID", nullable = false)
    @ManyToOne(optional = false)
    private Tblorder orderID;

    public Tblorderdetail() {
    }

    public Tblorderdetail(Integer orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public Integer getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(Integer orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getStoreID() {
        return storeID;
    }

    public void setStoreID(Integer storeID) {
        this.storeID = storeID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Tblorder getOrderID() {
        return orderID;
    }

    public void setOrderID(Tblorder orderID) {
        this.orderID = orderID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderDetailID != null ? orderDetailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblorderdetail)) {
            return false;
        }
        Tblorderdetail other = (Tblorderdetail) object;
        if ((this.orderDetailID == null && other.orderDetailID != null) || (this.orderDetailID != null && !this.orderDetailID.equals(other.orderDetailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Tblorderdetail[ orderDetailID=" + orderDetailID + " ]";
    }
    
}
