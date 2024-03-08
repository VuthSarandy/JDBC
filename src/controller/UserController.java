package controller;

import model.User;
import repository.UserRepository;

import java.util.List;

public class UserController {
    public static List<User> getAllUser(){
        return UserRepository.getAllUsers();
    }
}
