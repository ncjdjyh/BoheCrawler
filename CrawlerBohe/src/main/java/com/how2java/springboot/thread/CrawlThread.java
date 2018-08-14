package com.how2java.springboot.thread;

import com.how2java.springboot.crawler.AbstractJob;
import com.how2java.springboot.crawler.CrawlInterface;
import com.how2java.springboot.pojo.Food;
import com.how2java.springboot.util.CrawlUtil;
import com.how2java.springboot.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CrawlThread extends AbstractJob {
    //存放发爬取控制器，得到内容了返回给它
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
