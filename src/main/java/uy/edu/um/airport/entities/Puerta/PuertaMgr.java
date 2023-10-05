package uy.edu.um.airport.entities.Puerta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class PuertaMgr {
    private EntityManagerFactory emf;

    public PuertaMgr() {
        emf = Persistence.createEntityManagerFactory("YourPersistenceUnitName"); // Reemplaza con el nombre de tu unidad de persistencia
    }

    public void crearPuerta(Puerta puerta) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(puerta);
        em.getTransaction().commit();
        em.close();
    }

    public Puerta obtenerPuerta(Long id) {
        EntityManager em = emf.createEntityManager();
        Puerta puerta = em.find(Puerta.class, id);
        em.close();
        return puerta;
    }

    public List<Puerta> obtenerTodasLasPuertas() {
        EntityManager em = emf.createEntityManager();
        List<Puerta> puertas = em.createQuery("SELECT p FROM Puerta p", Puerta.class).getResultList();
        em.close();
        return puertas;
    }

    public void actualizarPuerta(Puerta puerta) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(puerta);
        em.getTransaction().commit();
        em.close();
    }

    public void eliminarPuerta(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Puerta puerta = em.find(Puerta.class, id);
        if (puerta != null) {
            em.remove(puerta);
        }
        em.getTransaction().commit();
        em.close();
    }
}
