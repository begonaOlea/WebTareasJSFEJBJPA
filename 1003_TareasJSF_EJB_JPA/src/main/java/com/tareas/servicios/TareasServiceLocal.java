
package com.tareas.servicios;

import com.tareas.entidades.Tarea;
import java.util.Collection;
import javax.ejb.Local;

@Local
public interface TareasServiceLocal {
    
    public Collection<Tarea> getTareas(Integer idUsuario, String estado);
    public void alta(Tarea tarea);
    
}
