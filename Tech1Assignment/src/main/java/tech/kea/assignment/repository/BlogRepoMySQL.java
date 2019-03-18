package tech.kea.assignment.repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BlogRepoMySQL implements BlogRepoInterface {
    DatabaseConnectionMySQL dbCon = null;

    public BlogRepoMySQL() throws SQLException, ClassNotFoundException {
        dbCon = new DatabaseConnectionMySQL();
    }

    public int createPost(String title, String content, boolean hidden)  throws SQLException {
        String SQL= "INSERT INTO posts (title,content,hidden) VALUES (?,?,?)";
        PreparedStatement preparedStatment = dbCon.getConnection().prepareStatement(SQL);
        preparedStatment.setString(1,title);
        preparedStatment.setString(2,content);
        preparedStatment.setBoolean(3,hidden);
        return preparedStatment.executeUpdate();

    }
    public void   deletePost(int id) throws SQLException{
        // Vores SQL Statement
        String SQL = "DELETE FROM posts WHERE id = ?";

        // Forbered SQL Statement
        PreparedStatement preparedStatement = dbCon.getConnection().prepareStatement(SQL);

        // Setter ? i vores SQL String til at være ligmed id
        preparedStatement.setInt(1, id);

        // Eksekver SQL statement
        preparedStatement.executeUpdate();
    }
    public void   editPost(int id, String title, String content, boolean hidden) throws SQLException{
        // Vores SQL Statement
        String SQL = "UPDATE blogdat18a.posts SET title = ?, content = ?, hidden = ? WHERE id = ?";
        int hiddenInt;
        if(hidden) {
            hiddenInt = 1;
        }else{
            hiddenInt = 0;
        }
        // Forbered SQL Statement
        PreparedStatement preparedStatement = dbCon.getConnection().prepareStatement(SQL);

        // Setter første ? i vores SQL String til at være ligmed title
        preparedStatement.setString(1, title);
        // Setter andet ? i vores SQL String til at være ligmed content
        preparedStatement.setString(2, content);
        // Setter tredje ? i vores SQL String til at være ligmed hidden
        preparedStatement.setInt(3, hiddenInt);
        // Setter fjere ? i vores SQL String til at være ligmed id
        preparedStatement.setInt(4, id);

        // Eksekver SQL statement
        preparedStatement.executeUpdate();
    }
    public ResultSet   getPost(int id) throws SQLException{
        // Vores SQL Statement
        String SQL = "SELECT * FROM posts WHERE `id` = ?";
        // Forbered SQL Statement
        PreparedStatement pstmt = dbCon.getConnection().prepareStatement(SQL);
        // Setter fjere ? i vores SQL String til at være ligmed id
        pstmt.setInt(1, id);

        // Eksekver SQL statement og returner Resultset med den enkelte post
        return pstmt.executeQuery();
    }
    public ResultSet   getPosts() throws SQLException{
        // Vores SQL Statement hvor vi henter alle kolonner i tabellen posts
        String SQL = "SELECT * FROM posts WHERE `hidden` = 0";

        // Forbered SQL Statement
        PreparedStatement pstmt = dbCon.getConnection().prepareStatement(SQL);

        // Eksekver SQL statement og returner Resultset med den enkelte post
        return pstmt.executeQuery();
    }
}