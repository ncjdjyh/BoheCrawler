package com.how2java.springboot.web;

import com.google.gson.Gson;
import com.how2java.springboot.crawl.GoCrawl;
import com.how2java.springboot.dao.FoodDao;
import com.how2java.springboot.exception.GlobalExceptionHandler;
import com.how2java.springboot.pojo.Food;
import com.how2java.springboot.util.ServerManager;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("")
public class FoodController {
    @Autowired
    private FoodDao foodDao;
    @Autowired
    private GoCrawl crawl;
    List<Food> foods = new ArrayList<>();

    @RequestMapping(value = "/food", method = RequestMethod.GET)
    public Object listFood(@RequestParam(value = "name", required = true, defaultValue = "") String name, HttpServletResponse response) throws IOException, GlobalExceptionHandler {
        foods = foodDao.findByNameLike("%" + name + "%");
        //非空说明数据库中有数据，直接中数据库中取，否则就去爬
        if (name.isEmpty()) throw  new GlobalExceptionHandler();
        if (!foods.isEmpty()) {
            return foods;
        } else {
            crawlFromBohe(name);
            //resultsFromBohe(foods);
            //listBoheFood();
            return "库中没有你要的数据,开始爬";
        }
    }

    private void crawlFromBohe(String name) {
        crawl.crawlInit(this, name);
        Thread cT = new Thread(crawl);
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
