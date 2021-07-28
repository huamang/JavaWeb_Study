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

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码
        req.setCharacterEncoding("utf-8");
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

        UserDao dao = new UserDao();
        int returnint = dao.register(user);

        if (returnint == 1){
            // 存储数据到Request域
            req.setAttribute("userinfo",user);
            req.getRequestDispatcher("/registsuccess").forward(req,resp);
        }else{
            req.getRequestDispatcher("/registfail").forward(req,resp);
        }
    }
}
