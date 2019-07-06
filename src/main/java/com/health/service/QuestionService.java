package com.health.service;

import com.health.entity.Question;

import java.util.List;

/**
 * Created by yaohandong on  2019/6/24 15:41
 */
public interface QuestionService {

    boolean save(Question question);

    boolean delete(Integer qid);

    boolean update(Question question);

    List<Question> findAll();

    Question findByQid(Integer qid);

    List<Question> findByAdopt(Integer solve);

    List<Question> findByType(String type);

    List<Question> findByQtitle(String title);

    int count();

}
