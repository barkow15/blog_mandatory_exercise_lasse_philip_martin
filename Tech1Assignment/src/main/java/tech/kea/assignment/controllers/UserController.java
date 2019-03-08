package tech.kea.assignment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import tech.kea.assignment.model.User;
import tech.kea.assignment.services.UserService;
import tech.kea.assignment.services.UserServiceInterface;

import java.sql.SQLException;

@Controller
public class UserController{


    UserServiceInterface userservice = null;

    public UserController()
    {
        try {
            userservice = new UserService();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Viser brugerens side når man tilgår /users
    @GetMapping("/users/")
    public String index(Model model) {
        try {
            model.addAttribute("users", userservice.getUsers());
            return "/users/index";
        } catch (SQLException e) {
            return "/error";
        }
    }

    @GetMapping("/users/index")
    public String index2(Model model) {
        try {
            model.addAttribute("users", userservice.getUsers());
            return "/users/index";
        } catch (SQLException e) {
            return "/error";
        }
    }

    // Getmapping <-- start -->
    // Viser siden hvor man kan oprette brugere når man tilgår /users/create/
    @GetMapping("/users/create")
    public String create() {
        return "/users/create";
    }

    // Getpost <-- start -->
    // Håndterer logikken for at oprette en bruger og interagerer med vores repository lag
    @PostMapping("/users/create")
    public String createUser(@ModelAttribute User user){
        try {
            userservice.createUser(user);
            return "redirect:/users/";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "redirect:/error";
        }
    }

    // Viser siden hvor man kan redigere en bruger når man tilgår /users/edit/
    @GetMapping("/users/edit")
    public String edit() {
        return "/users/edit";
    }

    // Håndterer logikken for at ændre en brugers oplysninger og interagerer med vores repository lag
    @PostMapping("/users/edit")
    public String editUser(@ModelAttribute User user, @ModelAttribute int userID)
    {
        try {
            userservice.editUser(userID, user);
        } catch (SQLException e) {
            return "redirect:/error";
        }
        return "redirect:/users/";
    }

    // Viser siden hvor man kan slette en bruger når man tilgår /users/delete/
    @GetMapping("/users/delete")
    public String delete()
    {
        return "/users/delete";
    }

    // Håndterer logikken for at slette brugeren og interagerer med vores repository lag
    @PostMapping("/users/delete")
    public String deleteUser(@ModelAttribute int userID)
    {
        try {
            userservice.deleteUser(userID);
        } catch (SQLException e) {
            return "redirect:/error";
        }
        return "redirect:/users/";
    }

    // Viser siden hvor man kan logge ind når man tilgår /users/login/
    @GetMapping("/users/login")
    public String login() {
        return "/users/login";
    }
    // Getmapping <-- /slut -->

    // Håndterer logikken for at validere en brugers loginoplsyninger og interagerer med vores repository lag
    @PostMapping("/users/login")
    public String validateUserCredentials(@ModelAttribute User user)
    {
        try {
            if(userservice.validateLoginCredentials(user))
            {
                //User login successfull
                return "redirect:/users/";
            }
            else
                {
                //User login failed
                    return "redirect:/users/login";
                }
        } catch (SQLException e) {
            return "redirect:/error";
        }


    }

    // Håndterer logikken for at ændre vise en brugers oplysninger og interagerer med vores repository lag
    @PostMapping("/users/info")
    public String showUser(@ModelAttribute User user){


        return "redirect:/users/info";
    }


}