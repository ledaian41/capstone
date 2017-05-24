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
 * @author Lê Đại An
 */
@Entity
@Table(name = "tbltracking", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbltracking.findAll", query = "SELECT t FROM Tbltracking t")
    , @NamedQuery(name = "Tbltracking.findByCount", query = "SELECT t FROM Tbltracking t WHERE t.count = :count")
    , @NamedQuery(name = "Tbltracking.findByLastUpdate", query = "SELECT t FROM Tbltracking t WHERE t.lastUpdate = :lastUpdate")
    , @NamedQuery(name = "Tbltracking.findByUserID", query = "SELECT t FROM Tbltracking t WHERE t.tbltrackingPK.userID = :userID")
    , @NamedQuery(name = "Tbltracking.findByTblCategorycategoryID", query = "SELECT t FROM Tbltracking t WHERE t.tbltrackingPK.tblCategorycategoryID = :tblCategorycategoryID")})
public class Tbltracking implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TbltrackingPK tbltrackingPK;
    @Column(name = "count")
    private Integer count;
    @Column(name = "lastUpdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @JoinColumn(name = "tblCategory_categoryID", referencedColumnName = "categoryID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tblcategory tblcategory;
    @JoinColumn(name = "userID", referencedColumnName = "userID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tbluser tbluser;

    public Tbltracking() {
    }

    public Tbltracking(TbltrackingPK tbltrackingPK) {
        this.tbltrackingPK = tbltrackingPK;
    }

    public Tbltracking(int userID, int tblCategorycategoryID) {
        this.tbltrackingPK = new TbltrackingPK(userID, tblCategorycategoryID);
    }

    public TbltrackingPK getTbltrackingPK() {
        return tbltrackingPK;
    }

    public void setTbltrackingPK(TbltrackingPK tbltrackingPK) {
        this.tbltrackingPK = tbltrackingPK;
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

    public Tblcategory getTblcategory() {
        return tblcategory;
    }

    public void setTblcategory(Tblcategory tblcategory) {
        this.tblcategory = tblcategory;
    }

    public Tbluser getTbluser() {
        return tbluser;
    }

    public void setTbluser(Tbluser tbluser) {
        this.tbluser = tbluser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tbltrackingPK != null ? tbltrackingPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbltracking)) {
            return false;
        }
        Tbltracking other = (Tbltracking) object;
        if ((this.tbltrackingPK == null && other.tbltrackingPK != null) || (this.tbltrackingPK != null && !this.tbltrackingPK.equals(other.tbltrackingPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Tbltracking[ tbltrackingPK=" + tbltrackingPK + " ]";
    }
    
}
