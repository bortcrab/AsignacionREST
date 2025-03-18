
import daos.CancionDAO;
import daos.ICancionDAO;
import excepciones.PersistenciaException;
import itson.entidades.CancionEntidad;
import java.util.GregorianCalendar;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Familia
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
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
        }
    }

    public static void imprimirLista(List<CancionEntidad> canciones) {
        for (CancionEntidad cancion : canciones) {
            System.out.println(cancion);
        }
        System.out.println("");
    }
}
