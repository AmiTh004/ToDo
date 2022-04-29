package com.example.ToDo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.example.ToDo.models.Frischware;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Kohäsion: gut: jede Methode macht genau eine Aufgabe
 * Kapselung: (besagt wie offen eine Klasse nach außenhin ist) gut: nur die Sachen public machen, die es wirklich brauchen, rest privat
 * Kopplung: (Verbundung von mehreren Klassen, Abhängikeit von anderen Klassen) gut: je weniger Dinge bei Änderung in einer Klasse verändert werden müssen, MVC Struktur
 * 
verbundung aufbauen
 Beziehungsansicht -> CASCADE ->  als verweis id nehmen
 */

@Controller
public class FrischwareController{

    ArrayList<Frischware> frischwaren;
    SimpleDateFormat sdf;

    public FrischwareController() {
        setFrischwaren(new ArrayList<Frischware>());
        createDemoData();
        setSdf(new SimpleDateFormat("dd.MM.yyyy"));
    }

    private void createDemoData() {
        getFrischwaren().add(new Frischware("Bananen", 250, "27.03.2022"));
        getFrischwaren().add(new Frischware("Mango", 250, "13.03.2022"));
    }

    @GetMapping("/frischwaren")
    public String frischwaren(@RequestParam(name = "activePage", required = false, defaultValue = "frischwaren") String activePage, Model model){
        model.addAttribute("activePage", "frischwaren");
        model.addAttribute("frischwaren", getFrischwaren());
        return "index.html";
    }

    @RequestMapping("/delfrischware")
    public String delfrischware(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "frischware") String activePage, Model model){
        getFrischwaren().remove(id);
        return "redirect:/frischwaren";
    }

    
    @RequestMapping("/changefrischware")
    public String changefrischware(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "changefrischware") String activePage, Model model){
        // Todo zur Bearbeitung laden
        model.addAttribute("frischware", getFrischwaren().get(id));
        model.addAttribute("frischwareid", id);
        model.addAttribute("activePage", "frischwareUpdate");
        return "index.html";
    }
    
    @RequestMapping("/updatefrischware")
    public String updatefrischware(@RequestParam(name="frischwareId", required = true, defaultValue = "null") int frischwareId, @RequestParam(name="inhalt", required = true, defaultValue = "null") String inhalt,@RequestParam(name="gewicht", required = true, defaultValue = "null") double gewicht, @RequestParam(name="haltbarkeit", required = true, defaultValue = "null") String haltbarkeit, @RequestParam(name="activePage", required = false, defaultValue = "frischwares") String activePage, Model model){
        getFrischwaren().get(frischwareId).setInhalt(inhalt);
        getFrischwaren().get(frischwareId).setGewicht(gewicht);
        try {
            getFrischwaren().get(frischwareId).setHaltbarkeit(getSdf().parse(haltbarkeit));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return "redirect:/frischwaren";
    }

    @RequestMapping("/addfrischware")
    public String addfrischware(@RequestParam(name = "inhalt", required = true, defaultValue = "null") String inhalt, @RequestParam(name="gewicht", required = true, defaultValue = "null") double gewicht, @RequestParam(name="haltbarkeit", required = true, defaultValue = "null") String haltbarheit, @RequestParam(name = "activePage",required = false, defaultValue = "frischwaren") String activePage, Model model) {
        getFrischwaren().add(new Frischware(inhalt, gewicht, haltbarheit));
        model.addAttribute("activePage", "frischwaren");
        model.addAttribute("frischwaren", getFrischwaren());
        return "index.html";
    }

    public void setFrischwaren(ArrayList<Frischware> frischwaren) {
        this.frischwaren = frischwaren;
    }
    public ArrayList<Frischware> getFrischwaren() {
        return frischwaren;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }
    public SimpleDateFormat getSdf() {
       return sdf;
    }
}