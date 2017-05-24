/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lê Đại An
 */
@Entity
@Table(name = "tblstyle", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblstyle.findAll", query = "SELECT t FROM Tblstyle t")
    , @NamedQuery(name = "Tblstyle.findByStyleID", query = "SELECT t FROM Tblstyle t WHERE t.styleID = :styleID")
    , @NamedQuery(name = "Tblstyle.findByName", query = "SELECT t FROM Tblstyle t WHERE t.name = :name")})
public class Tblstyle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "styleID", nullable = false)
    private Integer styleID;
    @Column(name = "name", length = 45)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblStylestyleID")
    private Collection<Tblideabookphoto> tblideabookphotoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblStylestyleID")
    private Collection<Tblproductphoto> tblproductphotoCollection;

    public Tblstyle() {
    }

    public Tblstyle(Integer styleID) {
        this.styleID = styleID;
    }

    public Integer getStyleID() {
        return styleID;
    }

    public void setStyleID(Integer styleID) {
        this.styleID = styleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Tblideabookphoto> getTblideabookphotoCollection() {
        return tblideabookphotoCollection;
    }

    public void setTblideabookphotoCollection(Collection<Tblideabookphoto> tblideabookphotoCollection) {
        this.tblideabookphotoCollection = tblideabookphotoCollection;
    }

    @XmlTransient
    public Collection<Tblproductphoto> getTblproductphotoCollection() {
        return tblproductphotoCollection;
    }

    public void setTblproductphotoCollection(Collection<Tblproductphoto> tblproductphotoCollection) {
        this.tblproductphotoCollection = tblproductphotoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (styleID != null ? styleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblstyle)) {
            return false;
        }
        Tblstyle other = (Tblstyle) object;
        if ((this.styleID == null && other.styleID != null) || (this.styleID != null && !this.styleID.equals(other.styleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Tblstyle[ styleID=" + styleID + " ]";
    }
    
}
