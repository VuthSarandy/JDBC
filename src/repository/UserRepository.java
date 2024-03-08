package repository;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public static List<User> getAllUsers(){
        List<User> userList = new ArrayList<>();
        LoadPropertyFile.LoadPropertiesFile();
        String sql = """
                    SELECT * FROM users
                    """;
        try(
                Connection connection = DriverManager.getConnection(
                        LoadPropertyFile.properties.getProperty("database_url"),
                        LoadPropertyFile.properties.getProperty("database_username"),
                        LoadPropertyFile.properties.getProperty("database_password")
                );
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

        )
        {
    while (resultSet.next()){
            userList.add( new User(
                   resultSet.getInt("id"),
                           resultSet.getString("name"),
                           resultSet.getString("gender"),
                           resultSet.getString("email")
                    )
            );
    }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return userList;
    }
}