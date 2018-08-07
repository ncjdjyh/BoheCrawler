package com.how2java.springboot.service;

import com.how2java.springboot.annotation.RedisCache;
import com.how2java.springboot.dao.FoodDao;
import com.how2java.springboot.pojo.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/8/5 16:00
     * @Description:
     */

    @Autowired
    private FoodDao foodDao;

    @RedisCache
    public List<Food> findByNameLike( String name) {
        List<Food> foods = foodDao.findByNameLike("%" + name + "%");
        return foods;
    }

    public void save(List<Food> foods) {
        foodDao.save(foods);
    }
}
