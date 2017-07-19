package hd.entity;

import hd.entity.Product;
import hd.entity.Professional;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-09T11:58:44")
@StaticMetamodel(SellerInfo.class)
public class SellerInfo_ { 

    public static volatile CollectionAttribute<SellerInfo, Product> productCollection;
    public static volatile SingularAttribute<SellerInfo, String> storeAddress;
    public static volatile SingularAttribute<SellerInfo, String> phone;
    public static volatile SingularAttribute<SellerInfo, Date> dueDate;
    public static volatile SingularAttribute<SellerInfo, String> sellerName;
    public static volatile SingularAttribute<SellerInfo, String> taxNumber;
    public static volatile SingularAttribute<SellerInfo, Integer> userId;
    public static volatile SingularAttribute<SellerInfo, Date> startDate;
    public static volatile SingularAttribute<SellerInfo, Professional> professional;

}