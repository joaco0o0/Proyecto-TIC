package uy.edu.um.airport.entities.Equipaje;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Vuelo.Vuelo;
import uy.edu.um.airport.persistence.EquipajeRepository;

@Service
public class EquipajeMgr {

    @Autowired
    private EquipajeRepository equipajeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public void addEquipaje(Equipaje equipaje) {
        equipajeRepository.save(equipaje);
    }

    public void deleteEquipaje(Equipaje equipaje) {
        equipajeRepository.delete(equipaje);
    }

    public Equipaje findEquipaje(String descripcion, String vueloCodigo, String usuarioId) {
        try {
            return entityManager.createQuery(
                            "SELECT e FROM Equipaje e " +
                                    "WHERE e.descripcion = :descripcion " +
                                    "AND e.vuelo.codigo = :vueloCodigo " +
                                    "AND e.usuario.id = :usuarioId", Equipaje.class)
                    .setParameter("descripcion", descripcion)
                    .setParameter("vueloCodigo", vueloCodigo)
                    .setParameter("usuarioId", usuarioId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Equipaje findEquipajeById(Long equipajeId) {
        return equipajeRepository.findById(equipajeId).orElse(null);
    }

    public Vuelo findVueloByCodigo(String vueloCodigo) {
        try {
            return entityManager.createQuery(
                            "SELECT v FROM Vuelo v WHERE v.codigo = :codigo", Vuelo.class)
                    .setParameter("codigo", vueloCodigo)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // No se encontró el vuelo
        }
    }

    public Usuario findUsuarioById(String usuarioId) {
        try {
            return entityManager.createQuery(
                            "SELECT u FROM Usuario u WHERE u.id = :id", Usuario.class)
                    .setParameter("id", usuarioId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // No se encontró el usuario
        }
    }
}
