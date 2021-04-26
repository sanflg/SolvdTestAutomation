package com.solvd.dataBaseOnlineShop.services.individual;

import com.solvd.dataBaseOnlineShop.dao.jdbc.impl.individual.LanguageDAO;
import com.solvd.dataBaseOnlineShop.models.individual.Language;
import com.solvd.dataBaseOnlineShop.services.IAbstractServ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LanguageServ implements IAbstractServ<Language> {
    private final static Logger logger = LogManager.getLogger(LanguageServ.class);
    private final LanguageDAO languageDAO = new LanguageDAO();

    public LanguageServ() {
    }

    @Override
    public void create(Language language) {
        languageDAO.create(language);
        logger.info("Language created in DB: " + language.toString());
    }

    @Override
    public Language getByID(int id) {
        Language language = languageDAO.getByID(id);
        logger.info("Getting language by " + id + ": "+ language.toString());
        return language;
    }

    @Override
    public void update(Language language) {
        languageDAO.update(language);
        logger.info("Updating language: " + language.toString());
    }

    @Override
    public void delete(int id) {
        languageDAO.delete(id);
        logger.info("Deleting language with id " + id);
    }
}
