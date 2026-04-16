package com.codegym.jsp_and_jstl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="productServlet", value = "/products")
public class ProductServlet extends HelloServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getAttribute("name").toString();
        PrintWriter writer = response.getWriter();
        writer.write("<h1> Hello " + name + "</h1>");
    }
}
