package edu.tarleton.jindex.ws;

import edu.tarleton.jindex.service.SearchService;
import edu.tarleton.jindex.dto.QueryDTO;
import edu.tarleton.jindex.dto.ResultDTO;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * The web service that searches the index.
 * 
 * @author Zdenek Tronicek, tronicek@tarleton.edu
 */
@Path("search")
public class SearchWS {

    private static final Logger logger = Logger.getAnonymousLogger();
    @Context
    private ServletContext servletContext;
    @Inject
    private SearchService searchService;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response search(QueryDTO query) {
        try {
            Properties project = (Properties) servletContext.getAttribute("project");
            ResultDTO[] dtos = searchService.find(project, query.getFragment());
            return Response.ok(dtos).build();
        } catch (Exception e) {
            logger.log(Level.WARNING, "WS failed", e);
            return Response.serverError().build();
        }
    }
}
