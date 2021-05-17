package fr.sunr.sme.avscheduler.web.rest;

import fr.sunr.sme.avscheduler.security.Role;
import fr.sunr.sme.avscheduler.security.User;
import io.quarkus.security.identity.SecurityIdentity;
import org.jboss.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/users")
public class UserResource {

    /** Logeur. */
    private static final Logger LOG = Logger.getLogger(UserResource.class);

    /** SecurityIdentity. */
    @Inject
    private SecurityIdentity identity;

    /**
     * Charge les sites pour l'utilisateur connecté.
     */
    @GET
    @Path("/me")
    @RolesAllowed({"BACK_OFFICE"})
    @Produces(MediaType.APPLICATION_JSON)
    public User getLocationsForUser() {
        return new User(identity);
    }

}