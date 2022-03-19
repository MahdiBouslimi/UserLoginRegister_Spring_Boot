package com.example.user_login_register.Controller;

import com.example.user_login_register.Model.User;
import com.example.user_login_register.Repository.UserRepo;
import com.example.user_login_register.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;
    private final UserRepo userRepo;


    @PostMapping("/register")
    public User registerUser(@RequestBody User user) throws Exception {
        String templEmail = user.getEmail();
        if(templEmail !=null && !"".equals(templEmail)) {
            User userObj = userRepo.findByEmail(templEmail);
            if(userObj != null) {
                throw new Exception("User with " + templEmail + " is already exist");
            }
        }
        User userObj = null;
        userObj = userRepo.save(user);
        return userObj;
    }
    @PostMapping("/login")
    public User loginUser(@RequestBody User user) throws Exception {
        String tempEmail = user.getEmail();
        String tempPassword = user.getPassword();
        User userObj = null;
        if(tempEmail !=null && tempPassword !=null) {
            userObj = userRepo.findByEmailAndPassword(tempEmail, tempPassword);
        }
        if (userObj == null) {
            throw new Exception("Error to login");
        }
        return userObj;    }
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
       return userService.getUserById(id);
    }
    @PutMapping("/modify-user")
    public User updateUser(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id ){
        return userService.deleteUser(id);
    }
}
