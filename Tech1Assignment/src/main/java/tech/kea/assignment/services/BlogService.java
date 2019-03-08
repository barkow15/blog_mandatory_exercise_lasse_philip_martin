package tech.kea.assignment.services;

import tech.kea.assignment.model.Post;

import java.sql.SQLException;

public class BlogService implements BlogServiceInterface{

    @Override
    public int createPost(Post post) throws SQLException {
        return 0;
    }

    @Override
    public void deletePost(int id) throws SQLException {

    }

    @Override
    public void editPost(Post post) throws SQLException {

    }

    @Override
    public Post getPost(int id) throws SQLException {
        return null;
    }

    @Override
    public Post[] getPosts() throws SQLException {
        return new Post[0];
    }
}