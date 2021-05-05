package com.solvd.dataBaseOnlineShop.services.individual;

import com.solvd.dataBaseOnlineShop.dao.jdbc.impl.individual.LanguageDAO;
import com.solvd.dataBaseOnlineShop.models.individual.Language;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LanguageServ {
    private final static Logger logger = LogManager.getLogger(LanguageServ.class);
    private final LanguageDAO languageDAO = new LanguageDAO();

    public void create(Language language) {
        languageDAO.create(language);
        logger.info("Language created in DB: " + language.toString());
    }

    public Language getByID(int id) {
        Language language = languageDAO.getByID(id);
        logger.info("Getting language by " + id + ": "+ language.toString());
        return language;
    }

    public void update(Language language) {
        languageDAO.update(language);
        logger.info("Updating language: " + language.toString());
    }

    public void delete(int id) {
        languageDAO.delete(id);
        logger.info("Deleting language with id " + id);
    }
}
