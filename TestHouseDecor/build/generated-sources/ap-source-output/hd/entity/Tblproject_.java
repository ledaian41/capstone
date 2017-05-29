package hd.entity;

import hd.entity.Tblideabookphoto;
import hd.entity.Tbluser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T17:21:06")
@StaticMetamodel(Tblproject.class)
public class Tblproject_ { 

    public static volatile SingularAttribute<Tblproject, String> website;
    public static volatile CollectionAttribute<Tblproject, Tblideabookphoto> tblideabookphotoCollection;
    public static volatile SingularAttribute<Tblproject, String> address;
    public static volatile SingularAttribute<Tblproject, Float> cost;
    public static volatile SingularAttribute<Tblproject, String> keywords;
    public static volatile SingularAttribute<Tblproject, Date> year;
    public static volatile SingularAttribute<Tblproject, String> projectName;
    public static volatile SingularAttribute<Tblproject, Integer> projectID;
    public static volatile SingularAttribute<Tblproject, Tbluser> userID;
    public static volatile SingularAttribute<Tblproject, Integer> status;

}