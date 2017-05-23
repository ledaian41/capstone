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
@Table(name = "tblproductphoto", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "productphoto.findAll", query = "SELECT p FROM productphoto p")
    , @NamedQuery(name = "productphoto.findByProductPhotoID", query = "SELECT p FROM productphoto p WHERE p.productPhotoID = :productPhotoID")
    , @NamedQuery(name = "productphoto.findByUrl", query = "SELECT p FROM productphoto p WHERE p.url = :url")
    , @NamedQuery(name = "productphoto.findByTitle", query = "SELECT p FROM productphoto p WHERE p.title = :title")
    , @NamedQuery(name = "productphoto.findByDescription", query = "SELECT p FROM productphoto p WHERE p.description = :description")
    , @NamedQuery(name = "productphoto.findByStatus", query = "SELECT p FROM productphoto p WHERE p.status = :status")})
public class productphoto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "productPhotoID", nullable = false)
    private Integer productPhotoID;
    @Column(name = "url", length = 245)
    private String url;
    @Column(name = "title", length = 115)
    private String title;
    @Column(name = "description", length = 545)
    private String description;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "tblCategory_categoryID", referencedColumnName = "categoryID", nullable = false)
    @ManyToOne(optional = false)
    private category tblCategorycategoryID;
    @JoinColumn(name = "tblProduct_productID", referencedColumnName = "productID", nullable = false)
    @ManyToOne(optional = false)
    private product tblProductproductID;
    @JoinColumn(name = "tblStyle_styleID", referencedColumnName = "styleID", nullable = false)
    @ManyToOne(optional = false)
    private style tblStylestyleID;

    public productphoto() {
    }

    public productphoto(Integer productPhotoID) {
        this.productPhotoID = productPhotoID;
    }

    public Integer getProductPhotoID() {
        return productPhotoID;
    }

    public void setProductPhotoID(Integer productPhotoID) {
        this.productPhotoID = productPhotoID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public category getTblCategorycategoryID() {
        return tblCategorycategoryID;
    }

    public void setTblCategorycategoryID(category tblCategorycategoryID) {
        this.tblCategorycategoryID = tblCategorycategoryID;
    }

    public product getTblProductproductID() {
        return tblProductproductID;
    }

    public void setTblProductproductID(product tblProductproductID) {
        this.tblProductproductID = tblProductproductID;
    }

    public style getTblStylestyleID() {
        return tblStylestyleID;
    }

    public void setTblStylestyleID(style tblStylestyleID) {
        this.tblStylestyleID = tblStylestyleID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productPhotoID != null ? productPhotoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof productphoto)) {
            return false;
        }
        productphoto other = (productphoto) object;
        if ((this.productPhotoID == null && other.productPhotoID != null) || (this.productPhotoID != null && !this.productPhotoID.equals(other.productPhotoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.productphoto[ productPhotoID=" + productPhotoID + " ]";
    }
    
}
