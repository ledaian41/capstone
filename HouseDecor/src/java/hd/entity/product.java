/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.entity;

import java.io.Serializable;
import java.util.List;
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
 * @author cuk3t
 */
@Entity
@Table(name = "tblproduct", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "product.findAll", query = "SELECT p FROM product p")
    , @NamedQuery(name = "product.findByProductID", query = "SELECT p FROM product p WHERE p.productID = :productID")
    , @NamedQuery(name = "product.findByProductName", query = "SELECT p FROM product p WHERE p.productName = :productName")
    , @NamedQuery(name = "product.findByBarCode", query = "SELECT p FROM product p WHERE p.barCode = :barCode")
    , @NamedQuery(name = "product.findByDescripsion", query = "SELECT p FROM product p WHERE p.descripsion = :descripsion")
    , @NamedQuery(name = "product.findByPrice", query = "SELECT p FROM product p WHERE p.price = :price")
    , @NamedQuery(name = "product.findByQuantity", query = "SELECT p FROM product p WHERE p.quantity = :quantity")
    , @NamedQuery(name = "product.findBySize", query = "SELECT p FROM product p WHERE p.size = :size")
    , @NamedQuery(name = "product.findByMaterial", query = "SELECT p FROM product p WHERE p.material = :material")
    , @NamedQuery(name = "product.findByWarranty", query = "SELECT p FROM product p WHERE p.warranty = :warranty")
    , @NamedQuery(name = "product.findByStatus", query = "SELECT p FROM product p WHERE p.status = :status")})
public class product implements Serializable {

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
    private List<promotion> promotionList;
    @JoinColumn(name = "tblSeller_userID", referencedColumnName = "userID", nullable = false)
    @ManyToOne(optional = false)
    private seller tblSelleruserID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblProductproductID")
    private List<productphoto> productphotoList;

    public product() {
    }

    public product(Integer productID) {
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
    public List<promotion> getPromotionList() {
        return promotionList;
    }

    public void setPromotionList(List<promotion> promotionList) {
        this.promotionList = promotionList;
    }

    public seller getTblSelleruserID() {
        return tblSelleruserID;
    }

    public void setTblSelleruserID(seller tblSelleruserID) {
        this.tblSelleruserID = tblSelleruserID;
    }

    @XmlTransient
    public List<productphoto> getProductphotoList() {
        return productphotoList;
    }

    public void setProductphotoList(List<productphoto> productphotoList) {
        this.productphotoList = productphotoList;
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
        if (!(object instanceof product)) {
            return false;
        }
        product other = (product) object;
        if ((this.productID == null && other.productID != null) || (this.productID != null && !this.productID.equals(other.productID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.product[ productID=" + productID + " ]";
    }
    
}
