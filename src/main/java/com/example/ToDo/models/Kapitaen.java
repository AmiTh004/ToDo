package com.example.ToDo.models;

public class Kapitaen {

    String vorname;
    String nachname;
    int personalnummer;
    int gefahreneFahrten;


    public Kapitaen(String vorname, String nachname, int personalnummer, int gefahreneFahrten) {
        setNachname(nachname);
        setPersonalnummer(personalnummer);
        setVorname(vorname);
        setGefahreneFahrten(gefahreneFahrten);
    }

    
    /**
     * 
     * SETTER UND GETTER
     */

     public void setNachname(String nachname) {
         this.nachname = nachname;
     }
     public String getNachname() {
         return nachname;
     }
     public void setPersonalnummer(int personalnummer) {
         this.personalnummer = personalnummer;
     }
     public int getPersonalnummer() {
         return personalnummer;
     }
     public void setVorname(String vorname) {
         this.vorname = vorname;
     }
     public String getVorname() {
         return vorname;
     }
     public void setGefahreneFahrten(int gefahreneFahrten) {
        this.gefahreneFahrten = gefahreneFahrten;
     }
     public int getGefahreneFahrten() {
        return gefahreneFahrten;
     }
    
}
