package com.how2java.springboot.util;

import com.google.gson.Gson;
import com.how2java.springboot.pojo.Food;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {
    @Autowired
    RedisUtil redisUtil;
    private Gson gson = new Gson();

    @Test
    public void test() {
        Food food1 = new Food();
        food1.setName("111");
        Food food2 = new Food();
        food1.setName("222");
        List<Food> list = new ArrayList<>();
        list.add(food1);
        list.add(food2);
    }
}