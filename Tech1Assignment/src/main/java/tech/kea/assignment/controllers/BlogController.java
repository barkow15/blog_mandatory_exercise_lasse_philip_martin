package tech.kea.assignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import tech.kea.assignment.services.BlogServiceInterface;
import tech.kea.assignment.services.Logging;
import tech.kea.assignment.services.SessionHelper;
import tech.kea.assignment.services.UserServiceInterface;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class BlogController{
    @Autowired
    private Logging logger;
    @Autowired
    private BlogServiceInterface blogservice;
    @Autowired
    private SessionHelper sessionhelper;

    public BlogController()
    {
        logger      = new Logging("BlogController");
        sessionhelper = new SessionHelper();
    }
    @GetMapping ("/")
    public String index(Model model, HttpSession session)
    {
        model.addAttribute("isAdmin", sessionhelper.isAdmin(session));

        try {
            model.addAttribute("posts", blogservice.getPosts());
            logger.log("index(Model model): END");
            return "index";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("index(Model model): END");
            return "/posts/error";
        }
    }

    @GetMapping ("/posts/post/{postID}")
    public String showPost(@PathVariable int postID, Model model, HttpSession session) {
        try {
            model.addAttribute("post", blogservice.getPost(postID));
            logger.log("index(Model model): END");
            return "/posts/post";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("index(Model model): END");
            return "/posts/error";
        }
    }
    @GetMapping("/pages/{id}")
    public String getPage(@PathVariable int pageid){
        return "/pages";
    }
}