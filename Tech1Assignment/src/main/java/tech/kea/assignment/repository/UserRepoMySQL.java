package tech.kea.assignment.repository;

import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * The UserRepoMySQL class implements methods that gives access to
 *   CRUD operations
 *   validations of users
 *   Requesting a single user
 *   Requesting all users
 *
 *   This class expects you to use a MySQL database
 *
 *   Users are expected to be located in the `users` table.
 *
 * The `users` table is constructed using the following SQL
 * CREATE TABLE `users` (
 *   `id` int(11) NOT NULL AUTO_INCREMENT,
 *   `name` varchar(255) DEFAULT NULL,
 *   `password` varchar(255) DEFAULT NULL,
 *   PRIMARY KEY (`id`),
 *   UNIQUE KEY `id_UNIQUE` (`id`),
 *   UNIQUE KEY `name_UNIQUE` (`name`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8
 */
@Repository
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
        String sql = "UPDATE users SET `name` = ?, `password` = md5(?) WHERE `id` = ?";
        PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        pstmt.setInt(3, userID);
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

    public void userMode() throws SQLException
    {
        conn.userMode();
    }

    public void adminMode() throws SQLException
    {
        conn.adminMode();
    }
}