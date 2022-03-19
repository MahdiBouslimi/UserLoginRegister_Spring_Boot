package com.example.user_login_register.Service;

import com.example.user_login_register.Exception.ResourceNotFoundException;
import com.example.user_login_register.Model.User;
import com.example.user_login_register.Repository.UserRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class UserService {
    public final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User registerUser(User user) throws Exception {
        String templEmail = user.getEmail();
        if(templEmail !=null && !"".equals(templEmail)) {
            User userObj = userRepo.findByEmail(templEmail);
            if(userObj != null) {
                throw new Exception("User with " + templEmail + " is already exist");
            }
        }
        return userRepo.save(user);
    }
    /*public User loginUser( User user) throws Exception {
        String tempEmail = user.getEmail();
        String tempPassword = user.getPassword();
        User userObj = null;
        if(tempEmail !=null && tempPassword !=null) {
            userObj = userRepo.findByEmailAndPassword(tempEmail, tempPassword);
        }
        if (userObj == null) {
            throw new Exception("Error to login");
        }
        return userObj;
    }*/
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
    public User getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        return user;
    }
    public String deleteUser(Long id){
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        userRepo.delete(user);
        return "User Deleted !";

    }
    public User updateUserInfo(User user ) {
        return userRepo.save(user);
    }


}
