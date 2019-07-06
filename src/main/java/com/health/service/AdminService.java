package com.health.service;

import com.health.entity.Admin;

import java.util.List;

/**
 * Created by yaohandong on  2019/6/24 15:40
 */
public interface AdminService {
    public List<Admin> findAll();

    public boolean save(Admin admin);

    public boolean update(Admin admin);

    public boolean delete(Integer aid);

    public Admin findByaname(String aname);

    public Admin findByPassword(String password);
}
