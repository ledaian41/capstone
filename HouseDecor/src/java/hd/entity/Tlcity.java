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
@Table(name = "tlcity", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tlcity.findAll", query = "SELECT t FROM Tlcity t")
    , @NamedQuery(name = "Tlcity.findByCityCode", query = "SELECT t FROM Tlcity t WHERE t.cityCode = :cityCode")
    , @NamedQuery(name = "Tlcity.findByCityName", query = "SELECT t FROM Tlcity t WHERE t.cityName = :cityName")})
public class Tlcity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cityCode", nullable = false, length = 15)
    private String cityCode;
    @Column(name = "cityName", length = 105)
    private String cityName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cityCode")
    private List<user> userList;

    public Tlcity() {
    }

    public Tlcity(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @XmlTransient
    public List<user> getUserList() {
        return userList;
    }

    public void setUserList(List<user> userList) {
        this.userList = userList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cityCode != null ? cityCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tlcity)) {
            return false;
        }
        Tlcity other = (Tlcity) object;
        if ((this.cityCode == null && other.cityCode != null) || (this.cityCode != null && !this.cityCode.equals(other.cityCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.Tlcity[ cityCode=" + cityCode + " ]";
    }
    
}
