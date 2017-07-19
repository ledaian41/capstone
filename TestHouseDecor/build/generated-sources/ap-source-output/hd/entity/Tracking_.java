package hd.entity;

import hd.entity.Category;
import hd.entity.TrackingPK;
import hd.entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-09T11:58:44")
@StaticMetamodel(Tracking.class)
public class Tracking_ { 

    public static volatile SingularAttribute<Tracking, Date> lastUpdate;
    public static volatile SingularAttribute<Tracking, Integer> count;
    public static volatile SingularAttribute<Tracking, Category> category;
    public static volatile SingularAttribute<Tracking, User> user;
    public static volatile SingularAttribute<Tracking, TrackingPK> trackingPK;

}