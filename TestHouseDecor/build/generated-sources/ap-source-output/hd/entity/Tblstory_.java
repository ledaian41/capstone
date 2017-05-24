package hd.entity;

import hd.entity.Tbluser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-24T13:30:35")
@StaticMetamodel(Tblstory.class)
public class Tblstory_ { 

    public static volatile SingularAttribute<Tblstory, Integer> storyID;
    public static volatile SingularAttribute<Tblstory, String> title;
    public static volatile SingularAttribute<Tblstory, Tbluser> userID;
    public static volatile SingularAttribute<Tblstory, String> content;
    public static volatile SingularAttribute<Tblstory, Integer> status;

}