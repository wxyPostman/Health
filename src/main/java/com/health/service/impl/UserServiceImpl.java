package com.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.health.dao.UserDAO;
import com.health.entity.User;
import com.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by yaohandong on  2019/6/24 18:20
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public List<User> findAll() {
        List<User> user = (List<User>) redisTemplate.opsForValue().get("user");
        if (user == null) {
            user = userDAO.selectList(null);
            redisTemplate.opsForValue().set("user", user);
        }
        return user;
    }

    @Override
    public boolean save(User user) {
        redisTemplate.opsForValue().set("user", null);
        int i = userDAO.insert(user);
        return i == 1 ? true : false;
    }

    @Override
    public boolean update(User user) {
        redisTemplate.opsForValue().set("user", null);
        int i = userDAO.updateById(user);
        return i == 1 ? true : false;
    }

    @Override
    public boolean delete(Integer uid) {
        redisTemplate.opsForValue().set("user", null);
        int i = userDAO.deleteById(uid);
        return i == 1 ? true : false;
    }

    @Override
    public boolean CountByUname(String uname) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("uname", uname);
        Integer count = userDAO.selectCount(wrapper);
        return count == 1 ? false : true;
    }

    @Override
    public User findByUname(String uname) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("uname", uname);
        return userDAO.selectOne(wrapper);
    }

    @Override
    public boolean CountByPhone(String phone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        Integer count = userDAO.selectCount(wrapper);
        return count == 1 ? false : true;
    }

    @Override
    public User findById(Integer uid) {
        return userDAO.selectById(uid);
    }

    @Override
    public int CountByUid() {
        return userDAO.selectCount(null);
    }

    @Override
    public List<User> findByUserName(String uname) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("uname", "%"+uname+"%");
        return userDAO.selectList(queryWrapper);
    }
}
