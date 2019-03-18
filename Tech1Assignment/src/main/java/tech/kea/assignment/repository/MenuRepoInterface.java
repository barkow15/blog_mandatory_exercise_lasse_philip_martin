package tech.kea.assignment.repository;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The repository interface MenuRepoInterface defines methods for CRUD regarding menu-items.
 * */

@Repository
public interface MenuRepoInterface{
    public int createMenuItem(String navn, int sortorder, int parentId, int blogId, String url)  throws SQLException;
    public ResultSet getMenuItem(int MenuID) throws SQLException;
    public ResultSet getMenuItems() throws SQLException;
}