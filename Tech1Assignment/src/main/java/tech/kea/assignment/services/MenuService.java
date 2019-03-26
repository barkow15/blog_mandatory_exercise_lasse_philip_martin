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





    public ArrayList<MenuItem> getMenuItems(int parentId) throws SQLException {
        int[]menuqueue = new int[10];
        int menuposition = 0;


        ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
        ResultSet rs = MenuRepo.getMenuItems(parentId);
        int depth = 0;
        while(rs.next()) {
            while (menuposition > 0 && menuqueue[menuposition-1]!= rs.getInt("parentID")){
                menuposition--;
            }
            menuqueue[menuposition]=rs.getInt("id");
            menuposition++;
            String Title = rs.getString("name");
            switch (menuposition) {
                case 1:
                    Title = "<b>"+Title+"</b>";
                    depth = 1;
                    break;
                case 2:
                    Title =    "&nbsp;"+ Title;
                    depth = 2;
                    break;
                case 3:
                    Title = "&nbsp;&nbsp;" + Title;
                    depth = 3;
                    break;
                default:
                    Title = "&nbsp;&nbsp;&nbsp;&nbsp;" + Title;
                    depth = 4;
                    break;
            }
            String u = rs.getString("url");
            if (u==null) {
                u = "/posts/post/"+rs.getInt("blogID");

            }
            menuItemList.add(new MenuItem(rs.getInt("id"),Title,u, depth));
        }
        return(menuItemList);
    }

    public ArrayList<MenuItem> getMenuItems() throws SQLException {
        return getMenuItems(0);
    }

}
