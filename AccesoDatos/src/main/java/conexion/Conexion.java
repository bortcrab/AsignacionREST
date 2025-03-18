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
public class Conexion implements IConexion {

    /**
     * Método para crear una conexión con la base de datos.
     *
     * @return Un objeto del tipo EntityManager.
     */
    @Override
    public EntityManager crearConexion() {
        // Creamos el EntityManagerFactory.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexionPU");
        // Creamos el EntityManager.
        EntityManager em = emf.createEntityManager();

        // Retornamos el EntityManager.
        return em;
    }

}

