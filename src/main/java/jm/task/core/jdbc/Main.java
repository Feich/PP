package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Vova", "Putin", (byte) 4));
        users.add(new User("Sasha", "Morozov", (byte) 22));
        users.add(new User("Dasha", "Rain", (byte) 13));
        users.add(new User("Vika", "Putina", (byte) 24));
        for (User u : users) {
            userService.saveUser(u.getName(), u.getLastName(), u.getAge());
            System.out.println("User с именем - " + u.getName() + " добавлен в базу данных");
        }
        users = (ArrayList<User>) userService.getAllUsers();
        for (User u : users) {
            System.out.println(u.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
