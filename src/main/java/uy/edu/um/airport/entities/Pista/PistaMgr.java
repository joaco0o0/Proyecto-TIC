package uy.edu.um.airport.entities.Pista;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class PistaMgr {

    private EntityManagerFactory emf;

    public PistaMgr() {
        emf = Persistence.createEntityManagerFactory("tu_unidad_de_persistencia"); // Reemplaza con el nombre de tu unidad de persistencia
    }

    public void agregarPista(Pista pista) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(pista);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void eliminarPista(String codigo) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Pista pista = em.find(Pista.class, codigo);
            if (pista != null) {
                em.remove(pista);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Pista buscarPistaPorCodigo(String codigo) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Pista.class, codigo);
        } finally {
            em.close();
        }
    }

    public List<Pista> obtenerTodasLasPistas() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pista p", Pista.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void cerrar() {
        emf.close();
    }
}
