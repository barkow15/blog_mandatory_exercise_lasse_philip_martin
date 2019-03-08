package tech.kea.assignment.services;

import tech.kea.assignment.model.User;
import java.sql.SQLException;
import java.util.List;

public interface UserServiceInterface{
    public void createUser(User user)  throws SQLException;
    public void deleteUser(int userID) throws SQLException;
    public void editUser(int userID, User user)    throws SQLException;
    public boolean validateLoginCredentials(User user) throws SQLException;

    public User getUser(int userID) throws SQLException;
    public List<User> getUsers() throws SQLException;
}