package hd.entity;

import hd.entity.Tblideabookphoto;
import hd.entity.Tblproductphoto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T17:21:06")
@StaticMetamodel(Tblstyle.class)
public class Tblstyle_ { 

    public static volatile CollectionAttribute<Tblstyle, Tblproductphoto> tblproductphotoCollection;
    public static volatile CollectionAttribute<Tblstyle, Tblideabookphoto> tblideabookphotoCollection;
    public static volatile SingularAttribute<Tblstyle, Integer> styleID;
    public static volatile SingularAttribute<Tblstyle, String> name;

}