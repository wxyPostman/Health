package com.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.health.dao.ProductDAO;
import com.health.entity.Product;
import com.health.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<Product> findAll() {
        return productDAO.selectList(null);
    }

    @Override
    public boolean save(Product product) {
        int i = productDAO.insert(product);
        return i == 1 ? true : false;
    }

    @Override
    public boolean update(Product product) {
        int i = productDAO.updateById(product);
        return i == 1 ? true : false;
    }

    @Override
    public boolean delete(Integer pid) {
        int i = productDAO.deleteById(pid);
        return i == 1 ? true : false;
    }

    @Override
    public Product findById(Integer pid) {
        return productDAO.selectById(pid);
    }

    @Override
    public List<Product> findByPname(String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("pname", "%" + name + "%");
        return productDAO.selectList(queryWrapper);
    }

    @Override
    public int count() {
        return productDAO.selectCount(null);
    }
}
