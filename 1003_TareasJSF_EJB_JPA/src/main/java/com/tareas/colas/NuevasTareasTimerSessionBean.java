package com.tareas.colas;

import com.tareas.servicios.TareasServiceLocal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;

import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@Stateless
public class NuevasTareasTimerSessionBean {

    @Resource(mappedName = "java:app/jms/TareasConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "java:app/jms/colaNuevasTareas")
    private Queue cola;


    
 //   @Schedule(hour = "*", minute = "*/1", persistent = false)
    public void myTimer() {
        try {
          //  System.out.println("Nueva Tarea: " + new Date());
            
            String descripcion = "Tarea " + (int) (Math.random() * 100 );
            System.out.println("envio el mensaje " + descripcion);
            
            Connection con = connectionFactory.createConnection();
            Session sesion = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            TextMessage msg = sesion.createTextMessage(descripcion);
            MessageProducer productor = sesion.createProducer(cola);
            
            productor.send(msg);
            
            productor.close();
            sesion.close();
            con.close();
            
        } catch (JMSException ex) {
            Logger.getLogger(NuevasTareasTimerSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
