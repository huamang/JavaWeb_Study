package com.example.MyJavaWeb;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 操控数据库中的user表的对象
 */

public class UserDao {
    //创建JdbcTemplate对象
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 登陆方法
     * @param loginUser
     * @return 查询成功就返回user，查询失败就返回null
     */
    public User login(User loginUser){
        // 用try catch来包裹，当查询成功就返回user，查询失败就返回null
        try {
            String sql = "select * from user where username = ? and passwd = ?";
            //使用queryForObject方法查询并封装成user类，注意使用RowMapper
            User user = template.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(),
                    loginUser.getPasswd()
            );
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 注册方法
     * @param registerUser
     * @return 注册成功就返回1，失败就返回null
     */
    /**
     * 注册方法
     *
     * @param registerUser
     * @return 注册成功就返回1，失败就返回null
     */
    public int register(User registerUser) {
        try {
            String sql = "INSERT INTO user (id, username, passwd) VALUES (NULL, ?, ?);";

            int user = template.update(
                    sql,
                    registerUser.getUsername(),
                    registerUser.getPasswd()
            );
            System.out.println(user);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
