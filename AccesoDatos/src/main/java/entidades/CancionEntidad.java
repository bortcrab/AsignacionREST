/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Familia
 */
@Entity
@Table(name = "canciones")
public class CancionEntidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "artista", nullable = false)
    private String artista;

    @Column(name = "album", nullable = false)
    private String album;

    @Column(name = "fecha_lanzamiento", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaLanzamiento;

    /**
     * Constructor vacío.
     */
    public CancionEntidad() {
    }

    /**
     * Constructor con todos los atributos de una canción menos su ID.
     *
     * @param titulo Título de la canción.
     * @param artista Artista de la canción.
     * @param album Album al que pertenece la canción.
     * @param fechaLanzamiento Fecha en que se lanzó.
     */
    public CancionEntidad(String titulo, String artista, String album, Calendar fechaLanzamiento) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Calendar getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Calendar fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CancionEntidad)) {
            return false;
        }
        CancionEntidad other = (CancionEntidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format(
                "{ \"id\": %d, \"titulo\": \"%s\", \"artista\": \"%s\", \"album\": \"%s\", \"fechaLanzamiento\": \"%s\" }",
                id,
                titulo,
                artista,
                album,
                fechaLanzamiento != null ? new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(fechaLanzamiento.getTime()) : null
        );
    }
}
