package tech.kea.assignment.repository;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The repository interface MenuRepoInterface defines methods for CRUD regarding menu-items.
 * */

@Repository
public interface MenuRepoInterface{
    public int createMenuItem(String name, int sortorder, int parentId, int blogId, String url)  throws SQLException;
    public void deleteMenuItem(int userID) throws SQLException;
    public void editMenuItem(int menuItemID, String name, int sortorder, int parentId, int blogId, String url) throws SQLException;

    public ResultSet getMenuItem(int menuItemID) throws SQLException;
    public ResultSet getMenuItems(int parentId) throws SQLException;
}