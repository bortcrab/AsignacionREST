package itson.serviciosrest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.logging.Logger;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@ApplicationPath("resources")
public class JakartaRestConfiguration extends Application {
    private static final Logger logger = Logger.getLogger(JakartaRestConfiguration.class.getName());
    
    public JakartaRestConfiguration() {
        logger.info("JakartaRestConfiguration inicialized");
    }
}
