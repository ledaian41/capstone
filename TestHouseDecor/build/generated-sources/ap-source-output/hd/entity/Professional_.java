package hd.entity;

import hd.entity.SellerInfo;
import hd.entity.TypeOfWork;
import hd.entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-09T11:58:44")
@StaticMetamodel(Professional.class)
public class Professional_ { 

    public static volatile SingularAttribute<Professional, String> address;
    public static volatile SingularAttribute<Professional, TypeOfWork> typeOfWorkId;
    public static volatile SingularAttribute<Professional, String> professionalName;
    public static volatile SingularAttribute<Professional, Integer> userId;
    public static volatile SingularAttribute<Professional, User> user;
    public static volatile SingularAttribute<Professional, SellerInfo> sellerInfo;

}