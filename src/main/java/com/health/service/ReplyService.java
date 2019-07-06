package com.health.service;

import com.health.entity.Reply;

import java.util.List;

/**
 * Created by yaohandong on  2019/6/24 15:42
 */
public interface ReplyService {

    public List<Reply> findAll();

    public boolean save(Reply reply);

    public boolean update(Reply reply);

    public boolean delete(Integer rid);

    int count();

    Reply findByRid(Integer rid);
    List<Reply> findByQid(Integer qid);
}
