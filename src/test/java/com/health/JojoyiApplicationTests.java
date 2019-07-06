package com.health;

import com.health.entity.Product;
import com.health.entity.ShoppingCar;
import com.health.entity.User;
import com.health.service.ProductService;
import com.health.service.ShoppingCarService;
import com.health.service.QuestionService;
import com.health.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JojoyiApplicationTests {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ProductService productService;

@Autowired
private UserService userService;
    @Autowired
    private ShoppingCarService shoppingCarService;

    @Test
    public void contextLoads() {
        List<User> list = userService.findByUserName("z");
        list.forEach(System.out::println);
    }

    @Test
    public void findByName(){
        List<Product> list = productService.findByPname("天");
        for (Product product : list) {
            System.out.println(product);
        }
    }

    @Test
    public void count(){
        int i = questionService.count();
        System.out.println(i);
    }


    @Test
    public void findByUser(){
        List<ShoppingCar> user = shoppingCarService.findByUser(1);
        user.forEach(System.out::println);
    }

    @Test
    public void ShoppingFindAll(){
        List<ShoppingCar> list = shoppingCarService.findAll();
        list.forEach(System.out::println);
    }

    @Test
    public void DeleteByProductId(){
        boolean b = shoppingCarService.delete(13);
        System.out.println(b);
    }

}
