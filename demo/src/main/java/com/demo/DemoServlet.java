package com.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "demoServlet" , value = "/c10")
public class DemoServlet extends HttpServlet {
    // gửi lên trên server theo method GET với đường dẫn /c10
    // form (method Get), URL, the a => Hiển thị (tìm kiếm)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hehe");
        PrintWriter writer = resp.getWriter();
        writer.write("<h1> Hello World </h1>");
    }

    // form (method Post)
    // dành cho đăng ký đăng, form thêm hoặc sửa dữ liệu cần truyền nhiều thông
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}


// Để gửi 1 request method GET trên server thì có 3 cách:
//Cách 1: nhấn 1 link
//Cách 2: Nhập trực tiếp trên thanh đường dẫn
//Cách 3: Gửi form theo method GET
//Để gửi 1 request method POST trên server sử dụng form method POST

// JSP and JSTL

