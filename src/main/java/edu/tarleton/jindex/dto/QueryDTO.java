package edu.tarleton.jindex.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Query Data Transfer Object.
 * 
 * @author Zdenek Tronicek, tronicek@tarleton.edu
 */
@XmlRootElement(name = "query")
@XmlType(name = "query")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class QueryDTO {

    private String fragment;
    private int maxResponseSize;

    @XmlElement
    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }

    @XmlElement
    public int getMaxResponseSize() {
        return maxResponseSize;
    }

    public void setMaxResponseSize(int maxResponseSize) {
        this.maxResponseSize = maxResponseSize;
    }
}
