package hd.entity;

import hd.entity.Tblcategory;
import hd.entity.TbltrackingPK;
import hd.entity.Tbluser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T17:21:06")
@StaticMetamodel(Tbltracking.class)
public class Tbltracking_ { 

    public static volatile SingularAttribute<Tbltracking, Tblcategory> tblcategory;
    public static volatile SingularAttribute<Tbltracking, Date> lastUpdate;
    public static volatile SingularAttribute<Tbltracking, Integer> count;
    public static volatile SingularAttribute<Tbltracking, TbltrackingPK> tbltrackingPK;
    public static volatile SingularAttribute<Tbltracking, Tbluser> tbluser;

}