package tech.kea.assignment.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.kea.assignment.model.Menu;
import tech.kea.assignment.model.MenuItem;
import tech.kea.assignment.services.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;


/**
 * Contains user CRUD operations and validation
 * */
@Controller
public class MenuController{

    @Autowired
    private Logging logger;
    @Autowired
    private MenuServiceInterface menuservice;
    @Autowired
    private SessionHelper sessionhelper;
    public MenuController()
    {
        logger = new Logging("MenuController");

        sessionhelper = new SessionHelper();
    }

    @GetMapping("menu/")
    public String getMenuRoot(){
        return("menu/index");
    }

    @GetMapping("menu/index")
    public String getMenuRootIndex(){
        return("menu/index");
    }

    @GetMapping("menu/info/{menuItemID}")
    public String info(@PathVariable int menuItemID, Model model, HttpSession session)
    {
        logger.log("info(@PathVariable int menuItemID, Model model, HttpSession session) : START");
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "menu/error";
        }
        try {
            model.addAttribute("menuitem", menuservice.getMenuItem(menuItemID));
            logger.log("info(@PathVariable int menuItemID, Model model, HttpSession session) : END");
            return "menu/info";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("info(@PathVariable int menuItemID, Model model, HttpSession session) : END");
            return "menu/error";
        }
    }

    @GetMapping("/menu/create")
    public String create(HttpSession session) {
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "menu/error";
        }
        return "menu/create";
    }

    @PostMapping("/menu/create")
    public String createMenuItem(            @RequestParam(value="name", required=true) String menuitemID,
                                             @RequestParam(value="sortorder", required=true) int sortorder,
                                             @RequestParam(value="parentId", required=true) int parentId,
                                             @RequestParam(value="blogId", defaultValue="0") int blogId,
                                             @RequestParam(value="url", defaultValue="") String url, HttpSession session){
        logger.log("createMenuItem(@ModelAttribute MenuItem menuitem): START");
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "menu/error";
        }
        try {
            menuservice.createMenuItem(new MenuItem(0, menuitemID, sortorder, parentId, blogId, url));
            logger.log("Created menu item", 1);
            logger.log("createMenuItem(@ModelAttribute MenuItem menuitem): END");
            return "redirect:/menu/";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            logger.log("createMenuItem(@ModelAttribute MenuItem menuitem): END");
            return "redirect:/menu/error";
        }

    }

    // Viser siden hvor man kan redigere en bruger når man tilgår /users/edit/
    @GetMapping("/menu/edit/{menuitemID}")
    public String edit(@PathVariable int menuitemID, Model model, HttpSession session)
    {
        logger.log("edit(@PathVariable int menuitemID, Model model) : START");
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "menu/error";
        }
        try {
            model.addAttribute("menuitem", menuservice.getMenuItem(menuitemID));
            logger.log("edit(@PathVariable int menuitemID, Model model) : END");
            return "menu/edit";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("edit(@PathVariable int menuitemID, Model model) : END");
            return "menu/error";
        }
    }

     @PostMapping("/menu/edit")
    public String editUser(@ModelAttribute MenuItem menuitem,
            HttpSession session
    )
    {
        logger.log("editUser(@ModelAttribute MenuItem menuitem, @ModelAttribute int userID): START");
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "menu/error";
        }
        try {
            menuservice.editMenuItem(menuitem);
            logger.log("editUser(@ModelAttribute MenuItem menuitem, @ModelAttribute int userID): End");
            return "redirect:/menu/";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("editUser(@ModelAttribute MenuItem menuitem, @ModelAttribute int userID): End");
            return "menu/error";
        }

    }

    @GetMapping("/menu/delete/{menuitemID}")
    public String delete(@PathVariable int menuitemID, Model model, HttpSession session)
    {
        logger.log("delete(@PathVariable int menuitemID): START");
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "menu/error";
        }
        try {
            model.addAttribute("menuitem", menuservice.getMenuItem(menuitemID));
            logger.log("delete(@PathVariable int menuitemID): END");
            return "menu/delete";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("delete(@PathVariable int menuitemID): END");
            return "menu/error";
        }
    }

    @PostMapping("/menu/delete")
    public String deleteMenuitem(@RequestParam(value="menuitemID", required=true) int menuitemID, HttpSession session)
    {
        logger.log("deleteMenuitem(@ModelAttribute int menuitemID): START");
        if(!sessionhelper.isAdmin(session))
        {
            logger.log("User access denied");
            return "menu/error";
        }
        try {
            menuservice.deleteMenuItem(menuitemID);
            logger.log("deleteMenuitem(@ModelAttribute int userID): END");
            return "redirect:/menu/";
        } catch (SQLException e) {
            logger.log("deleteMenuitem(@ModelAttribute int userID): END");
            return "redirect:/menu/error";
        }
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