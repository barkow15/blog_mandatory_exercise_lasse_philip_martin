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
 * The menu service implementation, is used to:
 *   CRUD menu items
 *   Get a single menu item from ID
 *   Get all menu items
 */
@Service
public class MenuService implements MenuServiceInterface {

    private MenuRepoInterface MenuRepo;

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


    public void createMenuItem(MenuItem menuitem) throws SQLException {
        MenuRepo.createMenuItem(menuitem.getName(), menuitem.getSortorder(), menuitem.getParentId(), menuitem.getBlogId(), menuitem.getUrl());
    }


    /**
     * Delete a menu item and all sub items
     */
    public void deleteMenuItem(int menuItemID) throws SQLException {
        deleteMenuItem(menuItemID, true);
    }

    public void deleteMenuItem(int menuItemID, boolean includesubitems) throws SQLException {
       MenuRepo.deleteMenuItem(menuItemID);
       if(includesubitems)
       {
           ResultSet rs = MenuRepo.getMenuItems(menuItemID);
           while(rs.next())
           {
               deleteMenuItem(rs.getInt("id"), false);
           }
       }
    }

    public void editMenuItem(MenuItem menuitem) throws SQLException {
        MenuRepo.editMenuItem(menuitem.getId(), menuitem.getName(), menuitem.getSortorder(), menuitem.getParentId(), menuitem.getBlogId(), menuitem.getUrl());
    }

    public MenuItem getMenuItem(int menuItemID) throws SQLException{
        ResultSet rs = MenuRepo.getMenuItem(menuItemID);
        if(rs.next()){
            return new MenuItem(rs.getInt("id"), rs.getString("name"), rs.getInt("sortorder"), rs.getInt("parentId"), rs.getInt("blogId"), rs.getString("url"));
        }
        return null;
    }

    /**
     * Build an ArrayList of all menu items that has the parent that is given. Each level is called individually
     */
    public ArrayList<MenuItem> getMenuItems(int parentId, int depth) throws SQLException {
        ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
        ResultSet rs = MenuRepo.getMenuItems(parentId);
        while(rs.next()) {
            menuItemList.add(new MenuItem(rs.getInt("id"),rs.getString("name"),rs.getInt("sortorder"), rs.getInt("parentID"),rs.getInt("blogID"), rs.getString("url"), depth));
            //Lets add all sub branches
            ArrayList<MenuItem> children = getMenuItems(rs.getInt("id"), depth+1);
            if(children.size() > 0)
            {
                menuItemList.addAll(children);
            }
        }
        return(menuItemList);
    }

    public ArrayList<MenuItem> getMenuItems() throws SQLException {
        return getMenuItems(0, 0);
    }

}
