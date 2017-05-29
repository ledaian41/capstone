package hd.entity;

import hd.entity.Tbluser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T17:21:06")
@StaticMetamodel(Tblrole.class)
public class Tblrole_ { 

    public static volatile SingularAttribute<Tblrole, Integer> roleID;
    public static volatile SingularAttribute<Tblrole, String> roleName;
    public static volatile SingularAttribute<Tblrole, Tbluser> userID;

}