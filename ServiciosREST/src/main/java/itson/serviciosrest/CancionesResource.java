/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package itson.serviciosrest;

import daos.CancionDAO;
import daos.ICancionDAO;
import excepciones.PersistenciaException;
import itson.entidades.CancionEntidad;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST Web Service
 *
 * @author Familia
 */
@Path("canciones")
@RequestScoped
public class CancionesResource {

    private ICancionDAO dao = new CancionDAO();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CancionesResource
     */
    public CancionesResource() {
    }

    /**
     * Método para añadir una canción.
     *
     * @param cancion Canción a añadir.
     * @return La canción añadida.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCancion(CancionEntidad cancion) {
        // Mensaje del error.
        StringBuilder mensajeError = new StringBuilder("Error: Los siguientes campos son obligatorios: ");
        // Campos de la solicitud que podrían presentar errores.
        StringBuilder camposErroneos = new StringBuilder();

        if (cancion.getTitulo() == null) {
            camposErroneos.append("Título, ");
        }
        if (cancion.getArtista() == null) {
            camposErroneos.append("Artista, ");
        }
        if (cancion.getAlbum() == null) {
            camposErroneos.append("Álbum, ");
        }
        if (cancion.getFechaLanzamiento() == null) {
            camposErroneos.append("Fecha de lanzamiento, ");
        }

        // Si hubo campos con errores.
        if (camposErroneos.length() > 0) {
            // Los agregamos al mensaje.
            mensajeError.append(camposErroneos);
            // Elimina la última coma y espacio
            mensajeError.setLength(mensajeError.length() - 2);

            return Response.status(400).entity(mensajeError.toString()).build();
        }

        try {
            dao.insertarCancion(cancion);
        } catch (PersistenciaException ex) {
            return Response.status(500).entity("Sucedió un error en el servidor").build();
        }
        return Response.status(200).entity(cancion).build();
    }

    /**
     * Método para actualizar una canción.
     *
     * @param cancion Canción a actualizar.
     * @return La canción actualizada.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editCancion(CancionEntidad cancion) {
        // Mensaje del error.
        StringBuilder mensajeError = new StringBuilder("Error: Los siguientes campos son obligatorios: ");
        // Campos de la solicitud que podrían presentar errores.
        StringBuilder camposErroneos = new StringBuilder();

        if (cancion.getId()== null) {
            camposErroneos.append("ID, ");
        }
        if (cancion.getTitulo() == null) {
            camposErroneos.append("Título, ");
        }
        if (cancion.getArtista() == null) {
            camposErroneos.append("Artista, ");
        }
        if (cancion.getAlbum() == null) {
            camposErroneos.append("Álbum, ");
        }
        if (cancion.getFechaLanzamiento() == null) {
            camposErroneos.append("Fecha de lanzamiento, ");
        }

        // Si hubo campos con errores.
        if (camposErroneos.length() > 0) {
            // Los agregamos al mensaje.
            mensajeError.append(camposErroneos);
            // Elimina la última coma y espacio
            mensajeError.setLength(mensajeError.length() - 2);

            return Response.status(400).entity(mensajeError.toString()).build();
        }

        try {
            CancionEntidad resultado = dao.actualizarCancion(cancion);
            if (resultado != null) {
                return Response.status(200).entity(cancion).build();
            } else {
                return Response.status(404).entity("No se encontró la canción que quiere actualizar.").build();
            }
        } catch (PersistenciaException ex) {
            return Response.status(500).entity("Sucedió un error en el servidor").build();
        }
    }
    
    /**
     * Método para borrar una canción.
     *
     * @param cancion Canción a borrar.
     * @return La canción borrada.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCancion(CancionEntidad cancion) {
        // Mensaje del error.
        StringBuilder mensajeError = new StringBuilder("Error: Los siguientes campos son obligatorios: ");
        // Campos de la solicitud que podrían presentar errores.
        StringBuilder camposErroneos = new StringBuilder();

        if (cancion.getId() == null) {
            camposErroneos.append("ID, ");
        }
        if (cancion.getTitulo() == null) {
            camposErroneos.append("Título, ");
        }
        if (cancion.getArtista() == null) {
            camposErroneos.append("Artista, ");
        }
        if (cancion.getAlbum() == null) {
            camposErroneos.append("Álbum, ");
        }
        if (cancion.getFechaLanzamiento() == null) {
            camposErroneos.append("Fecha de lanzamiento, ");
        }

        // Si hubo campos con errores.
        if (camposErroneos.length() > 0) {
            // Los agregamos al mensaje.
            mensajeError.append(camposErroneos);
            // Elimina la última coma y espacio
            mensajeError.setLength(mensajeError.length() - 2);

            return Response.status(400).entity(mensajeError.toString()).build();
        }

        try {
            CancionEntidad resultado = dao.borrarCancion(cancion);
            if (resultado != null) {
                return Response.status(200).entity(cancion).build();
            } else {
                return Response.status(404).entity("No se encontró la canción que quiere borrar.").build();
            }
        } catch (PersistenciaException ex) {
            return Response.status(500).entity("Sucedió un error en el servidor").build();
        }
    }

    /**
     * Obtiene una canción dado un ID.
     *
     * @param id ID de la canción a buscar.
     * @return La canción encontrad, null si no se encontró nada o un error si
     * sucedió algo inesperado en el servidor.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getCancion(@PathParam("id") String id) {
        Long idNumero;
        // Verificar que el ID sea un número válido
        try {
            idNumero = Long.valueOf(id);
        } catch (NumberFormatException e) {
            return Response.status(400).entity("El ID debe ser un número válido.").build();
        }

        CancionEntidad cancion;
        try {
            cancion = dao.obtenerCancionPorID(Long.valueOf(id));
        } catch (PersistenciaException ex) {
            return Response.status(500).entity("Sucedió un error en el servidor").build();
        }
        return Response.status(200)
                .entity(cancion)
                .build();
    }

    /**
     * Obtiene todas las canciones.
     *
     * @return Una lista con todas las canciones encontradas, null si no se
     * encontró nada o un error si sucedió algo inesperado en el servidor.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCanciones() {
        List<CancionEntidad> canciones;
        try {
            canciones = dao.obtenerCanciones();
        } catch (PersistenciaException ex) {
            return Response.status(500).entity("Sucedió un error en el servidor").build();
        }
        return Response.status(200).entity(canciones.toArray()).build();
    }

    /**
     * Obtiene la lista de todas las canciones de un mismo artista.
     *
     * @param artista Artista al que pertenecen todas las canciones.
     * @return Una lista con todas las canciones encontradas, null si no se
     * encontró nada o un error si sucedió algo inesperado en el servidor.
     */
    @GET
    @Path("/query")
    public Response getCanciones(
            @QueryParam("artista") String artista) {
        List<CancionEntidad> canciones;
        try {
            canciones = dao.obtenerCancionesPorArtista(artista);
        } catch (PersistenciaException ex) {
            return Response.status(500).entity("Sucedió un error en el servidor").build();
        }
        return Response.status(200).entity(canciones.toArray()).build();
    }
}
