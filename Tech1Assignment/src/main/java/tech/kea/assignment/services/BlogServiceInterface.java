package tech.kea.assignment.services;

import tech.kea.assignment.model.Post;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BlogServiceInterface{
    public int    createPost(Post post) throws SQLException;
    public void   deletePost(int id)    throws SQLException;
    public void   editPost(Post post)   throws SQLException;
    public Post   getPost(int id)       throws SQLException;
    public ArrayList<Post> getPosts()   throws SQLException;
}