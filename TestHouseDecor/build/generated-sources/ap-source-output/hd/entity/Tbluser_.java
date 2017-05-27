package hd.entity;

import hd.entity.Tblideabook;
import hd.entity.Tblorder;
import hd.entity.Tblprofessional;
import hd.entity.Tblproject;
import hd.entity.Tblrole;
import hd.entity.Tblstory;
import hd.entity.Tbltracking;
import hd.entity.Tlcity;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T17:21:06")
@StaticMetamodel(Tbluser.class)
public class Tbluser_ { 

    public static volatile SingularAttribute<Tbluser, String> firstname;
    public static volatile SingularAttribute<Tbluser, Boolean> gender;
    public static volatile SingularAttribute<Tbluser, Integer> roleID;
    public static volatile SingularAttribute<Tbluser, Tlcity> cityCode;
    public static volatile CollectionAttribute<Tbluser, Tbltracking> tbltrackingCollection;
    public static volatile SingularAttribute<Tbluser, Tblprofessional> tblprofessional;
    public static volatile SingularAttribute<Tbluser, String> sencondAddress;
    public static volatile SingularAttribute<Tbluser, Date> dateOfBirth;
    public static volatile SingularAttribute<Tbluser, Integer> userID;
    public static volatile SingularAttribute<Tbluser, String> lastname;
    public static volatile SingularAttribute<Tbluser, String> aboutMe;
    public static volatile SingularAttribute<Tbluser, String> password;
    public static volatile SingularAttribute<Tbluser, String> phoneNumber;
    public static volatile CollectionAttribute<Tbluser, Tblstory> tblstoryCollection;
    public static volatile CollectionAttribute<Tbluser, Tblrole> tblroleCollection;
    public static volatile SingularAttribute<Tbluser, String> primayryAddress;
    public static volatile CollectionAttribute<Tbluser, Tblorder> tblorderCollection;
    public static volatile CollectionAttribute<Tbluser, Tblproject> tblprojectCollection;
    public static volatile CollectionAttribute<Tbluser, Tblideabook> tblideabookCollection;
    public static volatile SingularAttribute<Tbluser, String> email;
    public static volatile SingularAttribute<Tbluser, Date> registerDate;
    public static volatile SingularAttribute<Tbluser, Integer> status;

}