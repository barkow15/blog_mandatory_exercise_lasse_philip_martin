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

    /**
     * The class works to keep all items.
     * Items are collected in a tree like structure, each level of the tree can have n items and there is no max depth of the tree
     * Items on the same level of the tree are sorted by the sort order of the menu item
     */
    private class MenuList {
        MenuList menulist = null;
        MenuItem[] menuitems = null;
        int counter = 0;

        public MenuList(MenuItem menuitem){
            this.menuitems = new MenuItem[7];
            add(menuitem);

        }

        public void add (MenuItem menuitem){
            if(this.menuitems[0].getParentId() == menuitem.getParentId())
            {

            }
        }

        private void expand() {

        }
    }
}
