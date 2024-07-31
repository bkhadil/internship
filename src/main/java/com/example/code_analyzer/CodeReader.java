package com.example.code_analyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CodeReader {
    public static List<String> extractComments(String filePath) throws IOException {
        List<String> comments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean multiLineComment = false;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("//")) {
                    comments.add(line.substring(2).trim());
                } else if (line.startsWith("/*")) {
                    multiLineComment = true;
                    comments.add(line.substring(2).trim());
                } else if (line.endsWith("*/")) {
                    multiLineComment = false;
                    comments.add(line.substring(0, line.length() - 2).trim());
                } else if (multiLineComment) {
                    comments.add(line);
                }
            }
        }
        return comments;
    }
}
