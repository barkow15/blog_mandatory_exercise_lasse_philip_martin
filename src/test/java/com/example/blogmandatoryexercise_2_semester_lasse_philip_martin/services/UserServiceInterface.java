package com.example.blogmandatoryexercise_2_semester_lasse_philip_martin.services;

import com.example.blogmandatoryexercise_2_semester_lasse_philip_martin.model.User;
import java.sql.SQLException;

public interface UserServiceInterface{
    public void createUser(User user)  throws SQLException;
    public void deleteUser(int userID) throws SQLException;
    public void editUser(User user)    throws SQLException;
    public void validateLoginCredentials(String username, String password) throws SQLException;

    public User getUser(int userID) throws SQLException;
    public User[] getUsers() throws SQLException;
}