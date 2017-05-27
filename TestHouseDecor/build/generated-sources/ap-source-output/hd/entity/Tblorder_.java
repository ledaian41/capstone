package hd.entity;

import hd.entity.Tblorderdetail;
import hd.entity.Tbluser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T17:21:06")
@StaticMetamodel(Tblorder.class)
public class Tblorder_ { 

    public static volatile SingularAttribute<Tblorder, Integer> orderID;
    public static volatile SingularAttribute<Tblorder, Date> createdTime;
    public static volatile SingularAttribute<Tblorder, Tbluser> userID;
    public static volatile CollectionAttribute<Tblorder, Tblorderdetail> tblorderdetailCollection;
    public static volatile SingularAttribute<Tblorder, Integer> status;

}