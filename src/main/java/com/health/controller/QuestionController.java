package com.health.controller;

import com.health.entity.Question;
import com.health.entity.User;
import com.health.others.QuestionComparator;
import com.health.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("find_questions")
    @ResponseBody
    public List<Question> findAll(HttpSession session) {

        List<Question> list = questionService.findAll();

        Comparator comparator = new QuestionComparator();
        Collections.sort(list, comparator);

        session.setAttribute("qlist", list);

        return list;
    }

    @RequestMapping("add_question")
    @ResponseBody
    public Integer AddQuestion(Question question, HttpSession session) {
        User user = (User) session.getAttribute("user");

        question.setQid(1);

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        question.setTime(sf.format(new Date()));
        question.setUname(user.getUname());
        //0表示未采纳，1表示采纳
        question.setAdopt(0);

        question.setPsrc(user.getPsrc());

        boolean b = questionService.save(question);
        if (b) {
            return 200;
        }
        return 400;
    }

    @RequestMapping("detail_question")
    @ResponseBody
    public Question findQuestionByQid(Integer qid) {

        return questionService.findByQid(qid);

    }

    @RequestMapping("solved_questions")
    @ResponseBody
    public List<Question> findByAdopt(String flag, HttpSession session) {

        List<Question> list = new ArrayList<>();

        if (flag.equals("200")) {
            list = questionService.findByAdopt(1);
        } else {
            list = questionService.findByAdopt(0);
        }

        Comparator comparator = new QuestionComparator();
        Collections.sort(list, comparator);

        session.setAttribute("qlist", list);

        return list;
    }

    @RequestMapping("type_questions")
    @ResponseBody
    public List<Question> findByType(String type, HttpSession session) {

        List<Question> list = questionService.findByType(type);

        Comparator comparator = new QuestionComparator();
        Collections.sort(list, comparator);

        session.setAttribute("qlist", list);

        return list;
    }

    @RequestMapping("find_title")
    @ResponseBody
    public List<Question> findByTitle(String qtitle, HttpSession session) {

        List<Question> list = questionService.findByQtitle(qtitle);

        Comparator comparator = new QuestionComparator();
        Collections.sort(list, comparator);

        session.setAttribute("qlist", list);

        return list;
    }

    @RequestMapping("get_questions")
    @ResponseBody
    public List<Question> getQuestion(HttpSession session) {

        List<Question> list = (List<Question>) session.getAttribute("qlist");
        return list;

    }

    @RequestMapping("isasker")
    @ResponseBody
    public int isasker(HttpServletRequest request, Integer qid) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Question question = questionService.findByQid(qid);
            if (question.getAdopt() == 0 && question.getUname().equals(user.getUname())) {
                return 200;
            } else {
                return 400;
            }
        }

        return 400;
    }

    @RequestMapping("adopt_reply")
    @ResponseBody
    public int adoptReply(HttpServletRequest request, Integer qid, Integer rid) {

        Question question = questionService.findByQid(qid);
        question.setAdopt(rid);
        boolean b = questionService.update(question);
        if (b) {
            return 200;
        }

        return 400;
    }
    @RequestMapping("delete")
    @ResponseBody
    public int delete(Integer id){
        boolean isok = questionService.delete(id);
        if (isok){
            return 200;
        }else{
            return 400;
        }

    }

}