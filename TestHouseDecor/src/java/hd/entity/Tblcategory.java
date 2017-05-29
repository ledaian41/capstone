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
@Table(name = "tblcategory", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblcategory.findAll", query = "SELECT t FROM Tblcategory t")
    , @NamedQuery(name = "Tblcategory.findByCategoryID", query = "SELECT t FROM Tblcategory t WHERE t.categoryID = :categoryID")
    , @NamedQuery(name = "Tblcategory.findByCategoryName", query = "SELECT t FROM Tblcategory t WHERE t.categoryName = :categoryName")})
public class Tblcategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "categoryID", nullable = false)
    private Integer categoryID;
    @Column(name = "categoryName", length = 95)
    private String categoryName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblcategory")
    private Collection<Tbltracking> tbltrackingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblCategorycategoryID")
    private Collection<Tblideabookphoto> tblideabookphotoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblCategorycategoryID")
    private Collection<Tblproductphoto> tblproductphotoCollection;

    public Tblcategory() {
    }

    public Tblcategory(Integer categoryID) {
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
    public Collection<Tbltracking> getTbltrackingCollection() {
        return tbltrackingCollection;
    }

    public void setTbltrackingCollection(Collection<Tbltracking> tbltrackingCollection) {
        this.tbltrackingCollection = tbltrackingCollection;
    }

    @XmlTransient
    public Collection<Tblideabookphoto> getTblideabookphotoCollection() {
        return tblideabookphotoCollection;
    }

    public void setTblideabookphotoCollection(Collection<Tblideabookphoto> tblideabookphotoCollection) {
        this.tblideabookphotoCollection = tblideabookphotoCollection;
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
        hash += (categoryID != null ? categoryID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblcategory)) {
            return false;
        }
        Tblcategory other = (Tblcategory) object;
        if ((this.categoryID == null && other.categoryID != null) || (this.categoryID != null && !this.categoryID.equals(other.categoryID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Tblcategory[ categoryID=" + categoryID + " ]";
    }
    
}
