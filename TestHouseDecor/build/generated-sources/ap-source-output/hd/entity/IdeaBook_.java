package hd.entity;

import hd.entity.IdeaBookPhoto;
import hd.entity.IdeaBookPhotoRef;
import hd.entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-09T11:58:44")
@StaticMetamodel(IdeaBook.class)
public class IdeaBook_ { 

    public static volatile CollectionAttribute<IdeaBook, IdeaBookPhotoRef> ideaBookPhotoRefCollection;
    public static volatile SingularAttribute<IdeaBook, Integer> ideaBookId;
    public static volatile CollectionAttribute<IdeaBook, IdeaBookPhoto> ideaBookPhotoCollection;
    public static volatile SingularAttribute<IdeaBook, String> description;
    public static volatile SingularAttribute<IdeaBook, Boolean> isPublic;
    public static volatile SingularAttribute<IdeaBook, String> title;
    public static volatile SingularAttribute<IdeaBook, User> userId;
    public static volatile SingularAttribute<IdeaBook, Integer> status;

}