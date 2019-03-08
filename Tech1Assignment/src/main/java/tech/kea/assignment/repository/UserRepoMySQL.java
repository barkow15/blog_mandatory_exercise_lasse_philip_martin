package tech.kea.assignment.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepoMySQL implements UserRepoInterface
{
    DatabaseConnectionMySQL conn = null;

    public UserRepoMySQL() throws SQLException, ClassNotFoundException {
        conn = new DatabaseConnectionMySQL();
    }

    public int createUser(String username, String password)  throws SQLException
    {
        String sql = "INSERT INTO users (`name`, `password`) VALUES (?, md5(?))";
        PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        return pstmt.executeUpdate();
    }

    public void deleteUser(int userID) throws SQLException
    {
        String sql = "DELETE FROM users WHERE `id` = ?";
        PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);
        pstmt.setInt(1, userID);
        pstmt.executeUpdate();
    }

    public void editUser(int userID, String username, String password)    throws SQLException
    {
        String sql = "UPATE users SET `name` = ?, `password` = ? WHERE id = ?";
        PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);
        pstmt.setInt(1, userID);
        pstmt.setString(2, username);
        pstmt.setString(3, password);
        pstmt.executeUpdate();
    }

    public boolean validateLoginCredentials(String username, String password) throws SQLException
    {
        String sql = "SELECT * FROM users WHERE name = ? AND password = md5(?)";
        PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        return (pstmt.executeQuery()).next();
    }

    public ResultSet getUser(int userID) throws SQLException
    {
        String sql = "SELECT * FROM users WHERE `id` = ?";
        PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);
        pstmt.setInt(1, userID);
        return pstmt.executeQuery();
    }

    public ResultSet getUsers() throws SQLException
    {
        String sql = "SELECT * FROM users ORDER BY `name`";
        PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);
        return pstmt.executeQuery();
    }
}