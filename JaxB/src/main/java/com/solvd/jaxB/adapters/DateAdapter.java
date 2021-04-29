package com.solvd.jaxB.adapters;


import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date> {

    @Override
    public String marshal(Date date){
        return new SimpleDateFormat("MM/dd/yyyy").format(date);
    }

    @Override
    public Date unmarshal(String s) throws ParseException {
        return new SimpleDateFormat("MM/dd/yyyy").parse(s);
    }

}
