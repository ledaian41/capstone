package hd.entity;

import hd.entity.Tblideabookphoto;
import hd.entity.Tbluser;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T17:21:06")
@StaticMetamodel(Tblideabook.class)
public class Tblideabook_ { 

    public static volatile CollectionAttribute<Tblideabook, Tblideabookphoto> tblideabookphotoCollection;
    public static volatile SingularAttribute<Tblideabook, Integer> ideaBookID;
    public static volatile SingularAttribute<Tblideabook, String> description;
    public static volatile SingularAttribute<Tblideabook, Integer> isPublic;
    public static volatile SingularAttribute<Tblideabook, String> title;
    public static volatile SingularAttribute<Tblideabook, Tbluser> userID;
    public static volatile SingularAttribute<Tblideabook, String> status;

}