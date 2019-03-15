package tech.kea.assignment.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BlogRepoInterface{
    public int createPost(String title, String content, boolean hidden)  throws SQLException;
    public void   deletePost(int id)        throws SQLException;
    public void   editPost(int postID,String title, String content, boolean hidden)       throws SQLException;
    public ResultSet   getPost(int id)       throws SQLException;
    public ResultSet   getPosts()            throws SQLException;
}