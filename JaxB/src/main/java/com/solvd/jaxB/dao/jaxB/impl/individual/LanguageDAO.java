package com.solvd.jaxB.dao.jaxB.impl.individual;

import com.solvd.jaxB.dao.interfaces.individual.ILanguageDAO;
import com.solvd.jaxB.models.individual.Language;
import com.solvd.jaxB.wrappers.individual.Languages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class LanguageDAO implements ILanguageDAO {
    private static final Logger logger = LogManager.getLogger(LanguageDAO.class);
    private static final File FILE = new File("src/main/resources/xml/languages.xml");

    @Override
    public void create(Language language) {
        Languages languages = new Languages();
        languages.setLanguages(unmarshall());
        languages.getLanguages().add(language);
        marshall(languages);
    }

    @Override
    public Language getByID(int id) {
        Languages languages = new Languages();
        languages.setLanguages(unmarshall());
        return languages.getLanguages().stream()
                .filter(language -> language.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(Language language) {
        Languages languages = new Languages();
        languages.setLanguages(unmarshall());
        languages.getLanguages().stream()
                .filter(languageStream -> languageStream.getId() == language.getId())
                .findAny()
                .ifPresent(editLanguage ->{
                    editLanguage.setName(language.getName());
                    editLanguage.setTag(language.getTag());
                });
        marshall(languages);
    }

    @Override
    public void delete(int id) {
        Languages languages = new Languages();
        languages.setLanguages(unmarshall());
        languages.getLanguages().stream()
                .filter(languageStream -> languageStream.getId() == id)
                .findAny()
                .ifPresent(editLanguage ->
                        languages.getLanguages().remove(editLanguage));
        marshall(languages);
    }

    private static synchronized List<Language> unmarshall(){
        Languages languages = new Languages();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Languages.class);
            Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();
            languages = (Languages) jaxUnmarshaller.unmarshal(FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
        return languages.getLanguages();
    }

    private static synchronized void marshall(Languages languages){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Languages.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(languages, FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }
}
