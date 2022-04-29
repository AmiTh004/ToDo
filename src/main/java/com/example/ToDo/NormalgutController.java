package com.example.ToDo;

import java.util.ArrayList;

import com.example.ToDo.models.Normalgut;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NormalgutController {
    
    ArrayList<Normalgut> normalguts;

    public NormalgutController() {
        setNormalguts(new ArrayList<Normalgut>());

        createDemoData();
    }

    private void createDemoData(){
        getNormalguts().add(new Normalgut("Socken", 23.5));
        getNormalguts().add(new Normalgut("Hundefutter", 430.5));
    }

    @GetMapping("/normalguts")
    public String normalguts(@RequestParam(name = "activePage", required = false, defaultValue = "normalguts") String activePage, Model model){
        model.addAttribute("activePage", "normalguts");
        model.addAttribute("normalguts", getNormalguts());
        return "index.html";
    }

    
    @RequestMapping("/delnormalgut")
    public String delnormalgut(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "normalgut") String activePage, Model model){
        getNormalguts().remove(id);
        return "redirect:/normalguts";
    }

    
    @RequestMapping("/changenormalgut")
    public String changenormalgut(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "changenormalgut") String activePage, Model model){
        // Todo zur Bearbeitung laden
        model.addAttribute("normalgut", getNormalguts().get(id));
        model.addAttribute("normalgutid", id);
        model.addAttribute("activePage", "normalgutUpdate");
        return "index.html";
    }
    
    @RequestMapping("/updatenormalgut")
    public String updatenormalgut(@RequestParam(name="normalgutId", required = true, defaultValue = "null") int normalgutId, @RequestParam(name="inhalt", required = true, defaultValue = "null") String inhalt,@RequestParam(name="gewicht", required = true, defaultValue = "null") double gewicht, @RequestParam(name="activePage", required = false, defaultValue = "normalguts") String activePage, Model model){
        getNormalguts().get(normalgutId).setInhalt(inhalt);
        getNormalguts().get(normalgutId).setGewicht(gewicht);
        return "redirect:/normalguts";
    }


    @RequestMapping("/addnormalgut")
    public String addnormalgut(@RequestParam(name = "inhalt", required = true, defaultValue = "null") String inhalt, @RequestParam(name="gewicht", required = true, defaultValue = "null") double gewicht, @RequestParam(name = "activePage",required = false, defaultValue = "normalguts") String activePage, Model model) {
        getNormalguts().add(new Normalgut(inhalt, gewicht));
        model.addAttribute("activePage", "normalguts");
        model.addAttribute("normalguts", getNormalguts());
        return "index.html";
    }

    public void setNormalguts(ArrayList<Normalgut> normalguts) {
        this.normalguts = normalguts;
    }
    public ArrayList<Normalgut> getNormalguts() {
        return normalguts;
    }
}
