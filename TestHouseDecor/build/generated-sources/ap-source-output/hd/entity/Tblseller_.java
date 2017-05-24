package hd.entity;

import hd.entity.Tblproduct;
import hd.entity.Tblprofessional;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-24T13:30:34")
@StaticMetamodel(Tblseller.class)
public class Tblseller_ { 

    public static volatile SingularAttribute<Tblseller, String> storeAddress;
    public static volatile SingularAttribute<Tblseller, Integer> phone;
    public static volatile SingularAttribute<Tblseller, Date> dueDate;
    public static volatile SingularAttribute<Tblseller, Tblprofessional> tblprofessional;
    public static volatile SingularAttribute<Tblseller, String> sellerName;
    public static volatile SingularAttribute<Tblseller, String> taxNumber;
    public static volatile CollectionAttribute<Tblseller, Tblproduct> tblproductCollection;
    public static volatile SingularAttribute<Tblseller, Integer> userID;
    public static volatile SingularAttribute<Tblseller, Date> startDate;

}