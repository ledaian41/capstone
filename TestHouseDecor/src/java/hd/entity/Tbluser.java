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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lê Đại An
 */
@Entity
@Table(name = "tbluser", catalog = "mydb", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbluser.findAll", query = "SELECT t FROM Tbluser t")
    , @NamedQuery(name = "Tbluser.findByUserID", query = "SELECT t FROM Tbluser t WHERE t.userID = :userID")
    , @NamedQuery(name = "Tbluser.findByEmail", query = "SELECT t FROM Tbluser t WHERE t.email = :email")
    , @NamedQuery(name = "Tbluser.findByPassword", query = "SELECT t FROM Tbluser t WHERE t.password = :password")
    , @NamedQuery(name = "Tbluser.findByFirstname", query = "SELECT t FROM Tbluser t WHERE t.firstname = :firstname")
    , @NamedQuery(name = "Tbluser.findByLastname", query = "SELECT t FROM Tbluser t WHERE t.lastname = :lastname")
    , @NamedQuery(name = "Tbluser.findByDateOfBirth", query = "SELECT t FROM Tbluser t WHERE t.dateOfBirth = :dateOfBirth")
    , @NamedQuery(name = "Tbluser.findByRegisterDate", query = "SELECT t FROM Tbluser t WHERE t.registerDate = :registerDate")
    , @NamedQuery(name = "Tbluser.findByPhoneNumber", query = "SELECT t FROM Tbluser t WHERE t.phoneNumber = :phoneNumber")
    , @NamedQuery(name = "Tbluser.findByRoleID", query = "SELECT t FROM Tbluser t WHERE t.roleID = :roleID")
    , @NamedQuery(name = "Tbluser.findByGender", query = "SELECT t FROM Tbluser t WHERE t.gender = :gender")
    , @NamedQuery(name = "Tbluser.findByStatus", query = "SELECT t FROM Tbluser t WHERE t.status = :status")
    , @NamedQuery(name = "Tbluser.findByStatusAndRole", query = "SELECT t FROM Tbluser t WHERE t.status = :status AND t.roleID = :roleID")
    , @NamedQuery(name = "Tbluser.findByPrimayryAddress", query = "SELECT t FROM Tbluser t WHERE t.primayryAddress = :primayryAddress")
    , @NamedQuery(name = "Tbluser.findBySencondAddress", query = "SELECT t FROM Tbluser t WHERE t.sencondAddress = :sencondAddress")
    , @NamedQuery(name = "Tbluser.setStatusAccount", query = "UPDATE Tbluser t SET t.status= :status WHERE t.userID= :userID")})
public class Tbluser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userID", nullable = false)
    private Integer userID;
    @Column(name = "email", length = 75)
    private String email;
    @Column(name = "password", length = 55)
    private String password;
    @Column(name = "firstname", length = 40)
    private String firstname;
    @Column(name = "lastname", length = 40)
    private String lastname;
    @Column(name = "dateOfBirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "registerDate")
    @Temporal(TemporalType.DATE)
    private Date registerDate;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "roleID")
    private Integer roleID;
    @Column(name = "gender")
    private Boolean gender;
    @Lob
    @Column(name = "aboutMe", length = 16777215)
    private String aboutMe;
    @Column(name = "status")
    private Integer status;
    @Column(name = "primayryAddress", length = 255)
    private String primayryAddress;
    @Column(name = "sencondAddress", length = 255)
    private String sencondAddress;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Tblrole> tblroleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbluser")
    private Collection<Tbltracking> tbltrackingCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tbluser")
    private Tblprofessional tblprofessional;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Tblproject> tblprojectCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Tblstory> tblstoryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Tblorder> tblorderCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Tblideabook> tblideabookCollection;
    @JoinColumn(name = "cityCode", referencedColumnName = "cityCode", nullable = false)
    @ManyToOne(optional = false)
    private Tlcity cityCode;

    public Tbluser() {
    }

    public Tbluser(Integer userID) {
        this.userID = userID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPrimayryAddress() {
        return primayryAddress;
    }

    public void setPrimayryAddress(String primayryAddress) {
        this.primayryAddress = primayryAddress;
    }

    public String getSencondAddress() {
        return sencondAddress;
    }

    public void setSencondAddress(String sencondAddress) {
        this.sencondAddress = sencondAddress;
    }

    @XmlTransient
    public Collection<Tblrole> getTblroleCollection() {
        return tblroleCollection;
    }

    public void setTblroleCollection(Collection<Tblrole> tblroleCollection) {
        this.tblroleCollection = tblroleCollection;
    }

    @XmlTransient
    public Collection<Tbltracking> getTbltrackingCollection() {
        return tbltrackingCollection;
    }

    public void setTbltrackingCollection(Collection<Tbltracking> tbltrackingCollection) {
        this.tbltrackingCollection = tbltrackingCollection;
    }

    public Tblprofessional getTblprofessional() {
        return tblprofessional;
    }

    public void setTblprofessional(Tblprofessional tblprofessional) {
        this.tblprofessional = tblprofessional;
    }

    @XmlTransient
    public Collection<Tblproject> getTblprojectCollection() {
        return tblprojectCollection;
    }

    public void setTblprojectCollection(Collection<Tblproject> tblprojectCollection) {
        this.tblprojectCollection = tblprojectCollection;
    }

    @XmlTransient
    public Collection<Tblstory> getTblstoryCollection() {
        return tblstoryCollection;
    }

    public void setTblstoryCollection(Collection<Tblstory> tblstoryCollection) {
        this.tblstoryCollection = tblstoryCollection;
    }

    @XmlTransient
    public Collection<Tblorder> getTblorderCollection() {
        return tblorderCollection;
    }

    public void setTblorderCollection(Collection<Tblorder> tblorderCollection) {
        this.tblorderCollection = tblorderCollection;
    }

    @XmlTransient
    public Collection<Tblideabook> getTblideabookCollection() {
        return tblideabookCollection;
    }

    public void setTblideabookCollection(Collection<Tblideabook> tblideabookCollection) {
        this.tblideabookCollection = tblideabookCollection;
    }

    public Tlcity getCityCode() {
        return cityCode;
    }

    public void setCityCode(Tlcity cityCode) {
        this.cityCode = cityCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbluser)) {
            return false;
        }
        Tbluser other = (Tbluser) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Tbluser[ userID=" + userID + " ]";
    }
    
}
