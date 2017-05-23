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
@Table(name = "tbltypeofwork", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "typeofwork.findAll", query = "SELECT t FROM typeofwork t")
    , @NamedQuery(name = "typeofwork.findById", query = "SELECT t FROM typeofwork t WHERE t.id = :id")
    , @NamedQuery(name = "typeofwork.findByName", query = "SELECT t FROM typeofwork t WHERE t.name = :name")})
public class typeofwork implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", length = 85)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblTypeOfWorkid")
    private List<professional> professionalList;

    public typeofwork() {
    }

    public typeofwork(Integer id) {
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
    public List<professional> getProfessionalList() {
        return professionalList;
    }

    public void setProfessionalList(List<professional> professionalList) {
        this.professionalList = professionalList;
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
        if (!(object instanceof typeofwork)) {
            return false;
        }
        typeofwork other = (typeofwork) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.typeofwork[ id=" + id + " ]";
    }
    
}