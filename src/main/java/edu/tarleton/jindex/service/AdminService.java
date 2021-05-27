package edu.tarleton.jindex.service;

import edu.tarleton.jindex.Engine;
import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;

/**
 * The service that creates the index.
 * 
 * @author Zdenek Tronicek, tronicek@tarleton.edu
 */
@ApplicationScoped
public class AdminService {

    public void initialize(Properties project) throws Exception {
        cleanBuffers();
        Engine eng = Engine.instance(project);
        eng.perform();
    }

    private void cleanBuffers() {
        // wait for memory mapped buffers to be written on disk
        try {
            Thread.sleep(1000);
            System.gc();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
