package com.jyh.bohe.foodService.thread;

import com.jyh.bohe.foodService.crawler.AbstractJob;
import com.jyh.bohe.foodService.crawler.CrawlInterface;
import com.jyh.bohe.foodService.util.CrawlUtil;
import com.jyh.bohe.foodService.util.HttpClientUtil;
import com.jyh.bohe.foodService.pojo.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CrawlThread extends AbstractJob {
    //存放爬取控制器，得到内容了返回给它
    private CrawlInterface controller;
    private List<Food> foods;
    @Autowired
    private CrawlUtil crawlUtil;
    private String keyword;

    public void crawlInit(CrawlInterface controller, String keyword) {
        this.keyword = keyword;
        this.controller = controller;
    }

    @Override
    public void doFetch() {
        String result =  HttpClientUtil.downloadPage("http://www.boohee.com/food/search?keyword="+ keyword +"");
        foods = crawlUtil.findFoodInfoAndSaveInDB(result);
    }

    @Override
    public void afterRun() {
        sendFoodListToController();
    }

    private void sendFoodListToController() {
        controller.resultsFromBohe(foods);
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
