package tech.kea.assignment.repository;

import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class MenuRepoMySQL implements MenuRepoInterface {
    DatabaseConnectionMySQL dbCon = null;

    public MenuRepoMySQL() throws SQLException, ClassNotFoundException {

        dbCon = new DatabaseConnectionMySQL();
    }



    public int createMenuItem(String name, int sortorder, int parentID, int blogID, String url)  throws SQLException {
        String SQL= "INSERT INTO menu (name,sortorder,parentId,blogId,url) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatment = dbCon.getConnection().prepareStatement(SQL);
        preparedStatment.setString(1,name);
        preparedStatment.setInt(2,sortorder);
        preparedStatment.setInt(3,parentID);
        if(blogID > 0)
        {
            preparedStatment.setInt(4,blogID);
            preparedStatment.setNull(5,Types.VARCHAR);
        }
        else
            {
                preparedStatment.setNull(4, Types.INTEGER);
                preparedStatment.setString(5,url);
            }

        return preparedStatment.executeUpdate();
    }

    public void deleteMenuItem(int menuItemID) throws SQLException{
        String SQL = "DELETE FROM menu WHERE id = ?";
        PreparedStatement preparedStatment = dbCon.getConnection().prepareStatement(SQL);
        preparedStatment.setInt(1,menuItemID);
        preparedStatment.executeUpdate();
    }
    public void editMenuItem(int menuItemID, String name, int sortorder, int parentID, int blogID, String url) throws SQLException{
        String SQL = "UPDATE menu SET name = ?, sortorder = ?, parentId = ?, blogId = ?, url = ? WHERE id = ?";
        PreparedStatement preparedStatment = dbCon.getConnection().prepareStatement(SQL);
        preparedStatment.setString(1,name);
        preparedStatment.setInt(2,sortorder);
        preparedStatment.setInt(3,parentID);
        preparedStatment.setInt(4,blogID);
        preparedStatment.setString(5,url);
        preparedStatment.setInt(6,menuItemID);
        preparedStatment.executeUpdate();
    }

    public ResultSet getMenuItem(int menuID) throws SQLException
    {
        String sql = "SELECT * FROM menu WHERE `id` = ?";
        PreparedStatement pstmt = dbCon.getConnection().prepareStatement(sql);
        pstmt.setInt(1, menuID);
        return pstmt.executeQuery();
    }

    public ResultSet getMenuItems(int parentId) throws SQLException {
        // String sql = "SELECT * FROM menu ORDER BY `parentID`,`sortorder`";
        String sql = "select blogID,url,id,\n" +
                "                name,\n" +
                "                parentid\n" +
                "        from(select * from menu\n" +
                "                order by sortorder) products_sorted,\n" +
                "                (select @pv :='" + parentId + "')initialisation\n" +
                "        where find_in_set (parentid,@pv)\n" +
                "        and length ( @pv :=concat( @pv,',', id)) ORDER BY parentId, sortorder";
        PreparedStatement pstmt = dbCon.getConnection().prepareStatement(sql);
        return pstmt.executeQuery();

    }
}

