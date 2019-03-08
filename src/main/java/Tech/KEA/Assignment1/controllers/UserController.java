package Tech.KEA.Assignment1.controllers;

import Tech.KEA.Assignment1.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController{

    // Viser brugerens side når man tilgår /users/
    @GetMapping("/users/")
    public String index() {
        return "users/index";
    }
    // Getmapping <-- start -->
    // Viser siden hvor man kan oprette brugere når man tilgår /users/create/
    @GetMapping("/users/create/")
    public String create() {
        return "users/create";
    }

    // Viser siden hvor man kan redigere en bruger når man tilgår /users/edit/
    @GetMapping("/users/edit/")
    public String edit() {
        return "users/edit";
    }

    // Viser siden hvor man kan slette en bruger når man tilgår /users/delete/
    @GetMapping("/users/delete/")
    public String delete() {
        return "users/delete";
    }

    // Viser siden hvor man kan logge ind når man tilgår /users/login/
    @GetMapping("/users/login/")
    public String login() {
        return "users/login";
    }
    // Getmapping <-- /slut -->

    // Getpost <-- start -->
    // Håndterer logikken for at oprette en bruger og interagerer med vores repository lag
    @PostMapping("/users/create")
    public String createUser(@ModelAttribute User user){

        return "redirect:/users/";
    }

    // Håndterer logikken for at slette brugeren og interagerer med vores repository lag
    @PostMapping("/users/delete")
    public String deleteUser(@ModelAttribute int userID){

        return "redirect:/users/";
    }

    // Håndterer logikken for at ændre en brugers oplysninger og interagerer med vores repository lag
    @PostMapping("/users/edit")
    public String editUser(@ModelAttribute User user){

        return "redirect:/users/";
    }

    // Håndterer logikken for at ændre vise en brugers oplysninger og interagerer med vores repository lag
    @PostMapping("/users/info")
    public String showUser(@ModelAttribute User user){


        return "redirect:/users/info";
    }

    // Håndterer logikken for at validere en brugers loginoplsyninger og interagerer med vores repository lag
    @PostMapping("/users/login/")
    public String validateUserCredentials(@ModelAttribute User user){


        return "redirect:/users/";
    }
    // GetPost <-- /slut -->
}