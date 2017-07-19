package hd.entity;

import hd.entity.IdeaBook;
import hd.entity.IdeaBookPhoto;
import hd.entity.IdeaBookPhotoRefPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-09T11:58:44")
@StaticMetamodel(IdeaBookPhotoRef.class)
public class IdeaBookPhotoRef_ { 

    public static volatile SingularAttribute<IdeaBookPhotoRef, IdeaBookPhotoRefPK> ideaBookPhotoRefPK;
    public static volatile SingularAttribute<IdeaBookPhotoRef, IdeaBookPhoto> ideaBookPhoto;
    public static volatile SingularAttribute<IdeaBookPhotoRef, String> comment;
    public static volatile SingularAttribute<IdeaBookPhotoRef, IdeaBook> ideaBook;

}