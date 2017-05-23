/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cuk3t
 */
@Entity
@Table(name = "tbltracking", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "tracking.findAll", query = "SELECT t FROM tracking t")
    , @NamedQuery(name = "tracking.findByCount", query = "SELECT t FROM tracking t WHERE t.count = :count")
    , @NamedQuery(name = "tracking.findByLastUpdate", query = "SELECT t FROM tracking t WHERE t.lastUpdate = :lastUpdate")
    , @NamedQuery(name = "tracking.findByUserID", query = "SELECT t FROM tracking t WHERE t.trackingPK.userID = :userID")
    , @NamedQuery(name = "tracking.findByTblCategorycategoryID", query = "SELECT t FROM tracking t WHERE t.trackingPK.tblCategorycategoryID = :tblCategorycategoryID")})
public class tracking implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected trackingPK trackingPK;
    @Column(name = "count")
    private Integer count;
    @Column(name = "lastUpdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @JoinColumn(name = "tblCategory_categoryID", referencedColumnName = "categoryID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private category category;
    @JoinColumn(name = "userID", referencedColumnName = "userID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private user user;

    public tracking() {
    }

    public tracking(trackingPK trackingPK) {
        this.trackingPK = trackingPK;
    }

    public tracking(int userID, int tblCategorycategoryID) {
        this.trackingPK = new trackingPK(userID, tblCategorycategoryID);
    }

    public trackingPK getTrackingPK() {
        return trackingPK;
    }

    public void setTrackingPK(trackingPK trackingPK) {
        this.trackingPK = trackingPK;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public category getCategory() {
        return category;
    }

    public void setCategory(category category) {
        this.category = category;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trackingPK != null ? trackingPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof tracking)) {
            return false;
        }
        tracking other = (tracking) object;
        if ((this.trackingPK == null && other.trackingPK != null) || (this.trackingPK != null && !this.trackingPK.equals(other.trackingPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.tracking[ trackingPK=" + trackingPK + " ]";
    }
    
}
