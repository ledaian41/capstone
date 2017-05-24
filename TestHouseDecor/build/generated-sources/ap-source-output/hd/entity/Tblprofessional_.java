package hd.entity;

import hd.entity.Tblseller;
import hd.entity.Tbltypeofwork;
import hd.entity.Tbluser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-24T13:30:34")
@StaticMetamodel(Tblprofessional.class)
public class Tblprofessional_ { 

    public static volatile SingularAttribute<Tblprofessional, String> address;
    public static volatile SingularAttribute<Tblprofessional, String> nameProfessional;
    public static volatile SingularAttribute<Tblprofessional, Tbltypeofwork> tblTypeOfWorkid;
    public static volatile SingularAttribute<Tblprofessional, Tblseller> tblseller;
    public static volatile SingularAttribute<Tblprofessional, Integer> userID;
    public static volatile SingularAttribute<Tblprofessional, Tbluser> tbluser;

}