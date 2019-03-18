package tech.kea.assignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.kea.assignment.model.Post;
import tech.kea.assignment.model.User;
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

    // Viser den enkelte post ud fra postID angivet i URL'en
    @GetMapping ("/posts/post/{postID}")
    public String showPost(@PathVariable int postID, Model model, HttpSession session ) {

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
    @ModelAttribute ("isAdmin")
    public boolean isAdmin(HttpSession session){
        return sessionhelper.isAdmin(session);
    }
    // Viser siden hvor man kan slette en bruger når man tilgår /users/delete/
    @GetMapping("/posts/delete/{postID}")
    public String delete(@PathVariable int postID, Model model, HttpSession session)
    {
        logger.log("delete(@PathVariable int postID): START");
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "/posts/error";
        }
        try {
            model.addAttribute("post", blogservice.getPost(postID));
            logger.log("delete(@PathVariable int postID): END");
            return "/posts/delete";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("delete(@PathVariable int postID): END");
            return "/posts/error";
        }
    }

    // Håndterer logikken for at slette posten interagerer med vores repository lag
    @PostMapping("/posts/delete")
    public String deleteExecute(@RequestParam(value="postID", required=true) int postID, HttpSession session)
    {
        logger.log("deletePost(@ModelAttribute int postID): START");
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "/users/error";
        }
        try {
            blogservice.deletePost(postID);
            logger.log("deleteUser(@ModelAttribute int postID): END");
            return "redirect:/";
        } catch (SQLException e) {
            logger.log("deleteUser(@ModelAttribute int postID): END");
            return "redirect:/users/error";
        }
    }

    // Viser siden hvor man kan redigere en bruger når man tilgår /users/edit/{postID}
    @GetMapping("/posts/edit/{postID}")
    public String edit(@PathVariable int postID, Model model, HttpSession session)
    {
        logger.log("edit(@PathVariable int postID): START");
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "/posts/error";
        }
        try {
            model.addAttribute("post", blogservice.getPost(postID));
            logger.log("edit(@PathVariable int postID): END");
            return "/posts/edit";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("edit(@PathVariable int postID): END");
            return "/posts/error";
        }
    }

    // Håndterer logikken for at redigere posten og interagerer med vores repository lag
    @PostMapping("/posts/edit")
    public String editExecute(
            @RequestParam(value="postID",   required=true) int postID,
            @RequestParam(value="title",    required=true) String title,
            @RequestParam(value="content",  required=true) String content,
            @RequestParam(value="hidden", required=false) String hidden
            , HttpSession session) {
        logger.log("editPost(@ModelAttribute int postID): START");
        boolean hiddenStatus = false;
        if(hidden.equalsIgnoreCase("on")){
            hiddenStatus = true;
        }

        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "/posts/error";
        }
        try {
            Post p = new Post(postID, title, content, hiddenStatus);
            logger.log("editing post with postID:" + postID + " with data(" + title + ", " + content + ", " + hidden + ")");
            blogservice.editPost(p);
            logger.log("editPost(@ModelAttribute int postID): END");
            return "redirect:/posts/post/" + postID;
        } catch (SQLException e) {
            logger.log("editPost(@ModelAttribute int postID): END");
            return "redirect:/posts/error";
        }
    }
    // Viser siden hvor man kan slette en bruger når man tilgår /users/delete/
    @GetMapping("/posts/create")
    public String create( Model model, HttpSession session)
    {
        logger.log("createPost: START");
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "/posts/error";
        }

        logger.log("createPost: END");
        return "/posts/create";

    }
    // Håndterer logikken for at oprette en post og interagerer med vores repository lag
    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post, HttpSession session){
        logger.log("createPost(@ModelAttribute Post post): START");
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "/posts/error";
        }
        try {
            blogservice.createPost(post);
            logger.log("Created post", 1);
            logger.log("createPost(@ModelAttribute Post post): END");
            return "redirect:/";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            logger.log("createPost(@ModelAttribute Post post): END");
            return "redirect:/posts/error";
        }

    }

    @GetMapping("/pages/{id}")
    public String getPage(@PathVariable int pageid){
        return "/pages";
    }
}