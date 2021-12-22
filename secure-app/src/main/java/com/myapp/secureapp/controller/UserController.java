package com.myapp.secureapp.controller;

import com.myapp.secureapp.models.AuthenticationRequest;
import com.myapp.secureapp.models.AuthenticationResponse;
import com.myapp.secureapp.models.CustomUser;
import com.myapp.secureapp.models.User;
import com.myapp.secureapp.repositories.UserRepository;
import com.myapp.secureapp.services.UserService;
import com.myapp.secureapp.utils.Jwtutil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Jwtutil jwtutil;

    @PostMapping("/signin")
    public ResponseEntity<?> createUser (@RequestBody User user) {
        User user1 = new User();
        user1 = userRepository.findByUsername(user.getUsername());
        if (user1 == null) {
            userService.createuser(user);
            return new ResponseEntity<> (user, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<> ("User Already Exist.", HttpStatus.CONFLICT);
        }
         
    }

    @PostMapping ("/authenticate")
    public ResponseEntity<?> createAuthenticationToken (@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername() , authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = new CustomUser(userRepository.findByUsername(authenticationRequest.getUsername()));
        final String jwt = jwtutil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping ("/users")
    public String adminGet () {
        return "This is from Admin....";
    }


    
}
