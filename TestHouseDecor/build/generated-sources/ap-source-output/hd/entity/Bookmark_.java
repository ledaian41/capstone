package hd.entity;

import hd.entity.Project;
import hd.entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-09T11:58:44")
@StaticMetamodel(Bookmark.class)
public class Bookmark_ { 

    public static volatile SingularAttribute<Bookmark, Integer> bookmarkId;
    public static volatile SingularAttribute<Bookmark, Date> bookmarkDate;
    public static volatile SingularAttribute<Bookmark, Project> projectId;
    public static volatile SingularAttribute<Bookmark, User> userId;

}