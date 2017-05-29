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
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findByUserID", query = "SELECT u FROM User u WHERE u.userID = :userID")
    , @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
    , @NamedQuery(name = "User.findByLikeEmail", query = "SELECT u FROM User u WHERE u.email LIKE :email")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByFirstname", query = "SELECT u FROM User u WHERE u.firstname = :firstname")
    , @NamedQuery(name = "User.findByLastname", query = "SELECT u FROM User u WHERE u.lastname = :lastname")
    , @NamedQuery(name = "User.findByDateOfBirth", query = "SELECT u FROM User u WHERE u.dateOfBirth = :dateOfBirth")
    , @NamedQuery(name = "User.findByRegisterDate", query = "SELECT u FROM User u WHERE u.registerDate = :registerDate")
    , @NamedQuery(name = "User.findByPhoneNumber", query = "SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber")
    , @NamedQuery(name = "User.findByGender", query = "SELECT u FROM User u WHERE u.gender = :gender")
    , @NamedQuery(name = "User.findByStatus", query = "SELECT u FROM User u WHERE u.status = :status")
    , @NamedQuery(name = "User.findByPrimayryAddress", query = "SELECT u FROM User u WHERE u.primayryAddress = :primayryAddress")
    , @NamedQuery(name = "User.findBySencondAddress", query = "SELECT u FROM User u WHERE u.sencondAddress = :sencondAddress")})
public class User implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Tracking> trackingList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Professional professional;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private List<Project> projectList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private List<Story> storyList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private List<Orders> ordersList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private List<Ideabook> ideabookList;
    @JoinColumn(name = "cityCode", referencedColumnName = "cityCode", nullable = false)
    @ManyToOne(optional = false)
    private City cityCode;
    @JoinColumn(name = "roleID", referencedColumnName = "roleID", nullable = false)
    @ManyToOne(optional = false)
    private Role roleID;

    public User() {
    }

    public User(Integer userID) {
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
    public List<Tracking> getTrackingList() {
        return trackingList;
    }

    public void setTrackingList(List<Tracking> trackingList) {
        this.trackingList = trackingList;
    }

    public Professional getProfessional() {
        return professional;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    @XmlTransient
    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    @XmlTransient
    public List<Story> getStoryList() {
        return storyList;
    }

    public void setStoryList(List<Story> storyList) {
        this.storyList = storyList;
    }

    @XmlTransient
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    @XmlTransient
    public List<Ideabook> getIdeabookList() {
        return ideabookList;
    }

    public void setIdeabookList(List<Ideabook> ideabookList) {
        this.ideabookList = ideabookList;
    }

    public City getCityCode() {
        return cityCode;
    }

    public void setCityCode(City cityCode) {
        this.cityCode = cityCode;
    }

    public Role getRoleID() {
        return roleID;
    }

    public void setRoleID(Role roleID) {
        this.roleID = roleID;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.User[ userID=" + userID + " ]";
    }
    
}
