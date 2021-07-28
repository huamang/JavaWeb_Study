package com.example.MyJavaWeb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registsuccess")
public class RegistSuccess extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设定编码,用response
        resp.setContentType("text/html;charset=utf-8");
        //获取Request域中共享的user对象,由于我们知道这个user是一个User类，所以可以做强制类型转换
//        Object user = req.getAttribute("userinfo");
        User user = (User) req.getAttribute("userinfo");
        resp.getWriter().write("注册成功，"+user.getUsername()+"，欢迎你");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}

