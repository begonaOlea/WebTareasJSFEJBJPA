package com.tareas.servicios;

import com.tareas.entidades.Tarea;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class TareasService implements TareasServiceLocal {

    @PersistenceContext(unitName = "TareasPU")
    private EntityManager em;

    @Override
    public Collection<Tarea> getTareas(Integer idUsuario, String estado) {
        Query query = em.createNamedQuery("Tarea.findByEstadoAndUserId");
        query.setParameter("estado", estado);
        query.setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }

    @Override
    public void alta(Tarea tarea) {
       em.persist(tarea);
    }

    
}
