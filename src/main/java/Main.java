import model.UserModel;
import org.example.group6minibookstore.entities.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // create the DAO object
        UserModel userModel = new UserModel();

        // create and save a new user
        User user = new User();
        user.setUsername("peter");
        user.setPassword("12345");
        user.setRole("customer");
        //userModel.register(user);

        // get a user by id
        User u = userModel.getUserById(1L);
        if (u != null) {
            System.out.println("User with ID 1: " + u.getUsername());
        } else {
            System.out.println("No user found with ID 1");
        }

        // get all users
        List<User> allUsers = userModel.getUsers();
        for (User usr : allUsers) {
            System.out.println("User: " + usr.getUsername() + " (" + usr.getRole() + ")");
        }

       // login test
        User loggedIn = userModel.login("peter", "12345");
        if (loggedIn != null) {
            System.out.println("Login successful: " + loggedIn.getUsername());
        } else {
            System.out.println("Login failed");
        }

       // update a user
        if (u != null) {
            u.setPassword("newpass");
            userModel.updateUser(u);
            System.out.println("Updated password for: " + u.getUsername());
        }
    }
}
