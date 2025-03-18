/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package itson.clienterest;

import itson.entidades.CancionEntidad;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Familia
 */
public class ClienteREST {

    public static void main(String[] args) {

        Client cliente = ClientBuilder.newClient();
        String BASE_URL = "http://localhost:8080/ServiciosREST/resources/canciones";

        // Obtener todas las canciones
        System.out.println("•••••• OBTENER TODAS LAS CANCIONES ••••••");
        Response response = cliente.target(BASE_URL).request().get();
        if (response.getStatus() == 200) {
            List<CancionEntidad> canciones = response.readEntity(new GenericType<>() {
            });
            canciones.forEach(System.out::println);
        } else {
            System.out.println("Error al obtener canciones: " + response.getStatus());
        }

        // Obtener canción por ID
        System.out.println("•••••• OBTENER CANCIÓN POR ID ••••••");
        response = cliente.target(BASE_URL + "/552").request().get();
        if (response.getStatus() == 200) {
            CancionEntidad cancion = response.readEntity(CancionEntidad.class);
            System.out.println(cancion);
        } else {
            System.out.println("Canción no encontrada: " + response.getStatus());
        }

        // Obtener canciones por artista
        System.out.println("•••••• OBTENER CANCIONES POR ARTISTA ••••••");
        response = cliente.target(BASE_URL + "/query")
                .queryParam("artista", "Entheos")
                .request()
                .get();
        if (response.getStatus() == 200) {
            List<CancionEntidad> canciones = response.readEntity(new GenericType<>() {
            });
            canciones.forEach(System.out::println);
        } else {
            System.out.println("Error al obtener canciones por artista: " + response.getStatus());
        }

        // Agregar nueva canción
        System.out.println("•••••• AGREGAR NUEVA CANCIÓN ••••••");
        CancionEntidad nuevaCancion = new CancionEntidad("Ya Love", "KGLW", "Butterfly 3000", new GregorianCalendar(2021, 3, 2));
        response = cliente.target(BASE_URL)
                .request()
                .post(Entity.json(nuevaCancion));
        if (response.getStatus() == 201) {
            System.out.println("Canción agregada con éxito");
        } else {
            System.out.println("Error al agregar canción: " + response.getStatus());
        }

        // Actualizar canción
        System.out.println("•••••• ACTUALIZAR CANCIÓN ••••••");
        nuevaCancion.setAlbum("Flying Microtonal Banana");
        response = cliente.target(BASE_URL + "/552")
                .request()
                .put(Entity.json(nuevaCancion));
        if (response.getStatus() == 200) {
            System.out.println("Canción actualizada con éxito");
        } else {
            System.out.println("Error al actualizar canción: " + response.getStatus());
        }

        // Eliminar canción
        System.out.println("•••••• ELIMINAR CANCIÓN ••••••");
        response = cliente.target(BASE_URL + "/552").request().delete();
        if (response.getStatus() == 200) {
            System.out.println("Canción eliminada con éxito");
        } else {
            System.out.println("Error al eliminar canción: " + response.getStatus());
        }

        // Cerrar cliente
        cliente.close();
    }
}
