package com.example.tobyexam;

import com.example.tobyexam.dao.NUserDao;
import com.example.tobyexam.dao.UserDao;
import com.example.tobyexam.model.User;

import java.sql.SQLException;

public class ExecuteTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UserDao userDao = new NUserDao();

        User user = User.builder()
                .id("keikang")
                .name("아이유")
                .password("1111").build();

        User result = userDao.get(user.getId());

        if(result != null){
            System.err.println("이미 있을 경우 삭제");
            userDao.deleteById(user.getId());
        }

        userDao.add(user);

        result = userDao.get(user.getId());
        System.out.println("result toString : "+result);
/*        try{
            userDao.testGraphizer();
        } catch (SQLException e) {
            System.out.println("SQLException msg : "+e.getMessage());
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException msg : "+e.getMessage());
            throw new RuntimeException(e);
        }*/


    }
}
