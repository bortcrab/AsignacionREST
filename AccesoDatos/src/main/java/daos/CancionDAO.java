/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.Conexion;
import conexion.IConexion;
import entidades.CancionEntidad;
import excepciones.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Clase que implementa la interfaz ICancionDAO y define los métodos para
 * realizar operaciones relacionadas con la entidad Cancion en la base de datos.
 *
 * @author Diego Valenzuela Parra - 00000247700
 */
public class CancionDAO implements ICancionDAO {

    private final IConexion conexion = new Conexion();
    private static final Logger logger = Logger.getLogger(CancionDAO.class.getName());

    /**
     * Método que inserta una cancion en la base de datos.
     *
     * @param cancion Canción a ser insertada.
     * @throws PersistenciaException si ocurre algún error.
     */
    @Override
    public void insertarCancion(CancionEntidad cancion) throws PersistenciaException {
        try {
            // Creamos un entity manager.
            EntityManager em = conexion.crearConexion();

            // Iniciamos la transacción.
            em.getTransaction().begin();

            // Mandamos a persistir la cancion.
            em.persist(cancion);

            // Hacemos el commit y cerramos el entity manager.
            em.getTransaction().commit();
            em.close();

            // Imprimimos un mensaje de que se insertó una canción.
            logger.log(Level.INFO, "Se ha insertando 1 cancion correctamente.");
        } catch (Exception e) {
            throw new PersistenciaException("Ocurrió algún error durante la inserción. Error " + e.getMessage());
        }
    }

    /**
     * Método que actualiza los datos de una cancion en la base de datos.
     *
     * @param cancion Canción a ser actualizada.
     * @throws PersistenciaException si ocurre algún error.
     */
    @Override
    public void actualizarCancion(CancionEntidad cancion) throws PersistenciaException {
        try {
            // Creamos un entity manager.
            EntityManager em = conexion.crearConexion();

            // Iniciamos la transacción.
            em.getTransaction().begin();

            // Adjuntamos la entidad al contexto de persistencia.
            CancionEntidad cancionManaged = em.find(CancionEntidad.class, cancion.getId());

            // Verificamos si la entidad existe en la base de datos antes de eliminarla.
            if (cancionManaged != null) {
                em.merge(cancion); // Eliminamos la entidad gestionada.
                em.getTransaction().commit();
                logger.log(Level.INFO, "Se ha eliminado la canción correctamente.");
            } else {
                em.getTransaction().rollback();
                throw new PersistenciaException("La entidad no existe en la base de datos.");
            }

            // Cerramos el entity manager.
            em.close();

            // Imprimimos un mensaje de que se actualizó una canción.
            logger.log(Level.INFO, "Se ha actualizado 1 cancion correctamente.");
        } catch (Exception e) {
            throw new PersistenciaException("Ocurrió algún error durante la actualización. Error " + e.getMessage());
        }
    }

    /**
     * Método que elimina una cancion de la base de datos.
     *
     * @param cancion Canción a ser borrada.
     * @throws PersistenciaException si ocurre algún error.
     */
    @Override
    public void borrarCancion(CancionEntidad cancion) throws PersistenciaException {
        try {
            // Creamos un entity manager.
            EntityManager em = conexion.crearConexion();

            // Iniciamos la transacción.
            em.getTransaction().begin();

            // Adjuntamos la entidad al contexto de persistencia.
            CancionEntidad cancionManaged = em.find(CancionEntidad.class, cancion.getId());

            // Verificamos si la entidad existe en la base de datos antes de eliminarla.
            if (cancionManaged != null) {
                em.remove(cancionManaged); // Eliminamos la entidad gestionada.
                em.getTransaction().commit();
                logger.log(Level.INFO, "Se ha eliminado la canción correctamente.");
            } else {
                em.getTransaction().rollback();
                throw new PersistenciaException("La entidad no existe en la base de datos.");
            }

            // Cerramos el entity manager.
            em.close();

            // Imprimimos un mensaje de que se actualizó una canción.
            logger.log(Level.INFO, "Se ha actualizado 1 cancion correctamente.");
        } catch (Exception e) {
            throw new PersistenciaException("Ocurrió algún error durante el borrado. Error " + e.getMessage());
        }
    }

    /**
     * Obtiene una canción dado un ID.
     *
     * @param id ID de la canción a buscar.
     * @return Canción encontrada.
     */
    @Override
    public CancionEntidad obtenerCancionPorID(Long id) throws PersistenciaException {
        try {
            // Creamos un entity manager.
            EntityManager em = conexion.crearConexion();

            // Mandamos a actualizar la canción.
            CancionEntidad cancion = em.find(CancionEntidad.class, id);

            // Cerramos el entity manager.
            em.close();

            // Imprimimos un mensaje de que se actualizó una canción.
            logger.log(Level.INFO, "Se ha actualizado 1 cancion correctamente.");

            // Retornamos la canción encontrada.
            return cancion;
        } catch (Exception e) {
            throw new PersistenciaException("Ocurrió algún error durante el borrado. Error " + e.getMessage());
        }
    }

    /**
     * Obtiene la lista completa de canciones registradas.
     *
     * @return Lista de todas las canciones registradas
     * @throws PersistenciaException si no encuentra a ninguna canción.
     */
    @Override
    public List<CancionEntidad> obtenerCanciones() throws PersistenciaException {
        try {
            // Creamos un entity manager.
            EntityManager em = conexion.crearConexion();

            // Creamos un objeto CriteriaBuilder.
            CriteriaBuilder cb = em.getCriteriaBuilder();

            // Creamos un CriteriaQuery para la entidad de Canción.
            CriteriaQuery<CancionEntidad> cq = cb.createQuery(CancionEntidad.class);

            // Define la entidad desde donde se hará la consulta de los datos.
            Root<CancionEntidad> rootEntry = cq.from(CancionEntidad.class);

            // Construye la consulta seleccionando todos los registros de la entidad.
            CriteriaQuery<CancionEntidad> all = cq.select(rootEntry);

            // Crea una consulta basada en el CriteriaQuery generada.
            TypedQuery<CancionEntidad> allQuery = em.createQuery(all);

            // Ejecuta la consulta y devuelve la lista de resultados.
            return allQuery.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Ocurrió algún error durante el borrado. Error " + e.getMessage());
        }
    }

    /**
     * Obtiene la lista completa de canciones de un mismo artista.
     *
     * @param artista Artista que hizo la canción.
     * @return Lista de todas las canciones de un mismo artista.
     * @throws PersistenciaException si no encuentra a ninguna canción.
     */
    @Override
    public List<CancionEntidad> obtenerCancionesPorArtista(String artista) throws PersistenciaException {
        try {
            // Creamos un entity manager.
            EntityManager em = conexion.crearConexion();

            // Creamos una consulta tipada.
            TypedQuery consulta = em.createQuery(
                    "SELECT c "
                    + "FROM CancionEntidad c "
                    + "WHERE c.artista LIKE '" + artista + "'", CancionEntidad.class);

            // La ejecutamos.
            List<CancionEntidad> resultado = consulta.getResultList();

            // Cerramos el entity manager.
            em.close();

            // Retornamos el resultado.
            return resultado;
        } catch (Exception e) {
            throw new PersistenciaException("Ocurrió algún error durante el borrado. Error " + e.getMessage());
        }
    }
}
