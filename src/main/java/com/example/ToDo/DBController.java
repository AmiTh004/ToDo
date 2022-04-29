package com.example.ToDo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.ToDo.models.*;

public class DBController {
    

    String connectionUrl;
    String username;
    String passwort;

    public DBController(){
        //Hier den Port aus XAMPP eintragen
        //javadb ist die Datenbank
        setConnectionUrl("jdbc:mysql://localhost:3306/javadb");
        setPasswort("root");
        setUsername("root");
    }

    //Alle ToDos aus der Datenbank in eine Arraylist einfügen, die zurückgegeben wird
    public ArrayList<Todo> getAllToDos(){

        //Lokale Todos-Arraylist erstellen
        ArrayList<Todo> todos = new ArrayList<>();

        //Die DB-Query
        String sqlSelectAllTodos = "SELECT * FROM `todos` JOIN personen ON personen.id=todos.id_person";

        //Verbindung aufbauen mit USERNAME root und PASSWORT root
        try {
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllTodos);
            ResultSet rs = ps.executeQuery();
            //Solange es Datensätze in der von der DB angefragten Ressource gibt, werden diese durchgearbeitet und dann als ArrayList zurückgegeben
            while (rs.next()) {
                int id = (int) rs.getLong("id");
                String person = rs.getString("name");
                int personId = (int) rs.getLong("id_person");
                String description = rs.getString("description");
                todos.add(new Todo(id, description, person, personId));
            }
        } 
        catch (SQLException e) {
            System.out.println(e);
        }

        return todos;
    }

    //Füge neues Todo hinzu
    public void addNewTodo(int person, String description){
        try {
            String sqlSelectAllPersons = "INSERT INTO todos(id_person, description) VALUES('"+person+"','"+description+"');";
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);
            //als Return von executeUpdate kommt 0 (fail) oder 1 (ok) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
        } 
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    //Lösche ein Todo
    public void delTodo(int id) {
        try {
            String sqlSelectAllPersons = "DELETE FROM todos WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);
            //als Return von executeUpdate kommt 0 (fail) oder 1 (ok) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
            
        } 
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    //Hohle spezifisches Todo
    public Todo getTodo(int id) {
        Todo todo = null;
        try {
            String sqlSelectToDo = "SELECT * FROM `todos` JOIN personen ON personen.id=todos.id_person WHERE todos.id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            PreparedStatement ps = conn.prepareStatement(sqlSelectToDo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int todoId = (int) rs.getLong("id");
                int personId = (int) rs.getLong("id_person");
                String person = rs.getString("name");
                String description = rs.getString("description");
                todo = new Todo(todoId, description, person, personId);
            }
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        return todo;
    }

    //Hohle spezifisches Todo und aktualisiere dieses
    public Todo updateTodo(int id, String description, int personId) {
        Todo todo = null;
        try {
            String sqlSelectAllPersons = "UPDATE todos SET id_person='"+personId+"', description='"+description+"' WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);
            ps.executeUpdate();
        } 
        catch (SQLException e) {
            System.out.println(e);
        }
        
        return todo;
    }

    // Holt alle ToDos aus der Datenbank und gibt diese als ArrayList zurück!
    public ArrayList<Person> getAllPersonen(){

        // Lokale Todos-Arraylist erstellen
        ArrayList<Person> personen = new ArrayList<>();

        // Das ist DB-Query
        String sqlSelectAllToDos = "SELECT * FROM personen";

        // Verbindung aufbauen mit USERNAME root und PASSWORT root
        try{
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllToDos); 
            ResultSet rs = ps.executeQuery();
            // Solange es Datensätze in der von der DB angefragen Ressource gibt, werden diese durchgearbeitet und dann als eine ArrayList zurückgegeben
            while (rs.next()) {
                int id = (int) rs.getLong("id");
                String name = rs.getString("name");
                personen.add(new Person(name, id));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return personen;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }
    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
    public String getPasswort() {
        return passwort;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
}
