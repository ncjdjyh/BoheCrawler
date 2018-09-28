package com.jyh.bohe.favoriteservice.controller;

import com.jyh.bohe.boheapi.pojo.Food;
import com.jyh.bohe.favoriteservice.service.FavoriteItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class FavoriteController {
    @Autowired
    private FavoriteItemService favoriteItemService;

    @GetMapping("/favoriteList/{username}")
    public List<Food> getFavoriteList(@PathVariable String username) {
//        User user = userMapper.getOneWithUsername(username);
//        return user.getFoodList();
        return favoriteItemService.findFoodListByUserId(username);
    }

    @RequestMapping(value = "/addFavorite", method = RequestMethod.POST)
    public void addFavorite(@RequestParam(value = "uid") String uid,
                            @RequestParam(value = "fid") int fid) {
        favoriteItemService.insert(uid, fid);
    }
}
