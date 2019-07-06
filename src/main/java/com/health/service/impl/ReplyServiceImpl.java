package com.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.health.dao.ReplyDAO;
import com.health.entity.Reply;
import com.health.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yaohandong on  2019/6/24 20:46
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDAO replyDAO;

    @Override
    public List<Reply> findAll() {
        return replyDAO.selectList(null);
    }

    @Override
    public boolean save(Reply reply) {
        int i = replyDAO.insert(reply);
        return i == 1 ? true : false;
    }

    @Override
    public boolean update(Reply reply) {
        int i = replyDAO.updateById(reply);
        return i == 1 ? true : false;
    }

    @Override
    public boolean delete(Integer rid) {
        int i = replyDAO.deleteById(rid);
        return i == 1 ? true : false;
    }

    @Override
    public int count() {
        return replyDAO.selectCount(null);
    }

    @Override
    public Reply findByRid(Integer rid) {
        return replyDAO.selectById(rid);
    }

    @Override
    public List<Reply> findByQid(Integer qid) {
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("qid", qid);
        return replyDAO.selectList(queryWrapper);
    }
}
