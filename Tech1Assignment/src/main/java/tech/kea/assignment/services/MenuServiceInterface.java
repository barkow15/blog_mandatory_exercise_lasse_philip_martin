package tech.kea.assignment.services;

import tech.kea.assignment.model.MenuItem;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MenuServiceInterface {
    public MenuItem getMenuItem(int menuItemID) throws SQLException;
    public ArrayList<MenuItem> getMenuItems() throws SQLException;
    public void createMenuItem(MenuItem menuitem) throws SQLException;
    public void deleteMenuItem(int menuItemID) throws SQLException;
    public void editMenuItem(MenuItem menuitem) throws SQLException;
}
