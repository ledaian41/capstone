/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.entity;

import java.io.Serializable;
import java.util.List;
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
 * @author cuk3t
 */
@Entity
@Table(name = "tblstyle", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Style.findAll", query = "SELECT s FROM Style s")
    , @NamedQuery(name = "Style.findByStyleID", query = "SELECT s FROM Style s WHERE s.styleID = :styleID")
    , @NamedQuery(name = "Style.findByName", query = "SELECT s FROM Style s WHERE s.name = :name")})
public class Style implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "styleID", nullable = false)
    private Integer styleID;
    @Column(name = "name", length = 45)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "styleID")
    private List<Ideabookphoto> ideabookphotoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblStylestyleID")
    private List<Productphoto> productphotoList;

    public Style() {
    }

    public Style(Integer styleID) {
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
    public List<Ideabookphoto> getIdeabookphotoList() {
        return ideabookphotoList;
    }

    public void setIdeabookphotoList(List<Ideabookphoto> ideabookphotoList) {
        this.ideabookphotoList = ideabookphotoList;
    }

    @XmlTransient
    public List<Productphoto> getProductphotoList() {
        return productphotoList;
    }

    public void setProductphotoList(List<Productphoto> productphotoList) {
        this.productphotoList = productphotoList;
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
        if (!(object instanceof Style)) {
            return false;
        }
        Style other = (Style) object;
        if ((this.styleID == null && other.styleID != null) || (this.styleID != null && !this.styleID.equals(other.styleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Style[ styleID=" + styleID + " ]";
    }
    
}
