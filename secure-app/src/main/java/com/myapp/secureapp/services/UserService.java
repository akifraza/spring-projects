package com.myapp.secureapp.services;

import com.myapp.secureapp.models.User;
import com.myapp.secureapp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createuser (User user) {
        User user1 = new User();
        user1.setFirstname(user.getFirstname());
        user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());
        user1.setLastname(user.getLastname());
        user1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user1.setRole(user.getRole());
        userRepository.save(user1);
        return user1;
    }

}
