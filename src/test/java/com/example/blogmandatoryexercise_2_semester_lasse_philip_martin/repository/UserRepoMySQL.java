package com.example.blogmandatoryexercise_2_semester_lasse_philip_martin.repository;

import com.example.blogmandatoryexercise_2_semester_lasse_philip_martin.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepoMySQL implements UserRepoInterface {


    public ResultSet createUser(User user)  throws SQLException {return null; }
    public void deleteUser(int userID) throws SQLException {}
    public void editUser(User user)    throws SQLException {}
    public void validateLoginCredentials(String username, String password) throws SQLException {}

    public ResultSet getUser(int userID) throws SQLException{ return null; }
    public ResultSet getUsers() throws SQLException{ return null; }
}