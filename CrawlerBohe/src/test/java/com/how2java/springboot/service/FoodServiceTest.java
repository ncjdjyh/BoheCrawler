package com.how2java.springboot.service;

import com.how2java.springboot.dao.FoodDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodServiceTest {

    @Autowired
    private FoodService service;
    @Autowired
    private FoodDao foodDao;

    @Test
    public void findByNameLike() {
        service.findByNameLike("苹果");
    }
}