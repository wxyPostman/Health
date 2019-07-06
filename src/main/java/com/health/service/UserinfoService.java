package com.health.service;

import com.health.entity.Userinfo;

import java.util.List;

public interface UserinfoService {

    int save(Userinfo userinfo);

    List<Userinfo> findByPid(Integer pid);

    List<Userinfo> findAll();
}
