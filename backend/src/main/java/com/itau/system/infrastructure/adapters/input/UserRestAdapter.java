package com.itau.system.infrastructure.adapters.input;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.system.application.ports.input.UserController;
import com.itau.system.domain.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "kafka:4200"}, maxAge = 3600)
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserRestAdapter {
    private final UserController userController;
    private final ModelMapper mapper;

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
    	log.info("new api request to get user by ID: {}", id);
        User user = userController.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAll(){
    	log.info("new api request to get all users");
        List<User> allUsers = userController.getAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> createUser(@RequestBody User userToCreate) throws JsonProcessingException{
    	log.info("new api request to create a new user");
        User user = mapper.map(userToCreate, User.class);
        user = userController.create(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping(value = "/users")
    public ResponseEntity<User> updateUser(@RequestBody User userToUpdate){
    	log.info("new api request to update user by ID: {}", userToUpdate.getId());
        User user = mapper.map(userToUpdate, User.class);
        user = userController.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
    	log.info("new api request to delete user by ID: {}", id);
        userController.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
