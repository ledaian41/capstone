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
    @NamedQuery(name = "Story.findAll", query = "SELECT s FROM Story s")
    , @NamedQuery(name = "Story.findByStoryID", query = "SELECT s FROM Story s WHERE s.storyID = :storyID")
    , @NamedQuery(name = "Story.findByTitle", query = "SELECT s FROM Story s WHERE s.title = :title")
    , @NamedQuery(name = "Story.findByContent", query = "SELECT s FROM Story s WHERE s.content = :content")
    , @NamedQuery(name = "Story.findByStatus", query = "SELECT s FROM Story s WHERE s.status = :status")})
public class Story implements Serializable {

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
    private User userID;

    public Story() {
    }

    public Story(Integer storyID) {
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

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
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
        if (!(object instanceof Story)) {
            return false;
        }
        Story other = (Story) object;
        if ((this.storyID == null && other.storyID != null) || (this.storyID != null && !this.storyID.equals(other.storyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Story[ storyID=" + storyID + " ]";
    }
    
}
