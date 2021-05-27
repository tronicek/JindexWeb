package edu.tarleton.jindex.service;

import edu.tarleton.jindex.Engine;
import edu.tarleton.jindex.Pos;
import edu.tarleton.jindex.dto.PositionDTO;
import edu.tarleton.jindex.dto.ResultDTO;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * The service that searches the index.
 * 
 * @author Zdenek Tronicek, tronicek@tarleton.edu
 */
public class SearchService {

    private static final int CACHE_SIZE = 1024 * 1024;
    private final Map<String, List<String>> CACHE = new HashMap<>();

    public ResultDTO[] find(Properties project, String code) throws Exception {
        Engine eng = Engine.instance(project);
        List<Pos> pp = eng.find(code);
        String srcDir = project.getProperty("sourceDir");
        ResultDTO[] dtos = new ResultDTO[pp.size()];
        for (int i = 0; i < pp.size(); i++) {
            Pos p = pp.get(i);
            PositionDTO start = new PositionDTO(p.getStart());
            PositionDTO end = new PositionDTO(p.getEnd());
            PositionDTO methodStart = new PositionDTO(p.getMethodStart());
            PositionDTO methodEnd = new PositionDTO(p.getMethodEnd());
            String method = readFile(srcDir, p.getFile(),
                    p.getMethodStartLine(), p.getMethodStartColumn(),
                    p.getMethodEndLine(), p.getMethodEndColumn());
            dtos[i] = new ResultDTO(p.getProject(), p.getFile(),
                    start, end, methodStart, methodEnd, method);
        }

        return dtos;
    }

    private String readFile(String sourceDir, String file,
            Integer startline, Integer startcolumn,
            Integer endline, Integer endcolumn) throws IOException {
        List<String> lines = searchCache(sourceDir, file);
        if (lines == null) {
            Path path = Paths.get(sourceDir, file);
            Charset cs = Charset.forName("UTF-8");
            lines = Files.readAllLines(path, cs);
            storeInCache(sourceDir, file, lines);
        }
        List<String> selected = lines.subList(startline - 1, endline);
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 0; i < selected.size(); i++) {
            String line = selected.get(i);
            if (i == 0 && startcolumn != null && i == selected.size() - 1 && endcolumn != null) {
                line = line.substring(startcolumn - 1, endcolumn);
            } else if (i == 0 && startcolumn != null) {
                line = line.substring(startcolumn - 1);
            } else if (i == selected.size() - 1 && endcolumn != null) {
                line = line.substring(0, endcolumn);
            }
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    private List<String> searchCache(String sourceDir, String file) {
        String s = sourceDir + "/" + file;
        return CACHE.get(s);
    }

    private void storeInCache(String sourceDir, String file, List<String> lines) {
        if (CACHE.size() >= CACHE_SIZE) {
            CACHE.clear();
        }
        String s = sourceDir + "/" + file;
        CACHE.put(s, lines);
    }
}
