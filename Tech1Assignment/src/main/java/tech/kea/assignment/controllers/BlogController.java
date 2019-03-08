package tech.kea.assignment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;

@Controller
public class BlogController{
    @GetMapping ("/")
    public String index() {
        return "index";
    }

    @GetMapping ("/pages")
    public String index2() {
        return "index";
    }
    @GetMapping("/pages/{id}")
    public String getPage(@PathVariable int pageid){
        return "/pages";
    }
}