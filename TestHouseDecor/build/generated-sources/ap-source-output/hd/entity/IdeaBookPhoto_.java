package hd.entity;

import hd.entity.Category;
import hd.entity.IdeaBook;
import hd.entity.IdeaBookPhotoRef;
import hd.entity.Project;
import hd.entity.Style;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-09T11:58:44")
@StaticMetamodel(IdeaBookPhoto.class)
public class IdeaBookPhoto_ { 

    public static volatile CollectionAttribute<IdeaBookPhoto, IdeaBookPhotoRef> ideaBookPhotoRefCollection;
    public static volatile SingularAttribute<IdeaBookPhoto, IdeaBook> ideaBookId;
    public static volatile SingularAttribute<IdeaBookPhoto, Style> styleId;
    public static volatile SingularAttribute<IdeaBookPhoto, Integer> photoId;
    public static volatile SingularAttribute<IdeaBookPhoto, String> description;
    public static volatile SingularAttribute<IdeaBookPhoto, String> tilte;
    public static volatile SingularAttribute<IdeaBookPhoto, Project> projectId;
    public static volatile SingularAttribute<IdeaBookPhoto, String> url;
    public static volatile SingularAttribute<IdeaBookPhoto, Category> categoryId;
    public static volatile SingularAttribute<IdeaBookPhoto, Integer> status;

}