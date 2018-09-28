package com.jyh.bohe.foodService.crawler;


import java.util.List;

public interface CrawlInterface<T> {
    /* 将爬取的数据传递给相关控制器 */
    void resultsFromBohe(List<T> foods);
}
