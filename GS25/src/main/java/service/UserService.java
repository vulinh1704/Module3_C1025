package service;

import entity.Category;
import entity.Product;
import entity.User;
import lib.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    private Connection connection;

    public UserService() {
        this.connection = MySQLConnection.getConnection();
    }

    public User signIn(String username, String password) { // Read (Kiểm tra)
        String sql = "select * from user where username = ? and password = ?;";
        User foundUser = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            boolean isFoundUser = resultSet.next();
            if(isFoundUser) {
                int id = resultSet.getInt("id");
                foundUser = new User(id, username);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foundUser;
    }

    public void signUp(User user) { // Create
        String sql = "insert into user(username, password) values (?, ?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
