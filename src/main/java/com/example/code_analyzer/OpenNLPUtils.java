package com.example.code_analyzer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

public class OpenNLPUtils {
    private SentenceDetectorME sentenceDetector;
    private TokenizerME tokenizer;
    private POSTaggerME posTagger;

    public OpenNLPUtils() throws IOException {
        loadModels();
    }

    private void loadModels() throws IOException {
        try (InputStream sentenceModelStream = new FileInputStream("src/main/resources/models/opennlp-en-ud-ewt-sentence-1.0-1.9.3.bin");
             InputStream tokenModelStream = new FileInputStream("src/main/resources/models/opennlp-en-ud-ewt-tokens-1.0-1.9.3.bin");
             InputStream posModelStream = new FileInputStream("src/main/resources/models/opennlp-en-ud-ewt-pos-1.0-1.9.3.bin")) {

            SentenceModel sentenceModel = new SentenceModel(sentenceModelStream);
            TokenizerModel tokenModel = new TokenizerModel(tokenModelStream);
            POSModel posModel = new POSModel(posModelStream);

            sentenceDetector = new SentenceDetectorME(sentenceModel);
            tokenizer = new TokenizerME(tokenModel);
            posTagger = new POSTaggerME(posModel);
        }
    }

    public String[] detectSentences(String text) {
        return sentenceDetector.sentDetect(text);
    }

    public String[] tokenize(String sentence) {
        return tokenizer.tokenize(sentence);
    }

    public String[] tagPOS(String[] tokens) {
        return posTagger.tag(tokens);
    }
}
