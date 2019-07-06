package com.health.service;

import com.health.entity.Articles;


import java.util.List;

/**
 * Created by yaohandong on  2019/6/24 15:42
 */
public interface ArticlesService {

    List<Articles> findAll();

    boolean save(Articles articles);

    boolean update(Articles articles);

    boolean delete(Integer tid);

    Articles findByTid(Integer tid);

    List<Articles> findByTitle(String title);

    List<Articles> findByType(String type);

    int Count();
}
