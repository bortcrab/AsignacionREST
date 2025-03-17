package itson.entidades;

import java.util.Calendar;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-03-17T16:36:47", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(CancionEntidad.class)
public class CancionEntidad_ { 

    public static volatile SingularAttribute<CancionEntidad, Calendar> fechaLanzamiento;
    public static volatile SingularAttribute<CancionEntidad, String> artista;
    public static volatile SingularAttribute<CancionEntidad, String> album;
    public static volatile SingularAttribute<CancionEntidad, String> titulo;
    public static volatile SingularAttribute<CancionEntidad, Long> id;

}