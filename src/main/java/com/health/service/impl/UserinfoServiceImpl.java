package com.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.health.dao.UserinfoDAO;
import com.health.entity.Userinfo;
import com.health.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserinfoServiceImpl implements UserinfoService {

    @Autowired
    private UserinfoDAO userinfoDAO;

    @Override
    public int save(Userinfo userinfo) {
        return userinfoDAO.insert(userinfo);
    }

    @Override
    public List<Userinfo> findByPid(Integer pid) {
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid",pid);
        return userinfoDAO.selectList(queryWrapper);
    }

    @Override
    public List<Userinfo> findAll() {
        return userinfoDAO.selectList(null);
    }
}
