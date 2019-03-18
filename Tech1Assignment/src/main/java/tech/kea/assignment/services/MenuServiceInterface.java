package tech.kea.assignment.services;

import tech.kea.assignment.model.MenuItem;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MenuServiceInterface {
    public ArrayList<MenuItem> getMenuItems() throws SQLException;
}
