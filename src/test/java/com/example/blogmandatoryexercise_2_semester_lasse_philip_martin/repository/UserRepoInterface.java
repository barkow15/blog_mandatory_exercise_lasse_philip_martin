package com.example.blogmandatoryexercise_2_semester_lasse_philip_martin.repository;

import com.example.blogmandatoryexercise_2_semester_lasse_philip_martin.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserRepoInterface{
    public ResultSet createUser(String username, String password)  throws SQLException;
    public void deleteUser(int userID) throws SQLException;
    public void editUser(String username, String password)    throws SQLException;
    public void validateLoginCredentials(String username, String password) throws SQLException;

    public ResultSet getUser(int userID) throws SQLException;
    public ResultSet getUsers() throws SQLException;
}