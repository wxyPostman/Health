package com.health.controller;

import com.health.entity.Product;
import com.health.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yaohandong on  2019/6/26 15:37
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping("pall")
    @ResponseBody
    public List<Product> findAll() {
        return productService.findAll();
    }

    @RequestMapping("count_product")
    @ResponseBody
    private int count(){
        return productService.count();
    }

    @RequestMapping("findProductById")
    @ResponseBody
    public Product findById(@RequestParam("id") Integer pid) {
        return productService.findById(pid);
    }

    @RequestMapping("findByPname")
    @ResponseBody
    public List<Product> findByPname(@RequestParam("pname") String pname) {
        return productService.findByPname(pname);
    }



    @RequestMapping("p_delete")
    @ResponseBody
    public int p_delete(Integer pid) {
        boolean isok = productService.delete(pid);
        if (isok) {
            return 200;
        } else {
            return 400;
        }
    }

    @RequestMapping("p_save")
    @ResponseBody
    public String  save(String pname, String introduction, Double price, String sort, Integer volume, String purl) {

        Product product = new Product();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        String format = dateFormat.format(date);
        product.setPid(0);
        product.setPname(pname);
        product.setIntroduction(introduction);
        product.setRtime(format);
        product.setPrice(price);
        product.setSort(sort);
        product.setVolume(volume);
        product.setPurl(purl);
       boolean isok = productService.save(product);
        if (isok) {
            return "上传成功";
      } else {
            return "上传失败";
       }
    }



}
