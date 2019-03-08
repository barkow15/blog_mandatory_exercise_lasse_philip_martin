package tech.kea.assignment.services;

import tech.kea.assignment.repository.UserRepoInterface;
import tech.kea.assignment.repository.UserRepoMySQL;
import tech.kea.assignment.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService implements UserServiceInterface {

    UserRepoInterface UserRepo;

    public UserService() throws SQLException, ClassNotFoundException {
        this(new UserRepoMySQL());
    }

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
    public boolean validateLoginCredentials(User user) throws SQLException {
        return UserRepo.validateLoginCredentials(user.getUsername(), user.getPassword());
    }

    @Override
    public User getUser(int userID) throws SQLException {
        ResultSet rs = UserRepo.getUser(userID);
        User user = null;
        if (rs.next()) {
            user = new User(rs.getString("username"), null);
        }
        return user;
    }

    @Override
    public List<User> getUsers() throws SQLException {
        List<User> userlist = new ArrayList<User>();
        ResultSet rs = UserRepo.getUsers();
        while(rs.next()) {
            userlist.add(new User(rs.getString("username"), null));
        }
        return(userlist);
    }
}