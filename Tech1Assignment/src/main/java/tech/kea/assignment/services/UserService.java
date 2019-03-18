package tech.kea.assignment.services;

import org.springframework.stereotype.Service;
import tech.kea.assignment.repository.UserRepoInterface;
import tech.kea.assignment.repository.UserRepoMySQL;
import tech.kea.assignment.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * The user service implementation, is used to:
 *   CRUD users
 *   validate users
 *   Get a single user from ID
 *   Get all users
 */
@Service
public class UserService implements UserServiceInterface {

    UserRepoInterface UserRepo;

    public UserService() throws SQLException, ClassNotFoundException {
        this(new UserRepoMySQL());
    }

    /**
     * If you prefer, you can use your own user repository object as long as it implements UserRepoInterface, as default a MySql database is used
     */
    public UserService(UserRepoInterface UserRepo)
    {
        this.UserRepo = UserRepo;
    }

    @Override
    public void createUser(User user) throws SQLException {
        UserRepo.createUser(user.getUsername(), user.getPassword());
    }

    @Override
    public void deleteUser(int userID) throws SQLException {
        UserRepo.deleteUser(userID);
    }

    @Override
    public void editUser(int userID, User user) throws SQLException {
        UserRepo.editUser(userID, user.getUsername(), user.getPassword());
    }

    @Override
    public boolean validateLoginCredentials(String username, String password) throws SQLException {
        return UserRepo.validateLoginCredentials(username, password);
    }

    @Override
    public boolean validateLoginCredentials(User user) throws SQLException {
        return validateLoginCredentials(user.getUsername(), user.getPassword());
    }

    @Override
    public User getUser(int userID) throws SQLException {
        ResultSet rs = UserRepo.getUser(userID);
        User user = null;
        if (rs.next()) {
            user = new User(rs.getInt("id"), rs.getString("name"));
        }
        return user;
    }

    @Override
    public ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> userlist = new ArrayList<User>();
        ResultSet rs = UserRepo.getUsers();
        while(rs.next()) {
            userlist.add(new User(rs.getInt("id"), rs.getString("name")));
        }
        return(userlist);
    }
}