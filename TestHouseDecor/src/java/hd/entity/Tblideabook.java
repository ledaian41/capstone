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
import javax.persistence.JoinTable;
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
@Table(name = "tblideabook", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblideabook.findAll", query = "SELECT t FROM Tblideabook t")
    , @NamedQuery(name = "Tblideabook.findByIdeaBookID", query = "SELECT t FROM Tblideabook t WHERE t.ideaBookID = :ideaBookID")
    , @NamedQuery(name = "Tblideabook.findByTitle", query = "SELECT t FROM Tblideabook t WHERE t.title = :title")
    , @NamedQuery(name = "Tblideabook.findByDescription", query = "SELECT t FROM Tblideabook t WHERE t.description = :description")
    , @NamedQuery(name = "Tblideabook.findByIsPublic", query = "SELECT t FROM Tblideabook t WHERE t.isPublic = :isPublic")
    , @NamedQuery(name = "Tblideabook.findByStatus", query = "SELECT t FROM Tblideabook t WHERE t.status = :status")})
public class Tblideabook implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ideaBookID", nullable = false)
    private Integer ideaBookID;
    @Column(name = "title", length = 85)
    private String title;
    @Column(name = "description", length = 555)
    private String description;
    @Column(name = "isPublic")
    private Integer isPublic;
    @Column(name = "status", length = 45)
    private String status;
    @JoinTable(name = "tblideabookphoto_has_tblideabook", joinColumns = {
        @JoinColumn(name = "tblIdeaBook_ideaBookID", referencedColumnName = "ideaBookID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "tblIdeaBookPhoto_photoID", referencedColumnName = "photoID", nullable = false)})
    @ManyToMany
    private Collection<Tblideabookphoto> tblideabookphotoCollection;
    @JoinColumn(name = "userID", referencedColumnName = "userID", nullable = false)
    @ManyToOne(optional = false)
    private Tbluser userID;

    public Tblideabook() {
    }

    public Tblideabook(Integer ideaBookID) {
        this.ideaBookID = ideaBookID;
    }

    public Integer getIdeaBookID() {
        return ideaBookID;
    }

    public void setIdeaBookID(Integer ideaBookID) {
        this.ideaBookID = ideaBookID;
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

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Tblideabookphoto> getTblideabookphotoCollection() {
        return tblideabookphotoCollection;
    }

    public void setTblideabookphotoCollection(Collection<Tblideabookphoto> tblideabookphotoCollection) {
        this.tblideabookphotoCollection = tblideabookphotoCollection;
    }

    public Tbluser getUserID() {
        return userID;
    }

    public void setUserID(Tbluser userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideaBookID != null ? ideaBookID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblideabook)) {
            return false;
        }
        Tblideabook other = (Tblideabook) object;
        if ((this.ideaBookID == null && other.ideaBookID != null) || (this.ideaBookID != null && !this.ideaBookID.equals(other.ideaBookID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Tblideabook[ ideaBookID=" + ideaBookID + " ]";
    }
    
}
