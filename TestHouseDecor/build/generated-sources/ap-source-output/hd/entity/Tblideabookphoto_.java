package hd.entity;

import hd.entity.Tblcategory;
import hd.entity.Tblideabook;
import hd.entity.Tblproject;
import hd.entity.Tblstyle;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T17:21:06")
@StaticMetamodel(Tblideabookphoto.class)
public class Tblideabookphoto_ { 

    public static volatile SingularAttribute<Tblideabookphoto, Tblstyle> tblStylestyleID;
    public static volatile SingularAttribute<Tblideabookphoto, Tblcategory> tblCategorycategoryID;
    public static volatile SingularAttribute<Tblideabookphoto, Integer> photoID;
    public static volatile SingularAttribute<Tblideabookphoto, String> description;
    public static volatile SingularAttribute<Tblideabookphoto, String> tilte;
    public static volatile CollectionAttribute<Tblideabookphoto, Tblideabook> tblideabookCollection;
    public static volatile SingularAttribute<Tblideabookphoto, Tblproject> tblProjectprojectID;
    public static volatile SingularAttribute<Tblideabookphoto, String> url;
    public static volatile SingularAttribute<Tblideabookphoto, Integer> status;

}