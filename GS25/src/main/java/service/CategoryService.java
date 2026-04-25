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

public class CategoryService implements IService<Category> {
    private final Connection connection;

    public CategoryService() {
        connection = MySQLConnection.getConnection();
    }

    @Override
    public void add(Category category) {

    }

    @Override
    public List<Category> getAll() {
        String sql = "select * from category;";
        List<Category> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category(id, name);
                list.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Category getById(int id) {
        return null;
    }

    @Override
    public void update(int id, Category category) {

    }

    @Override
    public void deleted(int id) {

    }
}
