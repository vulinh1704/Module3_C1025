package controller;

import entity.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProductService;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "ProductController", value = "/products")
public class ProductController extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        if (page == null) page = "";
        switch (page) {
            case "home":
                showHomePage(req, resp);
                break;
            case "add":
                showAddPage(req, resp);
                break;
            case "edit":
                showEditPage(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
            default:
                showNotFound(req, resp);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idDelete = Integer.parseInt(req.getParameter("id"));
        productService.deleted(idDelete);
        resp.sendRedirect("/products?page=home");
    }

    private void showEditPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product foundProduct = productService.getById(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("products/edit.jsp");
        req.setAttribute("foundProduct", foundProduct);
        dispatcher.forward(req, resp);
    }

    private void showHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("products/home.jsp");
        String keyword = req.getParameter("keyword");
        List<Product> products = productService.getByNameContains(keyword);
        req.setAttribute("products", products);
        dispatcher.forward(req, resp);
    }

    private void showAddPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("products/add.jsp");
        dispatcher.forward(req, resp);
    }

    private void showNotFound(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("errors/not-found.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "add":
                addProduct(req, resp);
                break;
            case "edit":
                editProduct(req, resp);
                break;
            default:
                showNotFound(req, resp);
        }
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String image = req.getParameter("image");
        Product newProduct = new Product(id, name, price, image);
        productService.update(id, newProduct);
        resp.sendRedirect("/products?page=home");
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String name = req.getParameter("name");
       double price = Double.parseDouble(req.getParameter("price"));
       String image = req.getParameter("image");
       Product newProduct = new Product(name, price, image);
       productService.add(newProduct);
       resp.sendRedirect("/products?page=home");
    }
}


/*
Nhiệm vụ cần hoàn thành:
Thực hành theo demo
Hoàn thành năng xóa
Thêm tính năng tìm kiếm: tên sản phẩm gần đúng, khoảng giá, khoảng số lượng
 */
