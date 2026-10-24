package com.telusko.RedisDemoProject.DAO;

import com.telusko.RedisDemoProject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDao
{
    @Autowired
    private RedisTemplate<String , Object> redisTemplate;  //need redisTemplate to communicate with redis

    private static final String KEY = "USER";

    //SAVE USER

    public User save(User user)
    {
        redisTemplate.opsForHash().put(KEY , user.getUserId() , user);
        return user;
    }

    public User get(String userId)
    {
        return (User) redisTemplate.opsForHash().get(KEY , userId);
    }


    //FIND ALL

    public Map<Object , Object> findAll()
    {
        return redisTemplate.opsForHash().entries(KEY);
    }


    //DELETE
    public String delete(String userId)
    {
        redisTemplate.opsForHash().delete(KEY , userId);
        return "User removed !!";
    }

    //UPDATE
    public User update(User user)
    {
        User existingUser = (User)redisTemplate.opsForHash().get(KEY , user.getUserId());
        if(existingUser != null)
        {
            if (user.getName() != null) existingUser.setName(user.getName());
            if (user.getPhone() != null) existingUser.setPhone(user.getPhone());
            if (user.getEmail() != null) existingUser.setEmail(user.getEmail());

            redisTemplate.opsForHash().put(KEY , existingUser.getUserId() , existingUser);
            return existingUser;
        }

        //Otherwise save the new entry
        redisTemplate.opsForHash().put(KEY, user.getUserId(), user);
        return user;


    }
}
