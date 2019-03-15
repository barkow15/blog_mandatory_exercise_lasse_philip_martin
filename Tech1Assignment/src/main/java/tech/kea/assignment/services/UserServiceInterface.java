package tech.kea.assignment.services;

import org.springframework.stereotype.Service;
import tech.kea.assignment.model.User;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public interface UserServiceInterface{
    public void createUser(User user)  throws SQLException;
    public void deleteUser(int userID) throws SQLException;
    public void editUser(int userID, User user)    throws SQLException;
    public boolean validateLoginCredentials(User user) throws SQLException;
    public boolean validateLoginCredentials(String username, String password) throws SQLException;
    public User getUser(int userID) throws SQLException;
    public ArrayList<User> getUsers() throws SQLException;
}