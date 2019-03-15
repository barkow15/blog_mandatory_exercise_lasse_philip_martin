package tech.kea.assignment.repository;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public interface UserRepoInterface{
    public int createUser(String username, String password)  throws SQLException;
    public void deleteUser(int userID) throws SQLException;
    public void editUser(int userID, String username, String password)    throws SQLException;
    public boolean validateLoginCredentials(String username, String password) throws SQLException;

    public ResultSet getUser(int userID) throws SQLException;
    public ResultSet getUsers() throws SQLException;
}