package Tech.KEA.Assignment1.services;

import Tech.KEA.Assignment1.model.User;
import java.sql.SQLException;

public interface UserServiceInterface{
    public void createUser(User user)  throws SQLException;
    public void deleteUser(int userID) throws SQLException;
    public void editUser(User user)    throws SQLException;
    public void validateLoginCredentials(String username, String password) throws SQLException;

    public User getUser(int userID) throws SQLException;
    public User[] getUsers() throws SQLException;
}