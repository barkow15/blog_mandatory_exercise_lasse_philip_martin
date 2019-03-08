package tech.kea.assignment.repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BlogRepoMySQL implements BlogRepoInterface {
    @Autowired
    DatabaseConnectionMySQL dbCon;

    public BlogRepoMySQL(){

    }

    public int createPost(String title, String content, boolean hidden)  throws SQLException {
        String SQL= "INSERT INTO posts (title,content,hidden) VALUES (?,?,?)";
        PreparedStatement preparedStatment = dbCon.getConnection().prepareStatement(SQL);
        preparedStatment.setString(1,title);
        preparedStatment.setString(2,content);
        preparedStatment.setBoolean(3,hidden);
        return preparedStatment.executeUpdate();

    }
    public void   deletePost(int id)        throws SQLException{

    }
    public void   editPost(String title, String content, boolean hidden)       throws SQLException{

    }
    public ResultSet   getPost(int id)       throws SQLException{
        return null;
    }
    public ResultSet   getPosts()            throws SQLException{
        return null;
    }
}