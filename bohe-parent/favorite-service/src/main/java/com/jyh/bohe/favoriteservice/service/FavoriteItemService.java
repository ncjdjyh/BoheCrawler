package com.jyh.bohe.favoriteservice.service;


import com.jyh.bohe.boheapi.pojo.Food;
import com.jyh.bohe.favoriteservice.dao.FavoriteItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteItemService {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/28 09:20
     * @Description:
     */
    @Autowired
    private FavoriteItemDao favoriteItemDao;

    public void insert(String uid, int fid) {
        //这里要做成 如果已经有数据就不插入了
        favoriteItemDao.insert(uid, fid);
    }

    public List<Food> findFoodListByUserId(String uid) {
        return favoriteItemDao.findFoodByUserId(uid);
    }
}
