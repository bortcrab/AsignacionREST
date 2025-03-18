/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package itson.clienterest;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Jersey REST client generated for REST resource:CancionesResource
 * [canciones]<br>
 * USAGE:
 * <pre>
 *        Cliente client = new Cliente();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Familia
 */
public class Cliente {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/ServiciosREST/resources";

    public Cliente() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("canciones");
    }

    public <T> T getCanciones(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("query");
        return resource.request(MediaType.APPLICATION_JSON).get(responseType);
    }

    public Response editCancion(Object requestEntity) throws ClientErrorException {
        return webTarget.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).put(jakarta.ws.rs.client.Entity.entity(requestEntity, jakarta.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public <T> T getCancion(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public Response addCancion(Object requestEntity) throws ClientErrorException {
        return webTarget.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).post(jakarta.ws.rs.client.Entity.entity(requestEntity, jakarta.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public Response deleteCancion(Object requestEntity) throws ClientErrorException {
        return webTarget.request(jakarta.ws.rs.core.MediaType.TEXT_PLAIN).delete(Response.class);
    }

    public void close() {
        client.close();
    }
    
}
