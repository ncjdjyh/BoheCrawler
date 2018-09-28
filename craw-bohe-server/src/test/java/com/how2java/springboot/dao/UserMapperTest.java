package com.how2java.springboot.dao;

import com.how2java.springboot.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void add() {
    }

    @Test
    public void delete() {
        userMapper.getAll();
        System.out.println("sdf");
    }

    @Test
    public void get() {
    }

    @Test
    public void update() {
    }

    @Test
    public void list() {
    }
}