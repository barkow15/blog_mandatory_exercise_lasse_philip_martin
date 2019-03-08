package com.example.blogmandatoryexercise_2_semester_lasse_philip_martin.repository;

import com.example.blogmandatoryexercise_2_semester_lasse_philip_martin.model.Post;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface BlogRepoInterface{
    public int createPost(Post post)  throws SQLException;
    public void   deletePost(int id)        throws SQLException;
    public void   editPost(Post post)       throws SQLException;
    public ResultSet   getPost(int id)       throws SQLException;
    public ResultSet   getPosts()            throws SQLException;
}