package entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-07T08:11:46")
@StaticMetamodel(ClienteOrden.class)
public class ClienteOrden_ { 

    public static volatile SingularAttribute<ClienteOrden, Date> fecha;
    public static volatile SingularAttribute<ClienteOrden, Integer> cliente;
    public static volatile SingularAttribute<ClienteOrden, Integer> idclienteOrden;
    public static volatile SingularAttribute<ClienteOrden, BigDecimal> cantidad;

}