package tech.kea.assignment.model;

import java.util.List;

public class Menu {
    private List<MenuItem> TopMenu;
    private String BottomMenu;

    public Menu(){

    }

    public List<MenuItem> getTopMenu() { return TopMenu; }
    public void setTopMenu (List<MenuItem> menuitem){
        TopMenu = menuitem;
    }
    public String getBottomMenu() {return null; }
    public void setBottomMenu(){}

}
