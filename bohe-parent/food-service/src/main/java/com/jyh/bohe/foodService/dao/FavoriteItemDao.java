package com.jyh.bohe.foodService.dao;

import com.jyh.bohe.foodService.pojo.Food;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FavoriteItemDao {
    void insert(String uid, int fid);
    List<Food> findFoodByUserId(String uid);
}
