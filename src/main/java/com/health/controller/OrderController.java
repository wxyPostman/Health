package com.health.controller;

import com.alipay.api.AlipayApiException;
import com.health.entity.AlipayBean;
import com.health.entity.ShoppingCar;
import com.health.entity.Userinfo;
import com.health.service.PayService;
import com.health.service.ShoppingCarService;
import com.health.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private UserinfoService userinfoService;

    @Autowired
    private ShoppingCarService shoppingCarService;

    @Autowired
    private PayService payService;

    @PostMapping(value = "alipay")
    public String alipay(Userinfo userinfo, @RequestParam("id") String id , @RequestParam("num") String num,@RequestParam("name") String subject,@RequestParam("amount") String totalAmount) throws AlipayApiException {
        System.out.println("进来了");
        Random random = new Random();
        String[] pid = id.substring(1,id.length()-1).split(",");
        String[] nums = num.substring(1,num.length()-1).split(",");
        String outTradeNo = "";
        int max = 100000000;
        int min = 10000000;
        outTradeNo  = String.valueOf(random.nextInt(max-min)+1000000);
        for (int i=0;i<pid.length;i++){
            outTradeNo =outTradeNo +pid[i];
            userinfo.setPid(Integer.parseInt(pid[i]));
            userinfo.setNumber(Integer.parseInt(nums[i]));
            userinfoService.save(userinfo);
            ShoppingCar shoppingCar = new ShoppingCar();
            shoppingCar.setId(Integer.parseInt(pid[i]));
            shoppingCar.setProductState(1);
            shoppingCarService.update(shoppingCar);
        }
        System.out.println(outTradeNo);
        System.out.println(subject);
        System.out.println(totalAmount);

        AlipayBean alipayBean = new AlipayBean();
        alipayBean.setOut_trade_no(outTradeNo);
        alipayBean.setSubject(subject);
        alipayBean.setTotal_amount(totalAmount);
        alipayBean.setBody(null);
        String s = payService.aliPay(alipayBean);
        System.out.println(s);
        return s;
    }
}