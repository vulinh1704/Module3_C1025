package controller;

import entity.Category;
import entity.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CategoryService;
import service.ProductService;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "ProductController", value = "/products")
public class ProductController extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();

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
        List<Category> categories = categoryService.getAll();
        req.setAttribute("categories", categories);
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
        List<Category> categories = categoryService.getAll();
        req.setAttribute("categories", categories);
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
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Category category = new Category(categoryId);
        Product newProduct = new Product(id, name, price, image, category);
        productService.update(id, newProduct);
        resp.sendRedirect("/products?page=home");
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String name = req.getParameter("name");
       double price = Double.parseDouble(req.getParameter("price"));
       String image = req.getParameter("image");
       int categoryId = Integer.parseInt(req.getParameter("categoryId"));
       Category category = new Category(categoryId);
       Product newProduct = new Product(name, price, image, category);
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

/*
Trong bài kiểm tra:
Thêm 1 bảng là group có id, name.
Thực hiện cập nhật lại tính năng của bài kiểm tra
+ Thêm tính năng lọc sinh viên theo group.

Gợi ý về phần bảo mật:
+ Account, User: email, username + password
+ Nếu người dùng chưa đăng nhập thì phải cho về login -> sử dụng session ở servlet để đánh dấu user khi đăng nhập (ChatGPT)
+ Đăng ký: ~ làm tính năng Create
+ Đăng nhập: ~ điền username + password ->kiểm tra user theo 2 dữ liệu trên
-> nếu không có thì báo lỗi -> nếu có thì chuyển trang đến home
*/

/*
User <===> Role (Customer, Admin, Staff,...) -
                                      permission (home, điểm danh => Staff)
                                      permission ( * => staff)
n     <->   n
Thực thể:
Product (C , R , U,  D)
Finance
Currency

 */