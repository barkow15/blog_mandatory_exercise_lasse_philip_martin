package Tech.KEA.Assignment1.repository;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepoMySQL implements UserRepoInterface {


    public ResultSet createUser(String username, String password)  throws SQLException {return null; }
    public void deleteUser(int userID) throws SQLException {}
    public void editUser(String username, String password)    throws SQLException {}
    public void validateLoginCredentials(String username, String password) throws SQLException {}

    public ResultSet getUser(int userID) throws SQLException{ return null; }
    public ResultSet getUsers() throws SQLException{ return null; }
}