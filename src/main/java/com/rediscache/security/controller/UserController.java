package com.rediscache.security.controller;

import com.rediscache.security.entity.User;
import com.rediscache.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService service;

    // Read operation
    @GetMapping("/")
    public List<User> list() {
        log.info("user list....");

        return service.findAll();
    }

    @Cacheable(value = "users", key = "#id")
    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        try {
            Optional<User> optionalUser = service.findById(id);
            if(optionalUser.isPresent()) {
                return optionalUser.get();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    //@CachePut(value = "users", key = "#user.id")
    // Save operation
    @PostMapping("/")
    public User save(@Valid @RequestBody User user) {
        try {
            return service.save(user);
        } catch (Exception e) {
            return null;
        }
    }

    @CachePut(value = "users",  key = "#id")
    // Update operation
    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable("id") Long id) {
        try {
            Optional<User> userOptional = service.findById(id);
            if (userOptional.isPresent()) {

                User _user = userOptional.get();

                //Set values
                _user.setEmail(user.getEmail());
                _user.setRoles( user.getRoles());

                return service.save(_user);
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    // Delete operation
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            Optional<User> userOptional = service.findById(id);
            if(userOptional.isPresent()) {
                service.delete(id);
                return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
