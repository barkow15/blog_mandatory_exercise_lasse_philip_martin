package Tech.KEA.Assignment1.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserRepoInterface{
    public ResultSet createUser(String username, String password)  throws SQLException;
    public void deleteUser(int userID) throws SQLException;
    public void editUser(String username, String password)    throws SQLException;
    public void validateLoginCredentials(String username, String password) throws SQLException;

    public ResultSet getUser(int userID) throws SQLException;
    public ResultSet getUsers() throws SQLException;
}