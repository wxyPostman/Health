package com.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.health.dao.ShoppingCarDAO;
import com.health.entity.ShoppingCar;
import com.health.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wenxinyan on 2019/07/01 8:51
 */
@Service
public class ShoppingCarServiceImpl implements ShoppingCarService {
    @Autowired
    private ShoppingCarDAO shoppingCarDAO;


    @Override
    public List<ShoppingCar> findAll() {
        return shoppingCarDAO.selectList(null);
    }

    @Override
    public List<ShoppingCar> findByUser(Integer userid) {
        return getShoppingCars(userid, "0");
    }

    @Override
    public boolean save(ShoppingCar shoppingCar) {
        int i = shoppingCarDAO.insert(shoppingCar);
        return i == 1 ? true : false;
    }

    @Override
    public boolean delete(Integer id) {
        int i = shoppingCarDAO.deleteById(id);
        return i == 1 ? true : false;
    }

    @Override
    public List<ShoppingCar> findShoppingByUserId(Integer userid) {
        QueryWrapper<ShoppingCar> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userid);

        return shoppingCarDAO.selectList(queryWrapper);
    }

    @Override
    public boolean update(ShoppingCar shoppingCar) {
        int i = shoppingCarDAO.updateById(shoppingCar);
        return i == 1 ? true : false;
    }


    private List<ShoppingCar> getShoppingCars(Integer userid, String s) {
        QueryWrapper<ShoppingCar> queryWrapper = new QueryWrapper<>();
        HashMap<String,Object> param = new HashMap<>();
        param.put("user_id",userid);
        param.put("product_state", s);
        queryWrapper.allEq(param);
        List<ShoppingCar> list = shoppingCarDAO.selectList(queryWrapper);
        return list;
    }


}
