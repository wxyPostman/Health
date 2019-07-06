package com.health.controller;

import com.health.entity.Comment;
import com.health.entity.User;
import com.health.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yaohandong on  2019/7/3 13:32
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("saveComment")
    @ResponseBody
    public int save(String content, Integer pid, HttpSession session, HttpServletRequest request) {
        Date date = new Date();
        SimpleDateFormat times = new SimpleDateFormat();
        String time = times.format(date);
        session = request.getSession();
        User user = (User) session.getAttribute("user");
        Comment comment = new Comment();
        comment.setCid(0);
        comment.setContent(content);
        comment.setTime(time);
        comment.setUname(user.getUname());
        comment.setUurl(user.getPsrc());
        comment.setPid(pid);
        boolean i = commentService.save(comment);
        return i == true ? 200 : 400;
    }


    @RequestMapping("findByPid")
    @ResponseBody
    public List<Comment> findByPid(Integer pid) {
        System.out.println(pid);
        List<Comment> list = commentService.findByPid(pid);
        System.out.println(list);
        return list;
    }
}
