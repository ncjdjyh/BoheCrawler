package com.jyh.bohe.foodService.service;

import com.google.gson.Gson;
import com.jyh.bohe.foodService.crawler.CrawlInterface;
import com.jyh.bohe.foodService.thread.CrawlThread;
import com.jyh.bohe.foodService.dao.FoodDao;
import com.jyh.bohe.foodService.exception.GlobalExceptionHandler;
import com.jyh.bohe.foodService.pojo.Food;
import com.jyh.bohe.foodService.util.ServerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService implements CrawlInterface<Food> {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/8/5 16:00
     * @Description:
     */

    @Autowired
    private FoodDao foodDao;
    @Autowired
    private CrawlThread crawler;

    public List<Food> findByNameLike( String name) {
        return foodDao.findByNameLike("%" + name + "%");
    }

    public void save(List<Food> foods) {
        foodDao.save(foods);
    }


    public List<Food> enhanceSearchFood(String name) throws GlobalExceptionHandler {
        if (name.isEmpty())
            throw  new GlobalExceptionHandler();
        //先去redis中找,找不到去数据库中找,找不到去薄荷中爬,再没有返回error
        List<Food> foods = findByNameLike(name);
        if (!foods.isEmpty()) {
            return foods;
        } else {
            crawlFromBohe(name);
            return null;
        }
    }

    private void crawlFromBohe(String name) {
        crawler.crawlInit(this, name);
        Thread cT = new Thread(crawler);
        cT.start();
    }

    //接受从薄荷爬取的数据，将得到的数据通过websocket发送给客户端
    @Override
    public void resultsFromBohe(List<Food> info) {
        //等200ms再发回去
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //没找到
        if (info == null) {
            ServerManager.broadCast("error");
        } else {
            ServerManager.broadCast(new Gson().toJson(info));
        }
    }
}
