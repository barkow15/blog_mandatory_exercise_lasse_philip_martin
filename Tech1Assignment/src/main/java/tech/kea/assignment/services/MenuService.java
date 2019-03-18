package tech.kea.assignment.services;

import org.springframework.stereotype.Service;
import tech.kea.assignment.model.MenuItem;
import tech.kea.assignment.repository.MenuRepoInterface;
import tech.kea.assignment.repository.MenuRepoMySQL;
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
public class MenuService implements MenuServiceInterface {

    MenuRepoInterface MenuRepo;

    public MenuService() throws SQLException, ClassNotFoundException {
        this(new MenuRepoMySQL());
    }

    /**
     * If you prefer, you can use your own user repository object as long as it implements UserRepoInterface, as default a MySql database is used
     */
    public MenuService(MenuRepoInterface MenuRepo)
    {
        this.MenuRepo = MenuRepo;
    }


    public void createMenuItem(String navn, int sortorder, int parentId, int blogId, String url) throws SQLException {
        MenuRepo.createMenuItem(navn, sortorder, parentId, blogId, url);
    }


    public void deleteMenuItem(int userID) throws SQLException {
       // MenuRepo.deleteMenuItem(userID);
    }


    public void editUser(int userID, User user) throws SQLException {
        //MenuRepo.editUser(userID, user.getUsername(), user.getPassword());
    }



/*
    @Override
    public User getUser(int userID) throws SQLException {
        ResultSet rs = UserRepo.getUser(userID);
        User user = null;
        if (rs.next()) {
            user = new User(rs.getInt("id"), rs.getString("name"));
        }
        return user;
    }
*/

    public ArrayList<MenuItem> getMenuItems() throws SQLException {
        ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
        ResultSet rs = MenuRepo.getMenuItems();
        while(rs.next()) {
           menuItemList.add(new MenuItem(rs.getInt("id"),rs.getString("name"),rs.getString("url") ));
        }
        return(menuItemList);
    }
}
