package com.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.health.dao.QuestionDAO;
import com.health.entity.Question;
import com.health.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yaohandong on  2019/6/24 20:43
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDAO questionDAO;

    @Override
    public boolean save(Question question) {
        int i = questionDAO.insert(question);
        return i == 1 ? true : false;
    }

    @Override
    public boolean delete(Integer qid) {
        int i = questionDAO.deleteById(qid);
        return i == 1 ? true : false;
    }

    @Override
    public boolean update(Question question) {
        int i = questionDAO.updateById(question);
        return i == 1 ? true : false;
    }

    @Override
    public List<Question> findAll() {
        return questionDAO.selectList(null);
    }

    @Override
    public Question findByQid(Integer qid) {
        return questionDAO.selectById(qid);
    }

    @Override
    public List<Question> findByAdopt(Integer solve) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        if (solve == 1) {
            queryWrapper.ne("adopt", "0");
            return questionDAO.selectList(queryWrapper);
        }
        queryWrapper.eq("adopt", "0");
        return questionDAO.selectList(queryWrapper);
    }

    @Override
    public List<Question> findByType(String type) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        return questionDAO.selectList(queryWrapper);
    }

    @Override
    public List<Question> findByQtitle(String qtitle) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        for (int i = 0; i < qtitle.length(); i++) {
            queryWrapper.like("qtitle", qtitle.charAt(i));
        }
        return questionDAO.selectList(queryWrapper);
    }

    @Override
    public int count() {

        return questionDAO.selectCount(null);
    }
}
