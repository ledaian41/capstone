package hd.entity;

import hd.entity.Tblorder;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T17:21:06")
@StaticMetamodel(Tblorderdetail.class)
public class Tblorderdetail_ { 

    public static volatile SingularAttribute<Tblorderdetail, Integer> quantity;
    public static volatile SingularAttribute<Tblorderdetail, Integer> productID;
    public static volatile SingularAttribute<Tblorderdetail, Tblorder> orderID;
    public static volatile SingularAttribute<Tblorderdetail, Float> price;
    public static volatile SingularAttribute<Tblorderdetail, Integer> storeID;
    public static volatile SingularAttribute<Tblorderdetail, Integer> orderDetailID;
    public static volatile SingularAttribute<Tblorderdetail, Integer> status;

}