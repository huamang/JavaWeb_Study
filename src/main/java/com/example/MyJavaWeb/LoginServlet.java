package com.example.MyJavaWeb;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import static org.apache.commons.beanutils.BeanUtils.*;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码
        req.setCharacterEncoding("utf-8");
//        // 获取参数
//        String username = req.getParameter("username");
//        String passwd = req.getParameter("passwd");
//        // 把参数封装成User对象
//        User user = new User();
//        user.setUsername(username);
//        user.setPasswd(passwd);
        // 获取所有参数到map
        Map<String, String[]> map = req.getParameterMap();
        // 创建User对象
        User user = new User();
        // 使用BeanUtils
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        // 调用login方法
        UserDao dao = new UserDao();
        User returnUser = dao.login(user);
        // 判断返回值，转发到结果servlet，如果查询成功还要存储数据到Request域
        if (returnUser == null){
            req.getRequestDispatcher("/failServlet").forward(req,resp);
        }else {
            // 存储数据到Request域
            req.setAttribute("userinfo",user);
            req.getRequestDispatcher("/successServlet").forward(req,resp);
        }

    }
}
