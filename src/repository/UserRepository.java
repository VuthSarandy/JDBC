package repository;

import model.User;
import unitl.LoadProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

        public  List<User> userData () {
            String sql = "SELECT * FROM users";
            List<User> users = new ArrayList<>();
            LoadProperties.loadProperties();
            try (
                    Connection connection = DriverManager.getConnection(
                        LoadProperties.properties.getProperty("database_URL"),
                        LoadProperties.properties.getProperty("database_username"),
                        LoadProperties.properties.getProperty("database_password"));
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);
                    ) {
                    while (resultSet.next()){
                        users.add( new User(
                                resultSet.getInt("user_id"),
                                resultSet.getString("user_name"),
                                resultSet.getString("user_gender"),
                                resultSet.getString("user_email")
                        ));
                    }
            }
            catch (SQLException e) {

            }
            return  users;
        }
        public User insertUser (User user){
            LoadProperties.loadProperties();
           String sql = "INSERT INTO users ( user_name, user_gender, user_email) VALUES (?, ?, ?)";
            try (
                    Connection connection = DriverManager.getConnection(
                            LoadProperties.properties.getProperty("database_URL"),
                            LoadProperties.properties.getProperty("database_username"),
                            LoadProperties.properties.getProperty("database_password"));
                   PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ){
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getUserGender());
                preparedStatement.setString(3, user.getUserEmail());
                try (ResultSet resultSet = preparedStatement.executeQuery()){
                        while (resultSet.next()) {
                            return new User(
                                    resultSet.getInt("user_id"),
                                    resultSet.getString("user_name"),
                                    resultSet.getString("user_gender"),
                                    resultSet.getString("user_email")
                            );
                        }
                }

            }
            catch (SQLException e){
            }
            return new User();
        }
        public Integer deleteById (Integer id) {
            String sql = "DELETE FROM users WHERE user_id = ?";
            try (
                    Connection connection = DriverManager.getConnection(
                            LoadProperties.properties.getProperty("database_URL"),
                            LoadProperties.properties.getProperty("database_username"),
                            LoadProperties.properties.getProperty("database_password"));
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {

            }
            return id;
        }
        public User updateUser (User user)  {
            LoadProperties.loadProperties();
            String sql = "update users SET user_id = ?,  user_name = ?, user_gender = ? , user_email = ? where user_id = ?";
            try (
                    Connection connection = DriverManager.getConnection(
                            LoadProperties.properties.getProperty("database_URL"),
                            LoadProperties.properties.getProperty("database_username"),
                            LoadProperties.properties.getProperty("database_password"));
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ){
                preparedStatement.setString(2, user.getUserName());
                preparedStatement.setString(2, user.getUserName());
                preparedStatement.setString(3, user.getUserEmail());
                preparedStatement.setInt(4, user.getUserId());
                try (ResultSet resultSet = preparedStatement.executeQuery()){
                    while (resultSet.next()) {
                        return new User(
                                resultSet.getInt("user_id"),
                                resultSet.getString("user_name"),
                                resultSet.getString("user_gender"),
                                resultSet.getString("user_email")
                        );
                    }
                }
            }
            catch (SQLException e) {
            }
            return  user;
        }

}
