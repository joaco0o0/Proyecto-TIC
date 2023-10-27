package uy.edu.um.airport.session;

import uy.edu.um.airport.entities.AeropuertoYAerolinea.AeropuertoYAerolineaId;
import uy.edu.um.airport.entities.Usuario.Usuario;

/**
 * Esta clase maneja la sesión del usuario logueado en la aplicación.
 */
public class Session {

    private static Session instance = null;
    private Usuario currentUser;

    // Constructor privado para seguir el patrón Singleton
    private Session() {
    }

    /**
     * Devuelve la instancia única de Session.
     *
     * @return instancia de Session.
     */
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    /**
     * Establece el usuario logueado.
     *
     * @param user Usuario a establecer como logueado.
     */
    public void setCurrentUser(Usuario user) {
        this.currentUser = user;
    }

    /**
     * Obtiene el usuario logueado.
     *
     * @return Usuario logueado.
     */
    public Usuario getCurrentUser() {
        return this.currentUser;
    }

    /**
     * Cierra la sesión del usuario actual.
     */
    public void logout() {
        this.currentUser = null;
    }


}
