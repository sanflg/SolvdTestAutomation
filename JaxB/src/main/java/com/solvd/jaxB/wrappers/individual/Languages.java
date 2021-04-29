package com.solvd.jaxB.wrappers.individual;


import com.solvd.jaxB.models.individual.Language;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

@XmlRootElement(name = "languages")
@XmlAccessorType (XmlAccessType.FIELD)
public class Languages{
    private final File FILE = new File("src/main/resources/xml/languages.xml");

    @XmlElement(name = "language")
    private List<Language> languages = null;

    public File getFILE() {
        return FILE;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
}
