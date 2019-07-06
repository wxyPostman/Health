package com.health.controller;

import com.google.gson.Gson;
import com.health.entity.User;
import com.health.service.UserService;
import com.health.verification.Verification;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;


/**
 * Created by yaohandong on  2019/6/24 18:10
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("all_user")
    @ResponseBody
    public List<User> findAll() {
        List<User> list = userService.findAll();
        return list;
    }

    //注册
    @RequestMapping("user_reg")
    @ResponseBody
    public int save(User user) {
        user.setPsrc("http://ptolozduu.bkt.clouddn.com/images/arts/lazy.jpg");

        boolean isok = userService.save(user);

        int msg = 0;
        if (isok) {
            msg = 200;
        } else {
            msg = 400;
        }
        return msg;

    }

    //登录
    @RequestMapping("user_login")
    @ResponseBody
    public int findByUname(String uname, String password, HttpSession session) {
        User user = userService.findByUname(uname);
        int msg = 0;
        if (user != null) {
            if (user.getPassword().equals(password)) {
                user.setPassword("");
                session.setAttribute("user", user);
                msg = 200;
            } else {
                msg = 400;
            }
        } else {
            msg = 500;
        }

        return msg;


    }

    //校验登录
    @RequestMapping("check_login")
    @ResponseBody
    public Object session(HttpSession session, HttpServletRequest request) {
        session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user!=null){
            return user;
        }else{
            return 400;
        }



    }

    //校验用户名
    @RequestMapping("check_name")
    @ResponseBody
    public int countByUname(String uname) {
        int msg = 0;
        boolean isok = userService.CountByUname(uname);

        if (isok) {
            msg = 200;
        } else {
            msg = 400;

        }
        System.out.println(msg);
        return msg;
    }

    //校验电话号码
    @RequestMapping("check_phone")
    @ResponseBody
    public int countByPhone(String phone) {
        int msg = 0;
        boolean isok = userService.CountByPhone(phone);
        if (isok) {
            msg = 200;
        } else {
            msg = 400;
        }
        return msg;
    }

    //验证码
    @RequestMapping("captcha")
    @ResponseBody
    public int captcha(String phone) {
        String cap = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int t = random.nextInt(10);
            cap += String.valueOf(t);
        }
        Verification.sendOne("注册", cap, phone);
        int capt = Integer.parseInt(cap);
        return capt;
    }

    //退出
    @RequestMapping("login_exit")
    @ResponseBody
    public int exit(HttpSession session, HttpServletRequest request) throws IOException {
        session = request.getSession();
        session.removeAttribute("user");
        session.invalidate();
        //session.setAttribute("register", null);
        //System.out.println(session);
        int msg = 400;
        return msg;
    }

    //更新用户信息
    @RequestMapping("userupdate")
    @ResponseBody
    public int Update(User user,HttpSession session){
        user.setUid(((User)session.getAttribute("user")).getUid());
        session.setAttribute("user",user);
        boolean b = userService.update(user);
       return b?200:400;
    }
    //更新用户密码
    @RequestMapping("updatepassword")
    @ResponseBody
    public int UpdatePassWord(User user,HttpSession session){
        user.setUid(((User)session.getAttribute("user")).getUid());
        boolean b = userService.update(user);
        return b?200:400;
    }
    //查询密码是否正确
    @RequestMapping("checkpassword")
    @ResponseBody
    public int checkPassword(String uname, String password, HttpSession session) {
        User user = userService.findByUname(uname);
        int msg = 0;
        if (user != null) {
            if (user.getPassword().equals(password)) {
                msg = 200;
            } else {
                msg = 400;
            }
        } else {
            msg = 500;
        }
        return msg;

    }
    //头像上传
    @RequestMapping("uploadhead")
    @ResponseBody
    public String UpLoad(MultipartFile file,HttpSession session)throws Exception{
        String name = file.getOriginalFilename();
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "ASef2aoRXqFgI-kgEs-pAMGSZKrKgZ1hjyV6eJ9N";
        String secretKey = "KsvTcozru56XBS1Eyg3_QDe3lZrQHKLClXlLB2k4";
        String bucket = "health";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        long l = System.currentTimeMillis();
        name = name + l;
        String key = "head/"+name;
        String psrc = "http://ptolozduu.bkt.clouddn.com/"+key;
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(file.getInputStream(),key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);


                User user = new User();
                int id =((User)session.getAttribute("user")).getUid();
                user.setUid(id);
                user.setPsrc(psrc);
                System.out.println(user);
                userService.update(user);
                user = userService.findById(id);
                session.setAttribute("user",user);

            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
                psrc = "400";
                return psrc;
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
            psrc = "400";
            return psrc;
        }
        return psrc;
    }

    @RequestMapping("count_uid")
    @ResponseBody
    public int countByuid(){
        return  userService.CountByUid();
    }
    @RequestMapping("find_username")
    @ResponseBody
    public List<User> findByUserName(String uname){
        List<User> list = userService.findByUserName(uname);
        return list;
    }

}
