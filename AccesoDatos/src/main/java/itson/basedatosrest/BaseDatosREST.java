/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package itson.basedatosrest;

import daos.CancionDAO;
import daos.ICancionDAO;
import entidades.CancionEntidad;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Familia
 */
public class BaseDatosREST {

    public static void main(String[] args) {
        try {
            ICancionDAO dao = new CancionDAO();
            List<CancionEntidad> canciones;

            CancionEntidad cancion1 = new CancionEntidad("Doom City", "King Gizzard & The Lizard Wizard", "Flying Microtonal Banana", new GregorianCalendar(2010, 10, 10));
            CancionEntidad cancion2 = new CancionEntidad("Mind Alone", "Entheos", "The Infinite Nothing", new GregorianCalendar(2016, 4, 1));

            System.out.println("INSERTAR CANCION");
            dao.insertarCancion(cancion1);
            dao.insertarCancion(cancion2);
            canciones = dao.obtenerCanciones();
            imprimirLista(canciones);

            System.out.println("ACTUALIZAR CANCION");
            cancion1.setFechaLanzamiento(new GregorianCalendar(2017, 2, 27));
            dao.actualizarCancion(cancion1);
            canciones = dao.obtenerCanciones();
            imprimirLista(canciones);

            System.out.println("OBTENER CANCION POR ID");
            System.out.println(dao.obtenerCancionPorID(1L));

            System.out.println("OBTENER CANCIONES POR ARTISTA");
            canciones = dao.obtenerCancionesPorArtista("King Gizzard");
            imprimirLista(canciones);

            System.out.println("BORRAR CANCION");
            dao.borrarCancion(cancion1);
            canciones = dao.obtenerCanciones();
            imprimirLista(canciones);
        } catch (PersistenciaException ex) {
            Logger.getLogger(BaseDatosREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void imprimirLista(List<CancionEntidad> canciones) {
        for (CancionEntidad cancion : canciones) {
            System.out.println(cancion);
        }
        System.out.println("");
    }
}
