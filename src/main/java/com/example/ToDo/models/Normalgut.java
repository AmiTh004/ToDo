package com.example.ToDo.models;


public class Normalgut {
    
    String inhalt;
    Double gewicht;

    public Normalgut (String inhalt, double gewicht){
        setGewicht(gewicht);
        setInhalt(inhalt);
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
