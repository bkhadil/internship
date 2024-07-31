package com.example.code_analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FunctionSignatureExtractor {

    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/com/example/code_analyzer/SampleClass.java";
        String content = new String(Files.readAllBytes(Paths.get(filePath)));

        OpenNLPUtils nlpUtils = new OpenNLPUtils();
        String[] sentences = nlpUtils.detectSentences(content);

        for (String sentence : sentences) {
            String[] tokens = nlpUtils.tokenize(sentence);
            String[] posTags = nlpUtils.tagPOS(tokens);

            // Simple heuristic to identify function signatures
            if (sentence.contains("(") && sentence.contains(")") && sentence.contains("{")) {
                System.out.println("Function Signature: " + sentence);
                System.out.print("Parameters: ");
                int start = sentence.indexOf('(') + 1;
                int end = sentence.indexOf(')');
                if (start < end) {
                    String params = sentence.substring(start, end);
                    System.out.println(params);
                } else {
                    System.out.println("No parameters");
                }
            }
        }
    }
}

