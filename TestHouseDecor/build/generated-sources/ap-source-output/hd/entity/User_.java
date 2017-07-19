package hd.entity;

import hd.entity.Bookmark;
import hd.entity.City;
import hd.entity.IdeaBook;
import hd.entity.Orders;
import hd.entity.Professional;
import hd.entity.Project;
import hd.entity.Role;
import hd.entity.Story;
import hd.entity.Tracking;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-09T11:58:44")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> firstname;
    public static volatile SingularAttribute<User, Boolean> gender;
    public static volatile SingularAttribute<User, City> cityCode;
    public static volatile SingularAttribute<User, Role> roleId;
    public static volatile SingularAttribute<User, String> sencondAddress;
    public static volatile CollectionAttribute<User, Story> storyCollection;
    public static volatile SingularAttribute<User, Date> dateOfBirth;
    public static volatile CollectionAttribute<User, Orders> ordersCollection;
    public static volatile SingularAttribute<User, Integer> userId;
    public static volatile SingularAttribute<User, String> lastname;
    public static volatile SingularAttribute<User, String> aboutMe;
    public static volatile SingularAttribute<User, Professional> professional;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> phoneNumber;
    public static volatile CollectionAttribute<User, Bookmark> bookmarkCollection;
    public static volatile CollectionAttribute<User, Tracking> trackingCollection;
    public static volatile SingularAttribute<User, String> primaryAddress;
    public static volatile CollectionAttribute<User, Project> projectCollection;
    public static volatile CollectionAttribute<User, IdeaBook> ideaBookCollection;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, Date> registerDate;
    public static volatile SingularAttribute<User, Integer> status;

}