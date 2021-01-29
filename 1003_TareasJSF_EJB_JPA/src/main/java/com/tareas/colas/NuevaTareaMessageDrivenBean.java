
package com.tareas.colas;

import com.tareas.servicios.TareasServiceLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import com.tareas.entidades.Tarea;
import com.tareas.entidades.Usuario;
/**
 *
 * @author ususario
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/jms/colaNuevasTareas"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class NuevaTareaMessageDrivenBean implements MessageListener {
    
    @EJB
    private TareasServiceLocal tareasService;
    
    public NuevaTareaMessageDrivenBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage msg = (TextMessage) message;
            System.out.println("... recibo el mensaje " + msg.getText());
            
            Tarea nueva = new Tarea();
            nueva.setDescripcion(msg.getText());
            nueva.setEstado("TO DO");
            Usuario u = new Usuario();
            u.setIdUsuario(1);
            nueva.setUsuario(u);
            
            tareasService.alta(nueva);
            
        } catch (JMSException ex) {
            Logger.getLogger(NuevaTareaMessageDrivenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
