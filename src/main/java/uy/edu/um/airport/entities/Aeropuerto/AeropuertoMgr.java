package uy.edu.um.airport.entities.Aeropuerto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class AeropuertoMgr {
    private EntityManagerFactory emf;

    public AeropuertoMgr() {
        emf = Persistence.createEntityManagerFactory("YourPersistenceUnitName"); // Reemplaza con el nombre de tu unidad de persistencia
    }

    public void crearAeropuerto(Aeropuerto aeropuerto) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(aeropuerto);
        em.getTransaction().commit();
        em.close();
    }

    public Aeropuerto obtenerAeropuerto(String codigo) {
        EntityManager em = emf.createEntityManager();
        Aeropuerto aeropuerto = em.find(Aeropuerto.class, codigo);
        em.close();
        return aeropuerto;
    }

    public List<Aeropuerto> obtenerTodosLosAeropuertos() {
        EntityManager em = emf.createEntityManager();
        List<Aeropuerto> aeropuertos = em.createQuery("SELECT a FROM Aeropuerto a", Aeropuerto.class).getResultList();
        em.close();
        return aeropuertos;
    }

    public void actualizarAeropuerto(Aeropuerto aeropuerto) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(aeropuerto);
        em.getTransaction().commit();
        em.close();
    }

    public void eliminarAeropuerto(String codigo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Aeropuerto aeropuerto = em.find(Aeropuerto.class, codigo);
        if (aeropuerto != null) {
            em.remove(aeropuerto);
        }
        em.getTransaction().commit();
        em.close();
    }
}
