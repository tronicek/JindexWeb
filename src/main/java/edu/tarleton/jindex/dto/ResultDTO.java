package edu.tarleton.jindex.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Result Data Transfer Object.
 * 
 * @author Zdenek Tronicek, tronicek@tarleton.edu
 */
@XmlRootElement(name = "result")
public class ResultDTO {

    private String project;
    private String path;
    private PositionDTO begin;
    private PositionDTO end;
    private PositionDTO methodBegin;
    private PositionDTO methodEnd;
    private String method;
    
    public ResultDTO() {
    }

    public ResultDTO(String project, String path,
            PositionDTO begin, PositionDTO end,
            PositionDTO methodBegin, PositionDTO methodEnd,
            String method) {
        this.project = project;
        this.path = path;
        this.begin = begin;
        this.end = end;
        this.methodBegin = methodBegin;
        this.methodEnd = methodEnd;
        this.method = method;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public PositionDTO getBegin() {
        return begin;
    }

    public void setBegin(PositionDTO begin) {
        this.begin = begin;
    }

    public PositionDTO getEnd() {
        return end;
    }

    public void setEnd(PositionDTO end) {
        this.end = end;
    }

    public PositionDTO getMethodBegin() {
        return methodBegin;
    }

    public void setMethodBegin(PositionDTO methodBegin) {
        this.methodBegin = methodBegin;
    }

    public PositionDTO getMethodEnd() {
        return methodEnd;
    }

    public void setMethodEnd(PositionDTO methodEnd) {
        this.methodEnd = methodEnd;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
