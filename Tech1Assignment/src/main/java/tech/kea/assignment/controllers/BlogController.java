package tech.kea.assignment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.kea.assignment.services.Logging;
import tech.kea.assignment.services.SessionHelper;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class BlogController{
    SessionHelper sessionhelper;
    Logging logger;

    public BlogController()
    {
        sessionhelper = new SessionHelper();
        logger = new Logging();
    }
    @GetMapping ("/")
    public String index(Model model, HttpSession session)
    {
        model.addAttribute("isAdmin", sessionhelper.isAdmin(session));
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