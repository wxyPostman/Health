package com.health.controller;

import com.health.entity.Articles;
import com.health.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticlesController {

    @Autowired
    private ArticlesService articlesService;


    @GetMapping("allarts")
    public List<Articles> all(){
        return articlesService.findAll();
    }

    @GetMapping("arts/{tid}")
    public Articles arts(@PathVariable("tid")Integer tid){
        return articlesService.findByTid(tid);
    }

    @GetMapping("atitle/{title}")
    public List<Articles> atitle(@PathVariable("title")String title){
        if (title.equals("null")||title == null){
            return articlesService.findAll();
        }
        return articlesService.findByTitle(title);
    }

    @GetMapping("Type/{type}")
    public List<Articles> ByType(@PathVariable("type")String type){
        return articlesService.findByType(type);
    }

    @GetMapping("count_aid")
    public int count(){
      return  articlesService.Count();
    }
}
