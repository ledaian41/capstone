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
@Table(name = "tblproductphoto", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblproductphoto.findAll", query = "SELECT t FROM Tblproductphoto t")
    , @NamedQuery(name = "Tblproductphoto.findByProductPhotoID", query = "SELECT t FROM Tblproductphoto t WHERE t.productPhotoID = :productPhotoID")
    , @NamedQuery(name = "Tblproductphoto.findByUrl", query = "SELECT t FROM Tblproductphoto t WHERE t.url = :url")
    , @NamedQuery(name = "Tblproductphoto.findByTitle", query = "SELECT t FROM Tblproductphoto t WHERE t.title = :title")
    , @NamedQuery(name = "Tblproductphoto.findByDescription", query = "SELECT t FROM Tblproductphoto t WHERE t.description = :description")
    , @NamedQuery(name = "Tblproductphoto.findByStatus", query = "SELECT t FROM Tblproductphoto t WHERE t.status = :status")})
public class Tblproductphoto implements Serializable {

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
    private Tblcategory tblCategorycategoryID;
    @JoinColumn(name = "tblProduct_productID", referencedColumnName = "productID", nullable = false)
    @ManyToOne(optional = false)
    private Tblproduct tblProductproductID;
    @JoinColumn(name = "tblStyle_styleID", referencedColumnName = "styleID", nullable = false)
    @ManyToOne(optional = false)
    private Tblstyle tblStylestyleID;

    public Tblproductphoto() {
    }

    public Tblproductphoto(Integer productPhotoID) {
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

    public Tblcategory getTblCategorycategoryID() {
        return tblCategorycategoryID;
    }

    public void setTblCategorycategoryID(Tblcategory tblCategorycategoryID) {
        this.tblCategorycategoryID = tblCategorycategoryID;
    }

    public Tblproduct getTblProductproductID() {
        return tblProductproductID;
    }

    public void setTblProductproductID(Tblproduct tblProductproductID) {
        this.tblProductproductID = tblProductproductID;
    }

    public Tblstyle getTblStylestyleID() {
        return tblStylestyleID;
    }

    public void setTblStylestyleID(Tblstyle tblStylestyleID) {
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
        if (!(object instanceof Tblproductphoto)) {
            return false;
        }
        Tblproductphoto other = (Tblproductphoto) object;
        if ((this.productPhotoID == null && other.productPhotoID != null) || (this.productPhotoID != null && !this.productPhotoID.equals(other.productPhotoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Tblproductphoto[ productPhotoID=" + productPhotoID + " ]";
    }
    
}
