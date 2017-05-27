package hd.entity;

import hd.entity.Tblcategory;
import hd.entity.Tblproduct;
import hd.entity.Tblstyle;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T17:21:06")
@StaticMetamodel(Tblproductphoto.class)
public class Tblproductphoto_ { 

    public static volatile SingularAttribute<Tblproductphoto, Tblstyle> tblStylestyleID;
    public static volatile SingularAttribute<Tblproductphoto, Integer> productPhotoID;
    public static volatile SingularAttribute<Tblproductphoto, Tblcategory> tblCategorycategoryID;
    public static volatile SingularAttribute<Tblproductphoto, Tblproduct> tblProductproductID;
    public static volatile SingularAttribute<Tblproductphoto, String> description;
    public static volatile SingularAttribute<Tblproductphoto, String> title;
    public static volatile SingularAttribute<Tblproductphoto, String> url;
    public static volatile SingularAttribute<Tblproductphoto, Integer> status;

}