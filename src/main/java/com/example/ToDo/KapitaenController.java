package com.example.ToDo;

import java.util.ArrayList;

import com.example.ToDo.models.Kapitaen;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KapitaenController {
    
    ArrayList<Kapitaen> kapitaene;

    public KapitaenController() {
        setKapitaene(new ArrayList<Kapitaen>());
        createDemoData();
    }

    private void createDemoData() {
        getKapitaene().add(new Kapitaen("Hans", "Rumber", 2394, 16));
        getKapitaene().add(new Kapitaen("Jürgen", "Pfeifer", 2373, 78));
        getKapitaene().add(new Kapitaen("Zoro", "Schmitt", 2345, 22));
        
    }
    
    @GetMapping("/kapitaene")
    public String normalguts(@RequestParam(name = "activePage", required = false, defaultValue = "normalguts") String activePage, Model model){
        model.addAttribute("activePage", "kapitaene");
        model.addAttribute("kapitaene", getKapitaene());
        return "index.html";
    }
    

     @RequestMapping("/delkapitaen")
    public String delkapitaen(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "kapitaen") String activePage, Model model){
        getKapitaene().remove(id);
        return "redirect:/kapitaene";
    }

   
    @RequestMapping("/changekapitaen")
    public String changekapitaen(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "changekapitaen") String activePage, Model model){
        // Todo zur Bearbeitung laden
        model.addAttribute("kapitaen", getKapitaene().get(id));
        model.addAttribute("kapitaenid", id);
        model.addAttribute("activePage", "kapitaenUpdate");
        return "index.html";
    }
    
    @RequestMapping("/updatekapitaen")
    public String updatekapitaen(@RequestParam(name="kapitaenId", required = true, defaultValue = "null") int kapitaenId, @RequestParam(name = "vorname", required = true, defaultValue = "null") String vorname, @RequestParam(name="nachname", required = true, defaultValue = "null") String nachname, @RequestParam(name="personalnummer", required = true, defaultValue = "null") int personalnummer, @RequestParam(name="gefahreneFahrten", required = true, defaultValue = "null") int gefahreneFahrten, @RequestParam(name="activePage", required = false, defaultValue = "kapitaene") String activePage, Model model){
        getKapitaene().get(kapitaenId).setVorname(vorname);
        getKapitaene().get(kapitaenId).setNachname(nachname);
        getKapitaene().get(kapitaenId).setPersonalnummer(personalnummer);
        getKapitaene().get(kapitaenId).setGefahreneFahrten(gefahreneFahrten);
        return "redirect:/kapitaene";
    }

    

    @RequestMapping("/addkapitaen")
    public String addkapitaen(@RequestParam(name = "vorname", required = true, defaultValue = "null") String vorname, @RequestParam(name="nachname", required = true, defaultValue = "null") String nachname, @RequestParam(name="personalnummer", required = true, defaultValue = "null") int personalnummer, @RequestParam(name="gefahreneFahrten", required = true, defaultValue = "null") int gefahreneFahrten, @RequestParam(name = "activePage",required = false, defaultValue = "kapitaene") String activePage, Model model) {
        getKapitaene().add(new Kapitaen(vorname, nachname, personalnummer, gefahreneFahrten));
        model.addAttribute("activePage", "kapitaene");
        model.addAttribute("kapitaene", getKapitaene());
        return "index.html";
    }

    


    public void setKapitaene(ArrayList<Kapitaen> kapitaene) {
        this.kapitaene = kapitaene;
    }
    public ArrayList<Kapitaen> getKapitaene() {
        return kapitaene;
    }
}
