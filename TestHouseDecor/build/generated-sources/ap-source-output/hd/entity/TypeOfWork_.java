package hd.entity;

import hd.entity.Professional;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-09T11:58:44")
@StaticMetamodel(TypeOfWork.class)
public class TypeOfWork_ { 

    public static volatile SingularAttribute<TypeOfWork, String> name;
    public static volatile SingularAttribute<TypeOfWork, Integer> id;
    public static volatile CollectionAttribute<TypeOfWork, Professional> professionalCollection;

}