package hd.entity;

import hd.entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-09T11:58:44")
@StaticMetamodel(Story.class)
public class Story_ { 

    public static volatile SingularAttribute<Story, Integer> storyId;
    public static volatile SingularAttribute<Story, String> title;
    public static volatile SingularAttribute<Story, User> userId;
    public static volatile SingularAttribute<Story, String> content;
    public static volatile SingularAttribute<Story, Integer> status;

}