package com.example.ToDo.models;

public class Route {
    
    String start;
    String ziel;
    int zeitDays;
    int kilometer;

    public Route(String start, String ziel, int zeitDays, int kilometer) {
        setKilometer(kilometer);
        setStart(start);
        setZeitDays(zeitDays);
        setZiel(ziel);
    }

    /**
     * 
     * SETTER UND GETTER
     */

     public void setKilometer(int kilometer) {
         this.kilometer = kilometer;
     }
     public int getKilometer() {
         return kilometer;
     }
     public void setStart(String start) {
         this.start = start;
     }
     public String getStart() {
         return start;
     }
     public void setZeitDays(int zeitDays) {
         this.zeitDays = zeitDays;
     }
     public int getZeitDays() {
         return zeitDays;
     }
     public void setZiel(String ziel) {
         this.ziel = ziel;
     }
     public String getZiel() {
         return ziel;
     }
}
