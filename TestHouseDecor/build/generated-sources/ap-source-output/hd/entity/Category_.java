package hd.entity;

import hd.entity.IdeaBookPhoto;
import hd.entity.ProductPhoto;
import hd.entity.Tracking;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-09T11:58:44")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile CollectionAttribute<Category, IdeaBookPhoto> ideaBookPhotoCollection;
    public static volatile CollectionAttribute<Category, ProductPhoto> productPhotoCollection;
    public static volatile CollectionAttribute<Category, Tracking> trackingCollection;
    public static volatile SingularAttribute<Category, String> categoryName;
    public static volatile SingularAttribute<Category, Integer> categoryId;

}