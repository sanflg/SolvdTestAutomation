package com.solvd.jaxB.wrappers.individual;

import com.solvd.jaxB.models.individual.PhoneNumber;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

@XmlRootElement(name = "phonenumbers")
@XmlAccessorType (XmlAccessType.FIELD)
public class PhoneNumbers{
    private final File FILE = new File("src/main/resources/xml/phonenumbers.xml");

    @XmlElement(name = "phonenumber")
    private List<PhoneNumber> phonenumbers = null;

    public File getFILE() {
        return FILE;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phonenumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phonenumbers) {
        this.phonenumbers = phonenumbers;
    }
}
