/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cuk3t
 */
@Entity
@Table(name = "promotion_detail", catalog = "house_decor", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PromotionDetail.findAll", query = "SELECT p FROM PromotionDetail p")
    , @NamedQuery(name = "PromotionDetail.findByPromotionDetailId", query = "SELECT p FROM PromotionDetail p WHERE p.promotionDetailId = :promotionDetailId")
    , @NamedQuery(name = "PromotionDetail.findByDiscount", query = "SELECT p FROM PromotionDetail p WHERE p.discount = :discount")})
public class PromotionDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "promotion_detail_id", nullable = false)
    private Integer promotionDetailId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "discount", precision = 12)
    private Float discount;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    @ManyToOne(optional = false)
    private Product productId;
    @JoinColumn(name = "promotion_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Promotion promotionId;

    public PromotionDetail() {
    }

    public PromotionDetail(Integer promotionDetailId) {
        this.promotionDetailId = promotionDetailId;
    }

	public PromotionDetail(Integer promotionDetailId, float discount) {
        this.promotionDetailId = promotionDetailId;
        this.discount = discount;
    }
    public Integer getPromotionDetailId() {
        return promotionDetailId;
    }

    public void setPromotionDetailId(Integer promotionDetailId) {
        this.promotionDetailId = promotionDetailId;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Promotion getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Promotion promotionId) {
        this.promotionId = promotionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promotionDetailId != null ? promotionDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PromotionDetail)) {
            return false;
        }
        PromotionDetail other = (PromotionDetail) object;
        if ((this.promotionDetailId == null && other.promotionDetailId != null) || (this.promotionDetailId != null && !this.promotionDetailId.equals(other.promotionDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entity.PromotionDetail[ promotionDetailId=" + promotionDetailId + " ]";
    }
    
}
