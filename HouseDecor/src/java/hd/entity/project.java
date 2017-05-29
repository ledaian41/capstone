/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cuk3t
 */
@Entity
@Table(name = "tblproject", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p")
    , @NamedQuery(name = "Project.findByProjectID", query = "SELECT p FROM Project p WHERE p.projectID = :projectID")
    , @NamedQuery(name = "Project.findByProjectName", query = "SELECT p FROM Project p WHERE p.projectName = :projectName")
    , @NamedQuery(name = "Project.findByAddress", query = "SELECT p FROM Project p WHERE p.address = :address")
    , @NamedQuery(name = "Project.findByCost", query = "SELECT p FROM Project p WHERE p.cost = :cost")
    , @NamedQuery(name = "Project.findByWebsite", query = "SELECT p FROM Project p WHERE p.website = :website")
    , @NamedQuery(name = "Project.findByYear", query = "SELECT p FROM Project p WHERE p.year = :year")
    , @NamedQuery(name = "Project.findByKeywords", query = "SELECT p FROM Project p WHERE p.keywords = :keywords")
    , @NamedQuery(name = "Project.findByStatus", query = "SELECT p FROM Project p WHERE p.status = :status")})
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "projectID", nullable = false)
    private Integer projectID;
    @Column(name = "projectName", length = 155)
    private String projectName;
    @Column(name = "address", length = 255)
    private String address;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cost", precision = 12)
    private Float cost;
    @Column(name = "website", length = 145)
    private String website;
    @Column(name = "year")
    @Temporal(TemporalType.DATE)
    private Date year;
    @Column(name = "keywords", length = 145)
    private String keywords;
    @Column(name = "status")
    private Integer status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectID")
    private List<Ideabookphoto> ideabookphotoList;
    @JoinColumn(name = "userID", referencedColumnName = "userID", nullable = false)
    @ManyToOne(optional = false)
    private User userID;

    public Project() {
    }

    public Project(Integer projectID) {
        this.projectID = projectID;
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @XmlTransient
    public List<Ideabookphoto> getIdeabookphotoList() {
        return ideabookphotoList;
    }

    public void setIdeabookphotoList(List<Ideabookphoto> ideabookphotoList) {
        this.ideabookphotoList = ideabookphotoList;
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
        hash += (projectID != null ? projectID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.projectID == null && other.projectID != null) || (this.projectID != null && !this.projectID.equals(other.projectID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Project[ projectID=" + projectID + " ]";
    }
    
}
