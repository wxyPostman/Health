package com.health.service;

import com.health.entity.Comment;

import java.util.List;

/**
 * Created by yaohandong on  2019/6/24 15:41
 */
public interface CommentService {

    public List<Comment> findAll();

    public boolean save(Comment comment);

    public boolean update(Comment comment);

    public boolean delete(Integer cid);

    List<Comment> findByPid(Integer pid);
}
