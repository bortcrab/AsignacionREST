/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Clase con la implementación de la interfaz IConexion para crear una conexión
 * con el la base de datos mediante JPA.
 *
 * @author Diego Valenzuela Parra - 00000247700
 */
public class Conexion{
    private static EntityManagerFactory emf;


    public static EntityManager crearConexion() {
        if (emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory("ConexionPU");
        }
        return emf.createEntityManager();
    }

    public static void cerrarEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            emf = null;
        }
    }
}

