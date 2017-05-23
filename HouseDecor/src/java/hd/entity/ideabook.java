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
 * @author cuk3t
 */
@Entity
@Table(name = "tblideabook", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ideabook.findAll", query = "SELECT i FROM ideabook i")
    , @NamedQuery(name = "ideabook.findByIdeaBookID", query = "SELECT i FROM ideabook i WHERE i.ideaBookID = :ideaBookID")
    , @NamedQuery(name = "ideabook.findByTitle", query = "SELECT i FROM ideabook i WHERE i.title = :title")
    , @NamedQuery(name = "ideabook.findByDescription", query = "SELECT i FROM ideabook i WHERE i.description = :description")
    , @NamedQuery(name = "ideabook.findByIsPublic", query = "SELECT i FROM ideabook i WHERE i.isPublic = :isPublic")
    , @NamedQuery(name = "ideabook.findByStatus", query = "SELECT i FROM ideabook i WHERE i.status = :status")})
public class ideabook implements Serializable {

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
    private List<ideabookphoto> ideabookphotoList;
    @JoinColumn(name = "userID", referencedColumnName = "userID", nullable = false)
    @ManyToOne(optional = false)
    private user userID;

    public ideabook() {
    }

    public ideabook(Integer ideaBookID) {
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
    public List<ideabookphoto> getIdeabookphotoList() {
        return ideabookphotoList;
    }

    public void setIdeabookphotoList(List<ideabookphoto> ideabookphotoList) {
        this.ideabookphotoList = ideabookphotoList;
    }

    public user getUserID() {
        return userID;
    }

    public void setUserID(user userID) {
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
        if (!(object instanceof ideabook)) {
            return false;
        }
        ideabook other = (ideabook) object;
        if ((this.ideaBookID == null && other.ideaBookID != null) || (this.ideaBookID != null && !this.ideaBookID.equals(other.ideaBookID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.ideabook[ ideaBookID=" + ideaBookID + " ]";
    }
    
}
