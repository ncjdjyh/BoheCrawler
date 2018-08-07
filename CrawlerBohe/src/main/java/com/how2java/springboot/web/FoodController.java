package com.how2java.springboot.web;

import com.google.gson.Gson;
import com.how2java.springboot.crawl.CrawlThread;
import com.how2java.springboot.dao.FoodDao;
import com.how2java.springboot.exception.GlobalExceptionHandler;
import com.how2java.springboot.pojo.Food;
import com.how2java.springboot.service.FoodService;
import com.how2java.springboot.util.RedisUtil;
import com.how2java.springboot.util.ServerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private CrawlThread crawler;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/food", method = RequestMethod.GET)
    public Object listFood(@RequestParam(value = "name", required = true, defaultValue = "") String name, HttpServletResponse response) throws IOException, GlobalExceptionHandler {
        List<Food> foods = foodService.findByNameLike(name);
        if (name.isEmpty())
            throw  new GlobalExceptionHandler();
        //先去redis中找,找不到去数据库中找,找不到去薄荷中爬,再没有返回error
        if (!foods.isEmpty()) {
            return foods;
        } else {
            crawlFromBohe(name);
            //resultsFromBohe(foods);
            //listBoheFood();
            return "error";
        }
    }

    private void crawlFromBohe(String name) {
        crawler.crawlInit(this, name);
        Thread cT = new Thread(crawler);
        cT.start();
    }

    //接受从薄荷爬取的数据，将得到的数据通过websocket发送给客户端
    public void resultsFromBohe(List<Food> info)  {
        //等一秒再发回去
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (info == null) {
            ServerManager.broadCast("hello");
        } else {
            ServerManager.broadCast(new Gson().toJson(info));
        }
    }
}
