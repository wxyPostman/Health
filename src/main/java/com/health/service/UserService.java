package com.health.service;

import com.health.entity.User;

import java.util.List;

/**
 * Created by yaohandong on  2019/6/24 14:39
 */
public interface UserService {
    public List<User> findAll();

    public boolean save(User user);

    public boolean update(User user);

    public boolean delete(Integer uid);

    public boolean CountByUname(String uname);

    public User findByUname(String uname);

    public boolean CountByPhone(String phone);

    public User findById(Integer uid);

    public int CountByUid();

    public List<User> findByUserName(String uname);
}
