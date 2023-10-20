package com.example.tobyexam;

import com.example.tobyexam.dao.UserDao;

import java.sql.SQLException;

public class ExecuteTest {
    public static void main(String[] args) {

        UserDao userDao = new UserDao();

        try{
            userDao.testGraphizer();
        } catch (SQLException e) {
            System.out.println("SQLException msg : "+e.getMessage());
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException msg : "+e.getMessage());
            throw new RuntimeException(e);
        }


    }
}
