package hd.entity;

import hd.entity.Tblproduct;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-24T13:30:34")
@StaticMetamodel(Tblpromotion.class)
public class Tblpromotion_ { 

    public static volatile SingularAttribute<Tblpromotion, Date> endDate;
    public static volatile SingularAttribute<Tblpromotion, String> name;
    public static volatile SingularAttribute<Tblpromotion, String> description;
    public static volatile SingularAttribute<Tblpromotion, Integer> id;
    public static volatile CollectionAttribute<Tblpromotion, Tblproduct> tblproductCollection;
    public static volatile SingularAttribute<Tblpromotion, Date> startDate;
    public static volatile SingularAttribute<Tblpromotion, Integer> status;

}