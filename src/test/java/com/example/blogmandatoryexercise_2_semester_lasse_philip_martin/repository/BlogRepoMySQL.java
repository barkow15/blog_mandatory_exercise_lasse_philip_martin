package com.example.blogmandatoryexercise_2_semester_lasse_philip_martin.repository;

import com.example.blogmandatoryexercise_2_semester_lasse_philip_martin.model.Post;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BlogRepoMySQL implements BlogRepoInterface {
    @Autowired
    DatabaseConnectionMySQL dbCon;

    public BlogRepoMySQL(){

    }

    public int createPost(Post post)  throws SQLException {
        return 0;
    }
    public void   deletePost(int id)        throws SQLException{

    }
    public void   editPost(Post post)       throws SQLException{

    }
    public ResultSet   getPost(int id)       throws SQLException{
        return null;
    }
    public ResultSet   getPosts()            throws SQLException{
        return null;
    }
}