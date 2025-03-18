/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package conexion;

import jakarta.persistence.EntityManager;

/**
 * Interfaz con los métodos necesarios para crear conexiones con la base de
 * datos.
 *
 * @author Diego Valenzuela Parra - 00000247700
 */
public interface IConexion {

    /**
     * Método para crear una conexión con la base de datos.
     *
     * @return Un objeto del tipo EntityManager.
     */
    public EntityManager crearConexion();
}
