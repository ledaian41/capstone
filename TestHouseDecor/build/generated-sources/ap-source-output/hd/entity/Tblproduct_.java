package hd.entity;

import hd.entity.Tblproductphoto;
import hd.entity.Tblpromotion;
import hd.entity.Tblseller;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T17:21:06")
@StaticMetamodel(Tblproduct.class)
public class Tblproduct_ { 

    public static volatile SingularAttribute<Tblproduct, Integer> quantity;
    public static volatile SingularAttribute<Tblproduct, Integer> productID;
    public static volatile CollectionAttribute<Tblproduct, Tblpromotion> tblpromotionCollection;
    public static volatile SingularAttribute<Tblproduct, String> descripsion;
    public static volatile SingularAttribute<Tblproduct, String> productName;
    public static volatile SingularAttribute<Tblproduct, String> barCode;
    public static volatile CollectionAttribute<Tblproduct, Tblproductphoto> tblproductphotoCollection;
    public static volatile SingularAttribute<Tblproduct, Tblseller> tblSelleruserID;
    public static volatile SingularAttribute<Tblproduct, String> size;
    public static volatile SingularAttribute<Tblproduct, String> material;
    public static volatile SingularAttribute<Tblproduct, Float> price;
    public static volatile SingularAttribute<Tblproduct, String> warranty;
    public static volatile SingularAttribute<Tblproduct, Integer> status;

}