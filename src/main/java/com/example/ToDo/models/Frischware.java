package com.example.ToDo.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Frischware {
    
    String inhalt;
    Double gewicht;
    //Angabe des Datums mit util.Date weil die Eingabe einfacher ist.
    Date haltbarkeit;
    SimpleDateFormat sdf;


    public Frischware(String inhalt, double gewicht, String haltbarkeit){
        setGewicht(gewicht);
        setInhalt(inhalt);
        setSdf(new SimpleDateFormat("dd.MM.yyyy"));
        try {
            setHaltbarkeit(getSdf().parse(haltbarkeit));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    

    /**
     * 
     * SETTER UND GETTER
     */

     public void setHaltbarkeit(Date haltbarkeit) {
         this.haltbarkeit = haltbarkeit;
     }
     public Date getHaltbarkeit() {
         return haltbarkeit;
     }
     public void setSdf(SimpleDateFormat sdf) {
         this.sdf = sdf;
     }
    public SimpleDateFormat getSdf() {
        return sdf;
    }
    public void setGewicht(Double gewicht) {
        this.gewicht = gewicht;
    }
    public Double getGewicht() {
        return gewicht;
    }
    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }
    public String getInhalt() {
        return inhalt;
    }
}

