package com.example.lab4fx.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserList {
    List<User> users = new ArrayList<>(Arrays.asList(
            new User("Nikita", "nikita", "nikita"),
            new User("Admin", "admin", "admin"),
            new User("User", "user", "user")
    ));
    public UserList(List<User> users) {
        this.users = users;
    }
    public UserList(){}

    public void addUser(String name, String password, String login){
        users.add(new User(name, password, login));
    }

    public Boolean isExistUser(String login, String password) {
        return users.stream() //есть ли user
                .anyMatch(user ->
                        user.getPassword().equals(password)
                        && user.getLogin().equals(login));
    }

    //для дебага
    public void showUsersList() {
        for (User user : users) {
            System.out.println(user.getName());
        }
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
