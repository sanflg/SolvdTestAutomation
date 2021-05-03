package com.solvd.jackson.impl.individual;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.solvd.jackson.interfaces.individual.ILanguageDAO;
import com.solvd.jackson.models.individual.Language;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LanguageDAO implements ILanguageDAO {
    private static final Logger logger = LogManager.getLogger(LanguageDAO.class);
    private static final File FILE = new File("src/main/resources/json/languages.json");
    List<Language> languages = new ArrayList<>();

    @Override
    public void create(Language language) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            languages = objectMapper.readValue(FILE, new TypeReference<List<Language>>(){});
            languages.add(language);
            objectMapper.writeValue(FILE, languages);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public Language getByID(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            languages = objectMapper.readValue(FILE, new TypeReference<List<Language>>(){});
        } catch (IOException e){
            logger.error(e);
        }
        return languages.stream()
                .filter(language -> language.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(Language language) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            languages = objectMapper.readValue(FILE, new TypeReference<List<Language>>(){});
            languages.stream()
                    .filter(languageElement -> languageElement.getId() == language.getId())
                    .findAny()
                    .ifPresent(updateLanguage -> {
                        updateLanguage.setName(language.getName());
                        updateLanguage.setTag(language.getTag());
                    });
            objectMapper.writeValue(FILE, languages);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public void delete(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            languages = objectMapper.readValue(FILE, new TypeReference<List<Language>>(){});
            languages.stream()
                    .filter(languageElement -> languageElement.getId() == id)
                    .findAny()
                    .ifPresent(deleteLanguage -> languages.remove(deleteLanguage));
        } catch (IOException e){
            logger.error(e);
        }
    }
}
