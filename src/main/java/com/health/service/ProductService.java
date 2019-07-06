package com.health.service;

import com.health.entity.Product;

import java.util.List;

/**
 * Created by yaohandong on  2019/6/24 15:41
 */
public interface ProductService {

    public List<Product> findAll();

    public boolean save(Product product);

    public boolean update(Product product);

    public boolean delete(Integer pid);

    public Product findById(Integer pid);

    public List<Product> findByPname(String name);

    int count();
}
