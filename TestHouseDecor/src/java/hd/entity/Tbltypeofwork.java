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
@Table(name = "tbltypeofwork", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbltypeofwork.findAll", query = "SELECT t FROM Tbltypeofwork t")
    , @NamedQuery(name = "Tbltypeofwork.findById", query = "SELECT t FROM Tbltypeofwork t WHERE t.id = :id")
    , @NamedQuery(name = "Tbltypeofwork.findByName", query = "SELECT t FROM Tbltypeofwork t WHERE t.name = :name")})
public class Tbltypeofwork implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", length = 85)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblTypeOfWorkid")
    private Collection<Tblprofessional> tblprofessionalCollection;

    public Tbltypeofwork() {
    }

    public Tbltypeofwork(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Tblprofessional> getTblprofessionalCollection() {
        return tblprofessionalCollection;
    }

    public void setTblprofessionalCollection(Collection<Tblprofessional> tblprofessionalCollection) {
        this.tblprofessionalCollection = tblprofessionalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbltypeofwork)) {
            return false;
        }
        Tbltypeofwork other = (Tbltypeofwork) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Tbltypeofwork[ id=" + id + " ]";
    }
    
}
