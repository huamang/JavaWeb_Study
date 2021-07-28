package com.example.MyJavaWeb;

import org.junit.Test;

public class DaoTest {
    @Test
    public void test(){
        User user = new User();
        user.setUsername("admin");
        user.setPasswd("huamang123");

        UserDao dao = new UserDao();
        User returnUser = dao.login(user);
        System.out.println(returnUser);
    }
}
