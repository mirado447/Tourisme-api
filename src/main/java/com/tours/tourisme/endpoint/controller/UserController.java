package com.tours.tourisme.endpoint.controller;

import com.tours.tourisme.model.BoundedPageSize;
import com.tours.tourisme.model.PageFromOne;
import com.tours.tourisme.repository.entity.User;
import com.tours.tourisme.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public List<User> getAllUser(@RequestParam int page,
                                 @RequestParam(value = "page_size") int pageSize){
        PageFromOne pageFromOne = new PageFromOne(page);
        BoundedPageSize boundedPageSize = new BoundedPageSize(pageSize);
        return userService.getAllUser(pageFromOne, boundedPageSize);
    }

    @GetMapping("/users/{uid}")
    public User getUserById(@PathVariable Long uid){
        return userService.getUserById(uid);
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping("/users/{uid}")
    public User crupdateUser(@PathVariable Long uid,
                             @RequestBody User user){
        return userService.crupdateUser(uid, user);
    }

    @DeleteMapping("/users/{uid}")
    public User deleteUserById(@PathVariable Long uid){
        return userService.deleteUserById(uid);
    }
}
