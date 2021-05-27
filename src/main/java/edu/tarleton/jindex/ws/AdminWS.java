package edu.tarleton.jindex.ws;

import edu.tarleton.jindex.dto.RepositoryDTO;
import edu.tarleton.jindex.service.AdminService;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
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
 * The web service that initializes the index.
 * 
 * @author Zdenek Tronicek, tronicek@tarleton.edu
 */
@Path("initialize")
public class AdminWS {

    private static final Logger logger = Logger.getAnonymousLogger();
    @Context
    private ServletContext servletContext;
    @Inject
    private AdminService adminService;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response initialize(RepositoryDTO repository) {
        try {
            Properties conf = (Properties) servletContext.getAttribute("config");
            if (conf == null) {
                conf = loadConfig();
                servletContext.setAttribute("config", conf);
            }
            String configPath = conf.getProperty("configPath");
            String dataPath = conf.getProperty("dataPath");
            String projFile = concat(configPath, repository.getProject() + ".properties");
            Properties project = new Properties();
            try (FileReader in = new FileReader(projFile)) {
                project.load(in);
            }
            project.setProperty("dataPath", dataPath);
            project.setProperty("verbose", "false");
            project.setProperty("printStatistics", "false");
            servletContext.setAttribute("project", project);
            adminService.initialize(project);
            return Response.ok(repository).build();
        } catch (Exception e) {
            logger.log(Level.WARNING, "WS failed", e);
            return Response.serverError().build();
        }
    }

    private Properties loadConfig() throws IOException {
        Properties conf = new Properties();
        try (InputStream input = servletContext.getResourceAsStream("/WEB-INF/jindex.properties")) {
            conf.load(input);
        }
        return conf;
    }

    private String concat(String dir, String file) {
        if (!dir.endsWith("/")) {
            dir += "/";
        }
        return dir + file;
    }
}
