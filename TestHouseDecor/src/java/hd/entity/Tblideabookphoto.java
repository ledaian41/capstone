/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lê Đại An
 */
@Entity
@Table(name = "tblideabookphoto", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblideabookphoto.findAll", query = "SELECT t FROM Tblideabookphoto t")
    , @NamedQuery(name = "Tblideabookphoto.findByPhotoID", query = "SELECT t FROM Tblideabookphoto t WHERE t.photoID = :photoID")
    , @NamedQuery(name = "Tblideabookphoto.findByUrl", query = "SELECT t FROM Tblideabookphoto t WHERE t.url = :url")
    , @NamedQuery(name = "Tblideabookphoto.findByTilte", query = "SELECT t FROM Tblideabookphoto t WHERE t.tilte = :tilte")
    , @NamedQuery(name = "Tblideabookphoto.findByDescription", query = "SELECT t FROM Tblideabookphoto t WHERE t.description = :description")
    , @NamedQuery(name = "Tblideabookphoto.findByStatus", query = "SELECT t FROM Tblideabookphoto t WHERE t.status = :status")})
public class Tblideabookphoto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "photoID", nullable = false)
    private Integer photoID;
    @Column(name = "url", length = 355)
    private String url;
    @Column(name = "tilte", length = 145)
    private String tilte;
    @Column(name = "description", length = 555)
    private String description;
    @Column(name = "status")
    private Integer status;
    @ManyToMany(mappedBy = "tblideabookphotoCollection")
    private Collection<Tblideabook> tblideabookCollection;
    @JoinColumn(name = "tblCategory_categoryID", referencedColumnName = "categoryID", nullable = false)
    @ManyToOne(optional = false)
    private Tblcategory tblCategorycategoryID;
    @JoinColumn(name = "tblProject_projectID", referencedColumnName = "projectID", nullable = false)
    @ManyToOne(optional = false)
    private Tblproject tblProjectprojectID;
    @JoinColumn(name = "tblStyle_styleID", referencedColumnName = "styleID", nullable = false)
    @ManyToOne(optional = false)
    private Tblstyle tblStylestyleID;

    public Tblideabookphoto() {
    }

    public Tblideabookphoto(Integer photoID) {
        this.photoID = photoID;
    }

    public Integer getPhotoID() {
        return photoID;
    }

    public void setPhotoID(Integer photoID) {
        this.photoID = photoID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
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

    @XmlTransient
    public Collection<Tblideabook> getTblideabookCollection() {
        return tblideabookCollection;
    }

    public void setTblideabookCollection(Collection<Tblideabook> tblideabookCollection) {
        this.tblideabookCollection = tblideabookCollection;
    }

    public Tblcategory getTblCategorycategoryID() {
        return tblCategorycategoryID;
    }

    public void setTblCategorycategoryID(Tblcategory tblCategorycategoryID) {
        this.tblCategorycategoryID = tblCategorycategoryID;
    }

    public Tblproject getTblProjectprojectID() {
        return tblProjectprojectID;
    }

    public void setTblProjectprojectID(Tblproject tblProjectprojectID) {
        this.tblProjectprojectID = tblProjectprojectID;
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
        hash += (photoID != null ? photoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblideabookphoto)) {
            return false;
        }
        Tblideabookphoto other = (Tblideabookphoto) object;
        if ((this.photoID == null && other.photoID != null) || (this.photoID != null && !this.photoID.equals(other.photoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Tblideabookphoto[ photoID=" + photoID + " ]";
    }
    
}
