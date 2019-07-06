package com.health.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.health.dao.AdminDAO;
import com.health.entity.Admin;
import com.health.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yaohandong on  2019/6/24 20:08
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public List<Admin> findAll() {
        return adminDAO.selectList(null);
    }

    @Override
    public boolean save(Admin admin) {
        int i = adminDAO.insert(admin);
        return i == 1 ? true : false;
    }

    @Override
    public boolean update(Admin admin) {
        int i = adminDAO.updateById(admin);
        return i == 1 ? true : false;
    }

    @Override
    public boolean delete(Integer aid) {
        int i = adminDAO.deleteById(aid);
        return i == 1 ? true : false;
    }

    @Override
    public Admin findByaname(String aname) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("aname", aname);
        return adminDAO.selectOne(queryWrapper);
    }

    @Override
    public Admin findByPassword(String password) {
        QueryWrapper<Admin> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("password",password);
        return adminDAO.selectOne(queryWrapper);
    }
}
