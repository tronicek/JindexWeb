package edu.tarleton.jindex.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Repository Data Transfer Object.
 * 
 * @author Zdenek Tronicek, tronicek@tarleton.edu
 */
@XmlRootElement(name = "repository")
public class RepositoryDTO {

    private String project;

    public RepositoryDTO() {
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
