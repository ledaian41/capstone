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
@Table(name = "tblstory", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "story.findAll", query = "SELECT s FROM story s")
    , @NamedQuery(name = "story.findByStoryID", query = "SELECT s FROM story s WHERE s.storyID = :storyID")
    , @NamedQuery(name = "story.findByTitle", query = "SELECT s FROM story s WHERE s.title = :title")
    , @NamedQuery(name = "story.findByContent", query = "SELECT s FROM story s WHERE s.content = :content")
    , @NamedQuery(name = "story.findByStatus", query = "SELECT s FROM story s WHERE s.status = :status")})
public class story implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "storyID", nullable = false)
    private Integer storyID;
    @Column(name = "title", length = 85)
    private String title;
    @Column(name = "content", length = 545)
    private String content;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "userID", referencedColumnName = "userID", nullable = false)
    @ManyToOne(optional = false)
    private user userID;

    public story() {
    }

    public story(Integer storyID) {
        this.storyID = storyID;
    }

    public Integer getStoryID() {
        return storyID;
    }

    public void setStoryID(Integer storyID) {
        this.storyID = storyID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        hash += (storyID != null ? storyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof story)) {
            return false;
        }
        story other = (story) object;
        if ((this.storyID == null && other.storyID != null) || (this.storyID != null && !this.storyID.equals(other.storyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.story[ storyID=" + storyID + " ]";
    }
    
}
