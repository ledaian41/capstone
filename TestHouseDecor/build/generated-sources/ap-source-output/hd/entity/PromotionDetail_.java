package hd.entity;

import hd.entity.Product;
import hd.entity.Promotion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-09T11:58:44")
@StaticMetamodel(PromotionDetail.class)
public class PromotionDetail_ { 

    public static volatile SingularAttribute<PromotionDetail, Product> productId;
    public static volatile SingularAttribute<PromotionDetail, Float> price;
    public static volatile SingularAttribute<PromotionDetail, Integer> promotionDetailId;
    public static volatile SingularAttribute<PromotionDetail, Promotion> promotionId;

}