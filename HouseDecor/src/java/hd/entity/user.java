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
 * @author cuk3t
 */
@Entity
@Table(name = "tbluser", catalog = "mydb", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "user.findAll", query = "SELECT u FROM user u")
    , @NamedQuery(name = "user.findByUserID", query = "SELECT u FROM user u WHERE u.userID = :userID")
    , @NamedQuery(name = "user.findByEmail", query = "SELECT u FROM user u WHERE u.email = :email")
    , @NamedQuery(name = "user.findByPassword", query = "SELECT u FROM user u WHERE u.password = :password")
    , @NamedQuery(name = "user.findByFirstname", query = "SELECT u FROM user u WHERE u.firstname = :firstname")
    , @NamedQuery(name = "user.findByLastname", query = "SELECT u FROM user u WHERE u.lastname = :lastname")
    , @NamedQuery(name = "user.findByDateOfBirth", query = "SELECT u FROM user u WHERE u.dateOfBirth = :dateOfBirth")
    , @NamedQuery(name = "user.findByRegisterDate", query = "SELECT u FROM user u WHERE u.registerDate = :registerDate")
    , @NamedQuery(name = "user.findByPhoneNumber", query = "SELECT u FROM user u WHERE u.phoneNumber = :phoneNumber")
    , @NamedQuery(name = "user.findByRoleID", query = "SELECT u FROM user u WHERE u.roleID = :roleID")
    , @NamedQuery(name = "user.findByGender", query = "SELECT u FROM user u WHERE u.gender = :gender")
    , @NamedQuery(name = "user.findByStatus", query = "SELECT u FROM user u WHERE u.status = :status")
    , @NamedQuery(name = "user.findByPrimayryAddress", query = "SELECT u FROM user u WHERE u.primayryAddress = :primayryAddress")
    , @NamedQuery(name = "user.findBySencondAddress", query = "SELECT u FROM user u WHERE u.sencondAddress = :sencondAddress")})
public class user implements Serializable {

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
    private Integer phoneNumber;
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
    private List<role> roleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<tracking> trackingList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private professional professional;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private List<project> projectList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private List<story> storyList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private List<orders> ordersList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private List<ideabook> ideabookList;
    @JoinColumn(name = "cityCode", referencedColumnName = "cityCode", nullable = false)
    @ManyToOne(optional = false)
    private Tlcity cityCode;

    public user() {
    }

    public user(Integer userID) {
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

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
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
    public List<role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<role> roleList) {
        this.roleList = roleList;
    }

    @XmlTransient
    public List<tracking> getTrackingList() {
        return trackingList;
    }

    public void setTrackingList(List<tracking> trackingList) {
        this.trackingList = trackingList;
    }

    public professional getProfessional() {
        return professional;
    }

    public void setProfessional(professional professional) {
        this.professional = professional;
    }

    @XmlTransient
    public List<project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<project> projectList) {
        this.projectList = projectList;
    }

    @XmlTransient
    public List<story> getStoryList() {
        return storyList;
    }

    public void setStoryList(List<story> storyList) {
        this.storyList = storyList;
    }

    @XmlTransient
    public List<orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<orders> ordersList) {
        this.ordersList = ordersList;
    }

    @XmlTransient
    public List<ideabook> getIdeabookList() {
        return ideabookList;
    }

    public void setIdeabookList(List<ideabook> ideabookList) {
        this.ideabookList = ideabookList;
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
        if (!(object instanceof user)) {
            return false;
        }
        user other = (user) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.user[ userID=" + userID + " ]";
    }
    
}
