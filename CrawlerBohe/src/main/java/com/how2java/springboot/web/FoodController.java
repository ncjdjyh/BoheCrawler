package com.how2java.springboot.web;

import com.google.gson.Gson;
import com.how2java.springboot.crawl.CrawlThread;
import com.how2java.springboot.exception.GlobalExceptionHandler;
import com.how2java.springboot.pojo.Food;
import com.how2java.springboot.service.FoodService;
import com.how2java.springboot.util.ServerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private CrawlThread crawler;

    @RequestMapping(value = "/food", method = RequestMethod.GET)
    public Object listFood(@RequestParam(value = "name", required = true, defaultValue = "") String name, HttpServletResponse response) throws GlobalExceptionHandler {
        /*List<Food> foods = foodService.findByNameLike(name);
        if (name.isEmpty())
            throw  new GlobalExceptionHandler();
        //先去redis中找,找不到去数据库中找,找不到去薄荷中爬,再没有返回error
        if (!foods.isEmpty()) {
            return foods;
        } else {
            crawlFromBohe(name);
            return null;
        }*/

        return foodService.enhanceSearchFood(name);
    }
}
