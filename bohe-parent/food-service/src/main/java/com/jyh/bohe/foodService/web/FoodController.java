package com.jyh.bohe.foodService.web;

import com.jyh.bohe.foodService.exception.GlobalExceptionHandler;
import com.jyh.bohe.foodService.service.FoodService;
import com.jyh.bohe.foodService.thread.CrawlThread;
import com.jyh.bohe.foodService.pojo.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
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
