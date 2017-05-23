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
@Table(name = "tblorderdetail", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "orderdetail.findAll", query = "SELECT o FROM orderdetail o")
    , @NamedQuery(name = "orderdetail.findByOrderDetailID", query = "SELECT o FROM orderdetail o WHERE o.orderDetailID = :orderDetailID")
    , @NamedQuery(name = "orderdetail.findByProductID", query = "SELECT o FROM orderdetail o WHERE o.productID = :productID")
    , @NamedQuery(name = "orderdetail.findByQuantity", query = "SELECT o FROM orderdetail o WHERE o.quantity = :quantity")
    , @NamedQuery(name = "orderdetail.findByPrice", query = "SELECT o FROM orderdetail o WHERE o.price = :price")
    , @NamedQuery(name = "orderdetail.findByStoreID", query = "SELECT o FROM orderdetail o WHERE o.storeID = :storeID")
    , @NamedQuery(name = "orderdetail.findByStatus", query = "SELECT o FROM orderdetail o WHERE o.status = :status")})
public class orderdetail implements Serializable {

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
    private orders orderID;

    public orderdetail() {
    }

    public orderdetail(Integer orderDetailID) {
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

    public orders getOrderID() {
        return orderID;
    }

    public void setOrderID(orders orderID) {
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
        if (!(object instanceof orderdetail)) {
            return false;
        }
        orderdetail other = (orderdetail) object;
        if ((this.orderDetailID == null && other.orderDetailID != null) || (this.orderDetailID != null && !this.orderDetailID.equals(other.orderDetailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.orderdetail[ orderDetailID=" + orderDetailID + " ]";
    }
    
}
