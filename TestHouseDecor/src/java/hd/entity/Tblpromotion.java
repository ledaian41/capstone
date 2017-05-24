/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lê Đại An
 */
@Entity
@Table(name = "tblpromotion", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblpromotion.findAll", query = "SELECT t FROM Tblpromotion t")
    , @NamedQuery(name = "Tblpromotion.findById", query = "SELECT t FROM Tblpromotion t WHERE t.id = :id")
    , @NamedQuery(name = "Tblpromotion.findByName", query = "SELECT t FROM Tblpromotion t WHERE t.name = :name")
    , @NamedQuery(name = "Tblpromotion.findByDescription", query = "SELECT t FROM Tblpromotion t WHERE t.description = :description")
    , @NamedQuery(name = "Tblpromotion.findByStatus", query = "SELECT t FROM Tblpromotion t WHERE t.status = :status")
    , @NamedQuery(name = "Tblpromotion.findByStartDate", query = "SELECT t FROM Tblpromotion t WHERE t.startDate = :startDate")
    , @NamedQuery(name = "Tblpromotion.findByEndDate", query = "SELECT t FROM Tblpromotion t WHERE t.endDate = :endDate")})
public class Tblpromotion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", length = 145)
    private String name;
    @Column(name = "description", length = 545)
    private String description;
    @Column(name = "status")
    private Integer status;
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @ManyToMany(mappedBy = "tblpromotionCollection")
    private Collection<Tblproduct> tblproductCollection;

    public Tblpromotion() {
    }

    public Tblpromotion(Integer id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @XmlTransient
    public Collection<Tblproduct> getTblproductCollection() {
        return tblproductCollection;
    }

    public void setTblproductCollection(Collection<Tblproduct> tblproductCollection) {
        this.tblproductCollection = tblproductCollection;
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
        if (!(object instanceof Tblpromotion)) {
            return false;
        }
        Tblpromotion other = (Tblpromotion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Tblpromotion[ id=" + id + " ]";
    }
    
}
