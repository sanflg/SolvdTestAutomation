package com.solvd.wordCounter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordCounter{
    public static void main(String[] args) throws IOException {
        Logger logger = LogManager.getLogger(WordCounter.class);
        Map<String, Integer> wordsMap = new HashMap<>();
        File fileInput = new File("src/main/resources/Text.txt");
        String stringFile = FileUtils.readFileToString(fileInput, (String) null);

        String[] splicedWords = StringUtils.split(stringFile.toLowerCase(), " .,:;!?/\\\"");

        Arrays.stream(splicedWords).forEach(word->{
            Object o = wordsMap.containsKey(word)?
            wordsMap.put(word,wordsMap.get(word)+1):wordsMap.put(word,1);});

        File file = new File("output/wordsMap.txt");

        wordsMap.entrySet().stream().forEach((k) -> {
            try {FileUtils.writeStringToFile(
                file, k.getKey() + "-" + k.getValue() + "\n", (String) null, true);
            }catch (IOException e) {logger.error(e);}});
    }
}


