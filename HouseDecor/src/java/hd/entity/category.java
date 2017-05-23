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
@Table(name = "tblcategory", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "category.findAll", query = "SELECT c FROM category c")
    , @NamedQuery(name = "category.findByCategoryID", query = "SELECT c FROM category c WHERE c.categoryID = :categoryID")
    , @NamedQuery(name = "category.findByCategoryName", query = "SELECT c FROM category c WHERE c.categoryName = :categoryName")})
public class category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "categoryID", nullable = false)
    private Integer categoryID;
    @Column(name = "categoryName", length = 95)
    private String categoryName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<tracking> trackingList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblCategorycategoryID")
    private List<ideabookphoto> ideabookphotoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblCategorycategoryID")
    private List<productphoto> productphotoList;

    public category() {
    }

    public category(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @XmlTransient
    public List<tracking> getTrackingList() {
        return trackingList;
    }

    public void setTrackingList(List<tracking> trackingList) {
        this.trackingList = trackingList;
    }

    @XmlTransient
    public List<ideabookphoto> getIdeabookphotoList() {
        return ideabookphotoList;
    }

    public void setIdeabookphotoList(List<ideabookphoto> ideabookphotoList) {
        this.ideabookphotoList = ideabookphotoList;
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
        hash += (categoryID != null ? categoryID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof category)) {
            return false;
        }
        category other = (category) object;
        if ((this.categoryID == null && other.categoryID != null) || (this.categoryID != null && !this.categoryID.equals(other.categoryID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.category[ categoryID=" + categoryID + " ]";
    }
    
}
