package com.how2java.springboot.dao;

import com.how2java.springboot.pojo.Food;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodDaoTest {
    @Autowired
    private FoodDao dao;


    @Test
    public void get() {
        Food food = dao.findOne(1);
        System.out.println(food.getName());
    }

    @Test
    public void getNameLike() {
        List<Food> foods = dao.findByNameLike("%土%");
        System.out.println(foods.get(0).getName());
        //System.out.println(foods);
        //dao.save()
    }
}