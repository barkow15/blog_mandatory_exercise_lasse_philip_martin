package com.example.blogmandatoryexercise_2_semester_lasse_philip_martin.controllers;
import com.example.blogmandatoryexercise_2_semester_lasse_philip_martin.repository.DatabaseConnectionMySQL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@Controller
public class BlogController{
    @GetMapping ("/")
    public String index() {
        try {
            DatabaseConnectionMySQL d = new DatabaseConnectionMySQL();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return "index";
    }
}