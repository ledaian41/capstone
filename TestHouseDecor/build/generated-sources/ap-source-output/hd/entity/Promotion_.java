package hd.entity;

import hd.entity.Product;
import hd.entity.PromotionDetail;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-09T11:58:44")
@StaticMetamodel(Promotion.class)
public class Promotion_ { 

    public static volatile CollectionAttribute<Promotion, PromotionDetail> promotionDetailCollection;
    public static volatile CollectionAttribute<Promotion, Product> productCollection;
    public static volatile SingularAttribute<Promotion, Date> endDate;
    public static volatile SingularAttribute<Promotion, String> name;
    public static volatile SingularAttribute<Promotion, String> description;
    public static volatile SingularAttribute<Promotion, Integer> id;
    public static volatile SingularAttribute<Promotion, Date> startDate;
    public static volatile SingularAttribute<Promotion, Integer> status;

}