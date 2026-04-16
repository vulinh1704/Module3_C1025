package com.codegym.jsp_and_jstl;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = "Truong Dang Vu Linh";
        Student student = new Student(1, "Linh", 20);
        String[] names = {"Huong", "Long", "Duc"};
        RequestDispatcher dispatcher = request.getRequestDispatcher("ui/test.jsp");
        request.setAttribute("name", name);
        request.setAttribute("names", names);
        request.setAttribute("student", student);
        dispatcher.forward(request, response);
    }
}

// Thực hành theo demo
// Taọ 1 array list student và thực hiện hiển thị lên web.
// Xem và thực xây dựng dự án theo mô hình MVC record.