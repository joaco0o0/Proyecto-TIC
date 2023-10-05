package uy.edu.um.airport.entities.Terminal;

import jakarta.persistence.EntityManager;
        import jakarta.persistence.EntityManagerFactory;
        import jakarta.persistence.Persistence;
        import java.util.List;

public class TerminalMgr {
    private EntityManagerFactory emf;

    public TerminalMgr() {
        emf = Persistence.createEntityManagerFactory("YourPersistenceUnitName"); // Reemplaza con el nombre de tu unidad de persistencia
    }

    public void crearTerminal(Terminal terminal) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(terminal);
        em.getTransaction().commit();
        em.close();
    }

    public Terminal obtenerTerminal(String codigo) {
        EntityManager em = emf.createEntityManager();
        Terminal terminal = em.find(Terminal.class, codigo);
        em.close();
        return terminal;
    }

    public List<Terminal> obtenerTodasLasTerminales() {
        EntityManager em = emf.createEntityManager();
        List<Terminal> terminales = em.createQuery("SELECT t FROM Terminal t", Terminal.class).getResultList();
        em.close();
        return terminales;
    }

    public void actualizarTerminal(Terminal terminal) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(terminal);
        em.getTransaction().commit();
        em.close();
    }

    public void eliminarTerminal(String codigo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Terminal terminal = em.find(Terminal.class, codigo);
        if (terminal != null) {
            em.remove(terminal);
        }
        em.getTransaction().commit();
        em.close();
    }
}
