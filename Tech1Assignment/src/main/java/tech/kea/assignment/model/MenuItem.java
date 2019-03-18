package tech.kea.assignment.model;

import java.lang.reflect.Array;

public class MenuItem {
    private int        id;
    private String     title;
    private String     url;
    private MenuItem[] menuItems;


    public MenuItem(int id, String title, String url){

    }

    public MenuItem(int id, String title, String url, MenuItem[] menuItems){

    }
}
