package com.health.controller;

import com.health.entity.Admin;
import com.health.service.AdminService;
import com.health.service.QuestionService;
import com.health.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yaohandong on  2019/6/25 10:11
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ReplyService replyService;


    @RequestMapping("admin_login")
    @ResponseBody
    public int findByaname(String aname, String password, HttpSession session) {
        Admin admin = adminService.findByaname(aname);
        if (admin != null) {
            if (admin.getPassword().equals(password)) {
                session.setAttribute("admin", admin);
                return 200;
            } else {
                return 400;
            }
        } else {
            return 500;
        }
    }

    @RequestMapping("check_admin")
    @ResponseBody
    public Admin session(HttpSession session, HttpServletRequest request) {
        session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        return admin;
    }

    @RequestMapping("admin_reg")
    @ResponseBody
    public int reg(Admin admin) {
        boolean isok = adminService.save(admin);
        if (isok) {
            return 200;
        } else {
            return 400;
        }
    }

    @RequestMapping("admin_exit")
    @ResponseBody
    public int exit(HttpSession session, HttpServletRequest request) throws IOException {
        session = request.getSession();
        session.removeAttribute("admin");
        session.invalidate(); //让session失效
        //session.setAttribute("register", null);
        //System.out.println(session);
        int msg = 400;
        return msg;
    }


    @RequestMapping("question_reply")
    @ResponseBody
    public int count() {
        int a = replyService.count();
        int i = questionService.count();
        return a + i;
    }

    @RequestMapping("time")
    @ResponseBody
    public String time() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        return format;
    }


    @RequestMapping("update")
    @ResponseBody
    public int update(String password, String password_new,HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        int msg=0;
        if(admin.getPassword().equals(password)){
            admin.setPassword(password_new);
            boolean isok = adminService.update(admin);
            if(isok){
               msg=200;
            }else{
                msg=400;
            }
        }else{
           msg=500;
        }
        return msg;
    }
}
