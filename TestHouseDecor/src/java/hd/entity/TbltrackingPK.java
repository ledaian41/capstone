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
 * @author Lê Đại An
 */
@Embeddable
public class TbltrackingPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "userID", nullable = false)
    private int userID;
    @Basic(optional = false)
    @Column(name = "tblCategory_categoryID", nullable = false)
    private int tblCategorycategoryID;

    public TbltrackingPK() {
    }

    public TbltrackingPK(int userID, int tblCategorycategoryID) {
        this.userID = userID;
        this.tblCategorycategoryID = tblCategorycategoryID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTblCategorycategoryID() {
        return tblCategorycategoryID;
    }

    public void setTblCategorycategoryID(int tblCategorycategoryID) {
        this.tblCategorycategoryID = tblCategorycategoryID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userID;
        hash += (int) tblCategorycategoryID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbltrackingPK)) {
            return false;
        }
        TbltrackingPK other = (TbltrackingPK) object;
        if (this.userID != other.userID) {
            return false;
        }
        if (this.tblCategorycategoryID != other.tblCategorycategoryID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.TbltrackingPK[ userID=" + userID + ", tblCategorycategoryID=" + tblCategorycategoryID + " ]";
    }
    
}
