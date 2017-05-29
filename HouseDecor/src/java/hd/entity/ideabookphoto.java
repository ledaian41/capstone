/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.entity;

import java.io.Serializable;
import java.util.List;
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
 * @author cuk3t
 */
@Entity
@Table(name = "tblideabookphoto", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ideabookphoto.findAll", query = "SELECT i FROM Ideabookphoto i")
    , @NamedQuery(name = "Ideabookphoto.findByPhotoID", query = "SELECT i FROM Ideabookphoto i WHERE i.photoID = :photoID")
    , @NamedQuery(name = "Ideabookphoto.findByUrl", query = "SELECT i FROM Ideabookphoto i WHERE i.url = :url")
    , @NamedQuery(name = "Ideabookphoto.findByTilte", query = "SELECT i FROM Ideabookphoto i WHERE i.tilte = :tilte")
    , @NamedQuery(name = "Ideabookphoto.findByDescription", query = "SELECT i FROM Ideabookphoto i WHERE i.description = :description")
    , @NamedQuery(name = "Ideabookphoto.findByStatus", query = "SELECT i FROM Ideabookphoto i WHERE i.status = :status")})
public class Ideabookphoto implements Serializable {

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
    @ManyToMany(mappedBy = "ideabookphotoList")
    private List<Ideabook> ideabookList;
    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID", nullable = false)
    @ManyToOne(optional = false)
    private Category categoryID;
    @JoinColumn(name = "projectID", referencedColumnName = "projectID", nullable = false)
    @ManyToOne(optional = false)
    private Project projectID;
    @JoinColumn(name = "styleID", referencedColumnName = "styleID", nullable = false)
    @ManyToOne(optional = false)
    private Style styleID;

    public Ideabookphoto() {
    }

    public Ideabookphoto(Integer photoID) {
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
    public List<Ideabook> getIdeabookList() {
        return ideabookList;
    }

    public void setIdeabookList(List<Ideabook> ideabookList) {
        this.ideabookList = ideabookList;
    }

    public Category getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }

    public Project getProjectID() {
        return projectID;
    }

    public void setProjectID(Project projectID) {
        this.projectID = projectID;
    }

    public Style getStyleID() {
        return styleID;
    }

    public void setStyleID(Style styleID) {
        this.styleID = styleID;
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
        if (!(object instanceof Ideabookphoto)) {
            return false;
        }
        Ideabookphoto other = (Ideabookphoto) object;
        if ((this.photoID == null && other.photoID != null) || (this.photoID != null && !this.photoID.equals(other.photoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Ideabookphoto[ photoID=" + photoID + " ]";
    }
    
}
