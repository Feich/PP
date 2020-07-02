package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }



    public void createUsersTable() {
        try (Connection connection = Util.getMysqlConnection()) {
            PreparedStatement stmt = connection.prepareStatement("create table if not exists user (id bigint auto_increment, name varchar(256), lastName varchar(256), age TINYINT, primary key (id))");
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getMysqlConnection()) {
            PreparedStatement stmt = connection.prepareStatement("DROP TABLE IF EXISTS user");
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getMysqlConnection()) {
            PreparedStatement stmt = connection.prepareStatement("insert user(name, lastName, age) values (?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void removeUserById(long id) {
        try (Connection connection = Util.getMysqlConnection()) {
            PreparedStatement stmt = connection.prepareStatement("delete FROM user where id = ? ");
            stmt.setLong(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (Connection connection = Util.getMysqlConnection()) {
            PreparedStatement stmt = connection.prepareStatement("select * from user");
            //stmt.executeUpdate();
            ResultSet result = stmt.executeQuery();
            List<User> res = new ArrayList<>();
            //System.out.println(res.size());
            while (result.next()) {
                res.add(new User(result.getString("name"),result.getString("lastName"), result.getByte("age")));
            }
            result.close();
            stmt.close();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getMysqlConnection()) {
            PreparedStatement stmt = connection.prepareStatement("TRUNCATE TABLE user");
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
