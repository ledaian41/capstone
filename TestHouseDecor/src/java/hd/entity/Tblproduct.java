/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lê Đại An
 */
@Entity
@Table(name = "tblproduct", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblproduct.findAll", query = "SELECT t FROM Tblproduct t")
    , @NamedQuery(name = "Tblproduct.findByProductID", query = "SELECT t FROM Tblproduct t WHERE t.productID = :productID")
    , @NamedQuery(name = "Tblproduct.findByProductName", query = "SELECT t FROM Tblproduct t WHERE t.productName = :productName")
    , @NamedQuery(name = "Tblproduct.findByBarCode", query = "SELECT t FROM Tblproduct t WHERE t.barCode = :barCode")
    , @NamedQuery(name = "Tblproduct.findByDescripsion", query = "SELECT t FROM Tblproduct t WHERE t.descripsion = :descripsion")
    , @NamedQuery(name = "Tblproduct.findByPrice", query = "SELECT t FROM Tblproduct t WHERE t.price = :price")
    , @NamedQuery(name = "Tblproduct.findByQuantity", query = "SELECT t FROM Tblproduct t WHERE t.quantity = :quantity")
    , @NamedQuery(name = "Tblproduct.findBySize", query = "SELECT t FROM Tblproduct t WHERE t.size = :size")
    , @NamedQuery(name = "Tblproduct.findByMaterial", query = "SELECT t FROM Tblproduct t WHERE t.material = :material")
    , @NamedQuery(name = "Tblproduct.findByWarranty", query = "SELECT t FROM Tblproduct t WHERE t.warranty = :warranty")
    , @NamedQuery(name = "Tblproduct.findByStatus", query = "SELECT t FROM Tblproduct t WHERE t.status = :status")})
public class Tblproduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "productID", nullable = false)
    private Integer productID;
    @Column(name = "productName", length = 145)
    private String productName;
    @Column(name = "barCode", length = 45)
    private String barCode;
    @Column(name = "descripsion", length = 545)
    private String descripsion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price", precision = 12)
    private Float price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "size", length = 45)
    private String size;
    @Column(name = "material", length = 45)
    private String material;
    @Column(name = "warranty", length = 45)
    private String warranty;
    @Column(name = "status")
    private Integer status;
    @JoinTable(name = "tblproduct_has_tblpromotion", joinColumns = {
        @JoinColumn(name = "tblProduct_productID", referencedColumnName = "productID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "tblPromotion_id", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private Collection<Tblpromotion> tblpromotionCollection;
    @JoinColumn(name = "tblSeller_userID", referencedColumnName = "userID", nullable = false)
    @ManyToOne(optional = false)
    private Tblseller tblSelleruserID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblProductproductID")
    private Collection<Tblproductphoto> tblproductphotoCollection;

    public Tblproduct() {
    }

    public Tblproduct(Integer productID) {
        this.productID = productID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getDescripsion() {
        return descripsion;
    }

    public void setDescripsion(String descripsion) {
        this.descripsion = descripsion;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Tblpromotion> getTblpromotionCollection() {
        return tblpromotionCollection;
    }

    public void setTblpromotionCollection(Collection<Tblpromotion> tblpromotionCollection) {
        this.tblpromotionCollection = tblpromotionCollection;
    }

    public Tblseller getTblSelleruserID() {
        return tblSelleruserID;
    }

    public void setTblSelleruserID(Tblseller tblSelleruserID) {
        this.tblSelleruserID = tblSelleruserID;
    }

    @XmlTransient
    public Collection<Tblproductphoto> getTblproductphotoCollection() {
        return tblproductphotoCollection;
    }

    public void setTblproductphotoCollection(Collection<Tblproductphoto> tblproductphotoCollection) {
        this.tblproductphotoCollection = tblproductphotoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productID != null ? productID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblproduct)) {
            return false;
        }
        Tblproduct other = (Tblproduct) object;
        if ((this.productID == null && other.productID != null) || (this.productID != null && !this.productID.equals(other.productID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Tblproduct[ productID=" + productID + " ]";
    }
    
}
