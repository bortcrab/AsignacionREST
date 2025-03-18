/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import excepciones.PersistenciaException;
import itson.entidades.CancionEntidad;
import java.util.List;

/**
 * Interfaz que proporciona los métodos para acceder y manipular datos
 * relacionados con la entidad Cancion en la base de datos.
 *
 * @author Diego Valenzuela Parra - 00000247700
 */
public interface ICancionDAO {

    /**
     * Método que inserta una cancion en la base de datos.
     *
     * @param cancion Canción a ser insertada.
     * @throws PersistenciaException si ocurre algún error.
     */
    public void insertarCancion(CancionEntidad cancion) throws PersistenciaException;

    /**
     * Método que actualiza los datos de una cancion en la base de datos.
     *
     * @param cancion Canción a ser actualizada.
     * @throws PersistenciaException si ocurre algún error.
     */
    public CancionEntidad actualizarCancion(CancionEntidad cancion) throws PersistenciaException;

    /**
     * Método que elimina una cancion de la base de datos.
     *
     * @param cancion Canción a ser borrada.
     * @throws PersistenciaException si ocurre algún error.
     */
    public CancionEntidad borrarCancion(String id) throws PersistenciaException;

    /**
     * Obtiene una canción dado un ID.
     *
     * @param id ID de la canción a buscar.
     * @return Canción encontrada.
     * @throws PersistenciaException si no encuentra a ninguna canción.
     */
    public CancionEntidad obtenerCancionPorID(Long id) throws PersistenciaException;

    /**
     * Obtiene la lista completa de canciones registradas.
     *
     * @return Lista de todas las canciones registradas
     * @throws PersistenciaException si no encuentra a ninguna canción.
     */
    public List<CancionEntidad> obtenerCanciones() throws PersistenciaException;

    /**
     * Obtiene la lista completa de canciones de un mismo artista.
     *
     * @param artista Artista que hizo la canción.
     * @return Lista de todas las canciones de un mismo artista.
     * @throws PersistenciaException si no encuentra a ninguna canción.
     */
    public List<CancionEntidad> obtenerCancionesPorArtista(String artista) throws PersistenciaException;
}
