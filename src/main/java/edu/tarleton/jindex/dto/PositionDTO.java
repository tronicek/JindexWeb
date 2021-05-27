package edu.tarleton.jindex.dto;

import com.github.javaparser.Position;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Position Data Transfer Object.
 * 
 * @author Zdenek Tronicek, tronicek@tarleton.edu
 */
@XmlRootElement(name = "pos")
public class PositionDTO {

    private int line;
    private int column;

    public PositionDTO() {
    }

    public PositionDTO(Position pos) {
        line = pos.line;
        column = pos.column;
    }

    public PositionDTO(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
