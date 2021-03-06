package com.example.ToDo;

import java.util.ArrayList;

import com.example.ToDo.models.Person;
import com.example.ToDo.models.Todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodosController {

    ArrayList<Todo> todos;

    public TodosController(){
        setTodos(new ArrayList<Todo>());

        //createDemoData();
        loadToDosFromDB();
    }

    private void createDemoData(){
        //getTodos().add(new Todo(0,"Müll rausbringen!", "Amelie", 0));
        //getTodos().add(new Todo(1,"Küche aufräumen", "Jordan", 0));
    }

    //Lädt aktuelle ToDos aus der Datenbank und wirft bei bedarf eine SQL-Exeption aus
    private void loadToDosFromDB(){
        DBController db = new DBController();
        setTodos(db.getAllToDos());
    }

    //Personen erstellen und zurückgeben
    private ArrayList<String> getPersonenNoDB(){
        ArrayList<String> personen = new ArrayList<>();

        personen.add("Amelie");
        personen.add("Alex");
        personen.add("Theo");
        personen.add("Bent");
        personen.add("Jordan");
        personen.add("Lennard");
        personen.add("Nils");
        personen.add("Holger");
        personen.add("Oskar");

        return personen;
    }

    //Holt alle Personen aus der Datenbank
    private ArrayList<Person> getPersonen() {
        ArrayList<Person> personen = new ArrayList<>();
        
        DBController db= new DBController();
        personen = db.getAllPersonen();

        return personen;
    }
    
    @GetMapping("/todos")
    public String todos(@RequestParam(name="activePage", required = false, defaultValue = "todos") String activePage, Model model) {
        loadToDosFromDB();
        model.addAttribute("activePage", "todos");
        model.addAttribute("todos", getTodos());

        //Personen für ein Todo holen
        model.addAttribute("personen", getPersonen());
        return "index.html";
    }

    @RequestMapping("/deltodo")
    public String deltodo(@RequestParam(name="id", required = true, defaultValue = "null")int id, @RequestParam(name="activePage", required = false, defaultValue = "todos") String activePage, Model model) {
        DBController db = new DBController();
        db.delTodo(id);
        return "redirect:/todos";        
    }

    @RequestMapping("/changetodo")
    public String changetodo(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "changetodo") String activePage, Model model){
        // Todo zur Bearbeitung laden
        DBController db = new DBController();
        model.addAttribute("todo", db.getTodo(id));
        model.addAttribute("todoid", id);

        //Mögliche Personen hier hinzufügen
        model.addAttribute("personen", getPersonen());

        model.addAttribute("activePage", "todoUpdate");
        return "index.html";
    }

    @RequestMapping("/updatetodo")
    public String updatetodo(@RequestParam(name="todoId", required = true, defaultValue = "null") int todoId, @RequestParam(name="todoDesc", required = true, defaultValue = "null") String todoDesc,@RequestParam(name="todoPersonId", required = true, defaultValue = "null") int todoPersonId, @RequestParam(name="activePage", required = false, defaultValue = "todos") String activePage, Model model){
        DBController db = new DBController();
        db.updateTodo(todoId, todoDesc, todoPersonId);
        return "redirect:/todos";
    }

    @RequestMapping("/addtodo")
    public String addtodo(@RequestParam(name="todoDesc", required = true, defaultValue = "null")String todoDesc,@RequestParam(name= "todoPersonId", required = true, defaultValue = "null") int todoPersonId, @RequestParam(name="activePage", required = false, defaultValue = "todos") String activePage, Model model){
        DBController db = new DBController();
        db.addNewTodo(todoPersonId, todoDesc);
        return "redirect:/todos";
    }




    public void setTodos(ArrayList<Todo> todos) {
        this.todos = todos;
    }
    public ArrayList<Todo> getTodos() {
        return todos;
    }

}
