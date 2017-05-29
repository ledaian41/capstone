package hd.entity;

import hd.entity.Tbluser;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T17:21:06")
@StaticMetamodel(Tlcity.class)
public class Tlcity_ { 

    public static volatile SingularAttribute<Tlcity, String> cityName;
    public static volatile SingularAttribute<Tlcity, String> cityCode;
    public static volatile CollectionAttribute<Tlcity, Tbluser> tbluserCollection;

}