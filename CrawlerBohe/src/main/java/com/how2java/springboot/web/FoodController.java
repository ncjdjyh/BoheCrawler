package com.how2java.springboot.web;

import com.how2java.springboot.thread.CrawlThread;
import com.how2java.springboot.exception.GlobalExceptionHandler;
import com.how2java.springboot.pojo.Food;
import com.how2java.springboot.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public Object listFood(@RequestParam(value = "name", required = true, defaultValue = "") String name)
            throws GlobalExceptionHandler {
        return foodService.enhanceSearchFood(name);
    }

    @RequestMapping(value = "/food/suggest_search", method = RequestMethod.GET)
    public List<Food> suggestSearch(@RequestParam(value = "name", defaultValue = "") String name) {
        return foodService.findByNameLike(name);
    }
}
