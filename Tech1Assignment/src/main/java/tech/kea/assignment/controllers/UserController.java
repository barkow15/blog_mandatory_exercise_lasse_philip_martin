package tech.kea.assignment.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.kea.assignment.model.Menu;
import tech.kea.assignment.model.User;
import tech.kea.assignment.services.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;


/**
 * Contains user CRUD operations and validation
 * */
@Controller
public class UserController{

    @Autowired
    private Logging logger;
    @Autowired
    private UserServiceInterface userservice;
    @Autowired
    private SessionHelper sessionhelper;
    public UserController()
    {
        logger = new Logging("UserController");

        sessionhelper = new SessionHelper();
    }

    // Viser brugerens side når man tilgår /users
    @GetMapping("/users/")
    public String index(Model model, HttpSession session) {
        logger.log("index(Model model): START");
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            model.addAttribute("users", userservice.getUsers());
            logger.log("index(Model model): END");
            return "users/index";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("index(Model model): END");
            return "users/error";
        }
    }

    @GetMapping("/users/index")
    public String index2(Model model, HttpSession session)
    {
        logger.log("index2(Model model): START");
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "users/error";
        }
        try
        {
            model.addAttribute("users", userservice.getUsers());
            logger.log("index2(Model model): END");
            return "users/index";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("index2(Model model): END");
            return "users/error";
        }
    }

    // Getmapping <-- start -->
    // Viser siden hvor man kan oprette brugere når man tilgår /users/create/
    @GetMapping("/users/create")
    public String create(HttpSession session) {
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "users/error";
        }
        return "users/create";
    }

    // Getpost <-- start -->
    // Håndterer logikken for at oprette en bruger og interagerer med vores repository lag
    @PostMapping("/users/create")
    public String createUser(@ModelAttribute User user, HttpSession session){
        logger.log("createUser(@ModelAttribute User user): START");
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            userservice.createUser(user);
            logger.log("Created user", 1);
            logger.log("createUser(@ModelAttribute User user): END");
            return "redirect:/users/";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            logger.log("createUser(@ModelAttribute User user): END");
            return "redirect:/users/error";
        }

    }

    // Viser siden hvor man kan redigere en bruger når man tilgår /users/edit/
    @GetMapping("/users/edit/{userID}")
    public String edit(@PathVariable int userID, Model model, HttpSession session)
    {
        logger.log("edit(@PathVariable int userID, Model model) : START");
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            model.addAttribute("user", userservice.getUser(userID));
            logger.log("edit(@PathVariable int userID, Model model) : END");
            return "users/edit";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("edit(@PathVariable int userID, Model model) : END");
            return "users/error";
        }
    }

    // Håndterer logikken for at ændre en brugers oplysninger og interagerer med vores repository lag
    @PostMapping("/users/edit")
    public String editUser(
            @RequestParam(value="userID", required=true) int userID,
            @RequestParam(value="newUsername", required=true) String newUsername,
            @RequestParam(value="oldPassword", required=true) String oldPassword,
            @RequestParam(value="newPassword", required=true) String newPassword,
            HttpSession session
    )
    {
        logger.log("editUser(@ModelAttribute User user, @ModelAttribute int userID): START");
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            String username = userservice.getUser(userID).getUsername();
            if(userservice.validateLoginCredentials(username, oldPassword))
            {
                logger.log("User validation success new username: " + newUsername, 1);
                userservice.editUser(userID, new User(userID, newUsername, newPassword));
                logger.log("editUser(@ModelAttribute User user, @ModelAttribute int userID): END");
                return "redirect:/users/info/" + Integer.toString(userID);
            }
            else
                {
                    logger.log("User validation failed username: " + username, 1);
                    logger.log("editUser(@ModelAttribute User user, @ModelAttribute int userID): END");
                    return "redirect:/users/edit/" + Integer.toString(userID);
                }

        } catch (SQLException e) {
            logger.log("Error " + e.getMessage());
            logger.log("editUser(@ModelAttribute User user, @ModelAttribute int userID): END");
            return "redirect:/users/error";
        }

    }

    // Viser siden hvor man kan slette en bruger når man tilgår /users/delete/
    @GetMapping("/users/delete/{userID}")
    public String delete(@PathVariable int userID, Model model, HttpSession session)
    {
        logger.log("delete(@PathVariable int userID): START");
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            model.addAttribute("user", userservice.getUser(userID));
            logger.log("delete(@PathVariable int userID): END");
            return "users/delete";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("delete(@PathVariable int userID): END");
            return "users/error";
        }
    }

    // Håndterer logikken for at slette brugeren og interagerer med vores repository lag
    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam(value="userID", required=true) int userID, HttpSession session)
    {
        logger.log("deleteUser(@ModelAttribute int userID): START");
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            userservice.deleteUser(userID);
            logger.log("deleteUser(@ModelAttribute int userID): END");
            return "redirect:/users/";
        } catch (SQLException e) {
            logger.log("deleteUser(@ModelAttribute int userID): END");
            return "redirect:/users/error";
        }
    }

    // Viser siden hvor man kan logge ind når man tilgår /users/login/
    @GetMapping("/users/login")
    public String login() {
        return "users/login";
    }
    // Getmapping <-- /slut -->

    // Håndterer logikken for at validere en brugers loginoplsyninger og interagerer med vores repository lag
    @PostMapping("users/login")
    public String validateUserCredentials(@ModelAttribute User user, Model model, HttpSession session)
    {
        logger.log("validateUserCredentials(@ModelAttribute User user, Model model): START");
        try {
            if(userservice.validateLoginCredentials(user))
            {
                logger.log("User validated", 1);
                session.setAttribute("user", user);
                session.setAttribute("isAdmin", new Boolean(true));
                logger.log("validateUserCredentials(@ModelAttribute User user, Model model): END");
                return "redirect:/";
            }
            else
                {
                    logger.log("User failed to validate. Username: "  + user.getUsername(), 1);
                    logger.log("validateUserCredentials(@ModelAttribute User user, Model model): END");
                    model.addAttribute("validationfailed", true);
                    return "users/login";
                }
        } catch (SQLException e) {
            logger.log("validateUserCredentials and error occured " + e.getMessage(), 1);
            logger.log("validateUserCredentials(@ModelAttribute User user, Model model): END");
            return "redirect:/users/error";
        }


    }

    // Håndterer logikken for at ændre vise en brugers oplysninger og interagerer med vores repository lag
    @GetMapping("/users/info/{userID}")
    public String showUser(@PathVariable int userID, Model model, HttpSession session)
    {
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "users/error";
        }
        logger.log("showUser(@RequestParam(value=\"userID\", required=true) int userID, Model model): START");
        try {
            model.addAttribute("user", userservice.getUser(userID));
            logger.log("showUser(@RequestParam(value=\"userID\", required=true) int userID, Model model): END");
            return "users/info";
        } catch (SQLException e) {
            logger.log("Error: " + e.getMessage(), 1);
            logger.log("showUser(@RequestParam(value=\"userID\", required=true) int userID, Model model): END");
            return "redirect:/users/error";
        }
    }

    @GetMapping("users/logout")
    public String logout(HttpSession session)
    {
        logger.log("logout(HttpSession session): START");
        session.removeAttribute("user");
        session.removeAttribute("isAdmin");
        logger.log("logout(HttpSession session): END");
        return "redirect:/";
    }

    @ModelAttribute ("SessionLog")
    public String logging(){
        return logger.getSessionLog();
    }

    @ModelAttribute ("isAdmin")
    public boolean isAdmin(HttpSession session){
        return sessionhelper.isAdmin(session);
    }

    @ModelAttribute("Menu")
    public Menu getMenu(){
        Menu m = new Menu();
        try {
            m.setTopMenu((new MenuService()).getMenuItems());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } return m;
    }
}