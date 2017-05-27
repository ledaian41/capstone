package hd.entity;

import hd.entity.Tblideabookphoto;
import hd.entity.Tblproductphoto;
import hd.entity.Tbltracking;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T17:21:06")
@StaticMetamodel(Tblcategory.class)
public class Tblcategory_ { 

    public static volatile CollectionAttribute<Tblcategory, Tblproductphoto> tblproductphotoCollection;
    public static volatile CollectionAttribute<Tblcategory, Tblideabookphoto> tblideabookphotoCollection;
    public static volatile CollectionAttribute<Tblcategory, Tbltracking> tbltrackingCollection;
    public static volatile SingularAttribute<Tblcategory, String> categoryName;
    public static volatile SingularAttribute<Tblcategory, Integer> categoryID;

}