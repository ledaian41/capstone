package hd.entity;

import hd.entity.Bookmark;
import hd.entity.IdeaBookPhoto;
import hd.entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-09T11:58:44")
@StaticMetamodel(Project.class)
public class Project_ { 

    public static volatile SingularAttribute<Project, String> website;
    public static volatile SingularAttribute<Project, String> address;
    public static volatile SingularAttribute<Project, Float> cost;
    public static volatile SingularAttribute<Project, String> keywords;
    public static volatile SingularAttribute<Project, Date> year;
    public static volatile CollectionAttribute<Project, Bookmark> bookmarkCollection;
    public static volatile CollectionAttribute<Project, IdeaBookPhoto> ideaBookPhotoCollection;
    public static volatile SingularAttribute<Project, String> projectName;
    public static volatile SingularAttribute<Project, Integer> projectId;
    public static volatile SingularAttribute<Project, User> userId;
    public static volatile SingularAttribute<Project, Integer> status;

}