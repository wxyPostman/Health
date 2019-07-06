package com.health.service;

import com.health.entity.Video;

import java.util.List;

/**
 * Created by yaohandong on  2019/6/24 14:44
 */
public interface VideoService {

    public List<Video> findAll();

    public boolean save(Video video);

    public boolean update(Video video);

    public boolean delete(Integer vid);
}
