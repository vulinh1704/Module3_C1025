package service;
import entity.Category;
import entity.Product;
import lib.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IService<Product> {
    private final Connection connection;
    public ProductService() {
        connection = MySQLConnection.getConnection();
    }

    @Override
    public void add(Product product) {
        String sql = "insert into product(name, price, image, categoryId) values (?, ?, ?, ?);";
        // dấu ? đại diện cho dữ liệu nhập từ ngươi dùng
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getImage());
            statement.setInt(4, product.getCategory().getId());
            // set valule cho từng dấu ?
            statement.executeUpdate(); // lưu vào executeUpdate
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAll() {
        String sql = "select * from product";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            // đối tượng đại diện cho câu lệnh truy vấn đến CSDL
            ResultSet resultSet = statement.executeQuery(); // lấy ra dùng executeQuery
            // thực thi stament trên và trả lại kết quả vào resultSet;
            // resultSet.next() => trả true khi còn bản ghi, giúp lấy 1 bản ghi => khi chạy đến hết bản ghi thì trả lại false
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String image = resultSet.getString("image");
                Product product = new Product(id, name, price, image);
                list.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Product> getByNameContains(String keyword) {
        if(keyword == null) keyword = "";
        keyword = "%" + keyword + "%";
        System.out.println(keyword);
        String sql = "select product.*, category.name as nameCategory from product\n" +
                "         join category on product.categoryId = category.id\n" +
                "        where product.name like ?";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, keyword);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String image = resultSet.getString("image");

                int categoryId = resultSet.getInt("categoryId");
                String nameCategory = resultSet.getString("nameCategory");
                Category category = new Category(categoryId, nameCategory);
                Product product = new Product(id, name, price, image, category);
                list.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Product getById(int id) {
        String sql = "select product.*, category.name as \"categoryName\" from product \n" +
                "         join category on product.categoryId = category.id\n" +
                "         where product.id = ?;";
        Product foundProduct = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            String image = resultSet.getString("image");
            int categoryId = resultSet.getInt("categoryId");
            String nameCategory = resultSet.getString("categoryName");
            Category category = new Category(categoryId, nameCategory);
            foundProduct = new Product(id, name, price, image, category);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foundProduct;
    }

    @Override
    public void update(int id, Product product) {
        String sql = "update product set name = ?, price = ?, image = ?, categoryId = ? where id = ?;";
        // dấu ? đại diện cho dữ liệu nhập từ ngươi dùng
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getImage());
            statement.setInt(4, product.getCategory().getId());
            statement.setInt(5, id);
            // set valule cho từng dấu ?
            statement.executeUpdate(); // lưu vào executeUpdate
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleted(int id) {
        String sql = "delete from product where id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
