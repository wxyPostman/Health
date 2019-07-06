package com.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.health.dao.CommentDAO;
import com.health.entity.Comment;
import com.health.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yaohandong on  2019/6/24 20:37
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public List<Comment> findAll() {
        return commentDAO.selectList(null);
    }

    @Override
    public boolean save(Comment comment) {
        int i = commentDAO.insert(comment);
        return i == 1 ? true : false;
    }

    @Override
    public boolean update(Comment comment) {
        int i = commentDAO.updateById(comment);
        return i == 1 ? true : false;
    }

    @Override
    public boolean delete(Integer cid) {
        int i = commentDAO.deleteById(cid);
        return i == 1 ? true : false;
    }

    @Override
    public List<Comment> findByPid(Integer pid) {
        QueryWrapper<Comment> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("pid",pid);
        return commentDAO.selectList(queryWrapper);
    }
}
