package com.example.ToDo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import com.example.ToDo.models.Route;

@Controller
public class RouteController {

    ArrayList<Route> routen;
    ArrayList<String> staedte;

    public RouteController() {
        setRouten(new ArrayList<Route>());

        createDemoData();
    }

    private void createDemoData(){
        getRouten().add(new Route("Hamburg", "New York", 29, 5300));
        getRouten().add(new Route("Bremen", "Dubai", 18, 1200));
    }

    private ArrayList<String> getStaedte(){
        ArrayList<String> staedte = new ArrayList<>();

        staedte.add("Hamburg");
        staedte.add("Sidney");
        staedte.add("New York");
        staedte.add("Bremen");
        staedte.add("Dubai");
        staedte.add("Rotterdam");

        return staedte;
    }

    @GetMapping("/routen")
    public String routen(@RequestParam(name = "activePage", required = false, defaultValue = "routen") String activePage, Model model){
        model.addAttribute("activePage", "routen");
        model.addAttribute("routen", getRouten());
        
        //Städte laden
        model.addAttribute("staedte", getStaedte());

        return "index.html";
    }

    
    @RequestMapping("/delroute")
    public String delroute(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "route") String activePage, Model model){
        getRouten().remove(id);
        return "redirect:/routen";
    }

   
    @RequestMapping("/changeroute")
    public String changeroute(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "changeroute") String activePage, Model model){
        // Route zur Bearbeitung laden
        model.addAttribute("route", getRouten().get(id));
        model.addAttribute("routeid", id);

        //Mögliche Städte hier hinzufügen
        model.addAttribute("staedte", getStaedte());

        model.addAttribute("activePage", "routeUpdate");
        return "index.html";
    }
    
    @RequestMapping("/updateroute")
    public String updateroute(@RequestParam(name="routeId", required = true, defaultValue = "null") int routeId, @RequestParam(name = "routeStart", required = true, defaultValue = "null") String start, @RequestParam(name="ziel", required = true, defaultValue = "null") String ziel, @RequestParam(name="zeitDays", required = true, defaultValue = "null") int zeitDays, @RequestParam(name="kilometer", required = true, defaultValue = "null") int kilometer, @RequestParam(name="activePage", required = false, defaultValue = "routen") String activePage, Model model){
        getRouten().get(routeId).setStart(start);
        getRouten().get(routeId).setZiel(ziel);
        getRouten().get(routeId).setZeitDays(zeitDays);
        getRouten().get(routeId).setKilometer(kilometer);
        return "redirect:/routen";
    }

    

    @RequestMapping("/addroute")
    public String addroute(@RequestParam(name = "routeStart", required = true, defaultValue = "null") String start, @RequestParam(name="ziel", required = true, defaultValue = "null") String ziel, @RequestParam(name="zeitDays", required = true, defaultValue = "null") int zeitDays, @RequestParam(name="kilometer", required = true, defaultValue = "null") int kilometer, @RequestParam(name = "activePage",required = false, defaultValue = "routen") String activePage, Model model) {
        getRouten().add(new Route(start, ziel, zeitDays, kilometer));
        model.addAttribute("activePage", "routen");
        model.addAttribute("routen", getRouten());
        return "index.html";
    }


    public void setRouten(ArrayList<Route> routen) {
        this.routen = routen;
    }
    public ArrayList<Route> getRouten() {
        return routen;
    }

    
}