
package com.tareas.colas;

import javax.ejb.Singleton;
import javax.jms.JMSConnectionFactoryDefinition;
import javax.jms.JMSDestinationDefinition;

@JMSDestinationDefinition(
    name = "java:app/jms/colaNuevasTareas",
    interfaceName = "javax.jms.Queue",
    destinationName = "colaNuevasTareas"
  )
@JMSConnectionFactoryDefinition(
    name="java:app/jms/TareasConnectionFactory"
)
@Singleton
public class InicialiarColaSingletonSessionBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
