package hd.entity;

import hd.entity.Category;
import hd.entity.Product;
import hd.entity.Style;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-09T11:58:44")
@StaticMetamodel(ProductPhoto.class)
public class ProductPhoto_ { 

    public static volatile SingularAttribute<ProductPhoto, Product> productId;
    public static volatile SingularAttribute<ProductPhoto, Style> styleId;
    public static volatile SingularAttribute<ProductPhoto, Integer> photoId;
    public static volatile SingularAttribute<ProductPhoto, String> description;
    public static volatile SingularAttribute<ProductPhoto, String> title;
    public static volatile SingularAttribute<ProductPhoto, Date> uploadTime;
    public static volatile SingularAttribute<ProductPhoto, String> url;
    public static volatile SingularAttribute<ProductPhoto, Category> categoryId;
    public static volatile SingularAttribute<ProductPhoto, Integer> status;

}