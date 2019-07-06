package com.health.service.impl;

import com.health.dao.VideoDAO;
import com.health.entity.Video;
import com.health.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yaohandong on  2019/6/24 20:48
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDAO videoDAO;

    @Override
    public List<Video> findAll() {
        return videoDAO.selectList(null);
    }

    @Override
    public boolean save(Video video) {
        int i = videoDAO.insert(video);
        return i == 1 ? true : false;
    }

    @Override
    public boolean update(Video video) {
        int i = videoDAO.updateById(video);
        return i == 1 ? true : false;
    }

    @Override
    public boolean delete(Integer vid) {
        int i = videoDAO.deleteById(vid);
        return i == 1 ? true : false;
    }
}
