/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author cuk3t
 */
@Embeddable
public class TrackingPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "userID", nullable = false)
    private int userID;
    @Basic(optional = false)
    @Column(name = "categoryID", nullable = false)
    private int categoryID;

    public TrackingPK() {
    }

    public TrackingPK(int userID, int categoryID) {
        this.userID = userID;
        this.categoryID = categoryID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userID;
        hash += (int) categoryID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrackingPK)) {
            return false;
        }
        TrackingPK other = (TrackingPK) object;
        if (this.userID != other.userID) {
            return false;
        }
        if (this.categoryID != other.categoryID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.TrackingPK[ userID=" + userID + ", categoryID=" + categoryID + " ]";
    }
    
}
