package com.solvd.jaxB.wrappers.commerce;

import com.solvd.jaxB.models.commerce.Currency;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;

@XmlRootElement(name = "currencies")
@XmlAccessorType(XmlAccessType.FIELD)
public class Currencies{
    private final File FILE = new File("src/main/resources/xml/currencies.xml");

    @XmlElement(name = "currency")
    private List<Currency> currencies = null;

    public File getFILE() {
        return FILE;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}
