package com.how2java.springboot.dao;

import com.how2java.springboot.pojo.Food;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FoodDao extends JpaRepository<Food, Integer> {
    //jpa实现的模糊查询
    List<Food> findByNameLike(String name);
}
