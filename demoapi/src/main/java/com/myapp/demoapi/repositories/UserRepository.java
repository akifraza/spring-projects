package com.myapp.demoapi.repositories;

import com.myapp.demoapi.models.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <User, String> {
    
    public User findByUsername(String username);
}
