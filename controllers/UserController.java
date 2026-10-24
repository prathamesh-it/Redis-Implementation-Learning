package com.telusko.RedisDemoProject.controllers;

import com.telusko.RedisDemoProject.DAO.UserDao;
import com.telusko.RedisDemoProject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserDao userDao;

    @PostMapping
    public User createUser(@RequestBody User user)
    {
        user.setUserId(UUID.randomUUID().toString());
        return userDao.save(user);
    }

    //Get a single user
    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") String userId)
    {
        return userDao.get(userId);
    }

    //FIND ALL
    @GetMapping
    public List<User> getUsers()
    {
        Map<Object , Object> all = userDao.findAll();
        Collection<Object> values = all.values();
        List<User> collect = values.stream().map(value -> (User)value)
                .collect(Collectors.toList());

        return collect;

    }

    //DELETE
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String userId)
    {
        return userDao.delete(userId);
    }

    //UPDATE
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable("userId") String userId,
                           @RequestBody User user)
    {
        user.setUserId(userId);
        return userDao.update(user);
    }
}
