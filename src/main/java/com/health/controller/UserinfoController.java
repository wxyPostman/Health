package com.health.controller;

import com.health.entity.ShoppingCar;
import com.health.entity.Userinfo;
import com.health.service.ShoppingCarService;
import com.health.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserinfoController {

    @Autowired
    private UserinfoService userinfoService;

    @Autowired
    private ShoppingCarService shoppingCarService;

    @RequestMapping("saveuserinfo")
    @ResponseBody
    public String save(Userinfo userinfo,@RequestParam("id") String id ,@RequestParam("num") String num){
        String[] pid = id.substring(1,id.length()-1).split(",");
        String[] nums = num.substring(1,num.length()-1).split(",");
        for (int i=0;i<pid.length;i++){
            userinfo.setPid(Integer.parseInt(pid[i]));
            userinfo.setNumber(Integer.parseInt(nums[i]));
            userinfoService.save(userinfo);
            ShoppingCar shoppingCar = new ShoppingCar();
            shoppingCar.setId(Integer.parseInt(pid[i]));
            shoppingCar.setProductState(1);
            shoppingCarService.update(shoppingCar);
        }
        return "200";
    }

}
