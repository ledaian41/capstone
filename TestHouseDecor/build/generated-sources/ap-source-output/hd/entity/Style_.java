package hd.entity;

import hd.entity.IdeaBookPhoto;
import hd.entity.ProductPhoto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-09T11:58:44")
@StaticMetamodel(Style.class)
public class Style_ { 

    public static volatile SingularAttribute<Style, Integer> styleId;
    public static volatile CollectionAttribute<Style, IdeaBookPhoto> ideaBookPhotoCollection;
    public static volatile SingularAttribute<Style, String> name;
    public static volatile CollectionAttribute<Style, ProductPhoto> productPhotoCollection;

}