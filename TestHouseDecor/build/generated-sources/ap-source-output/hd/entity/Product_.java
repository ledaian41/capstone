package hd.entity;

import hd.entity.OrderDetail;
import hd.entity.ProductPhoto;
import hd.entity.Promotion;
import hd.entity.PromotionDetail;
import hd.entity.SellerInfo;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-09T11:58:44")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, SellerInfo> sellerInfoUserId;
    public static volatile SingularAttribute<Product, Integer> quantity;
    public static volatile SingularAttribute<Product, String> productId;
    public static volatile CollectionAttribute<Product, OrderDetail> orderDetailCollection;
    public static volatile SingularAttribute<Product, String> descripsion;
    public static volatile SingularAttribute<Product, String> productName;
    public static volatile SingularAttribute<Product, String> barCode;
    public static volatile CollectionAttribute<Product, PromotionDetail> promotionDetailCollection;
    public static volatile SingularAttribute<Product, String> size;
    public static volatile SingularAttribute<Product, String> material;
    public static volatile SingularAttribute<Product, Float> price;
    public static volatile CollectionAttribute<Product, Promotion> promotionCollection;
    public static volatile CollectionAttribute<Product, ProductPhoto> productPhotoCollection;
    public static volatile SingularAttribute<Product, String> warranty;
    public static volatile SingularAttribute<Product, Integer> status;

}