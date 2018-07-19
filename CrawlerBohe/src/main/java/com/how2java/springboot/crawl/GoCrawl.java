package com.how2java.springboot.crawl;

import com.how2java.springboot.pojo.Food;
import com.how2java.springboot.util.CrawlUtil;
import com.how2java.springboot.util.HttpClientUtil;
import com.how2java.springboot.web.FoodController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GoCrawl extends AbstractJob  {
    //存放发起爬取请求的控制器，得到内容了返回给它
    private FoodController controller;
    private List<Food> foods;
    @Autowired
    private CrawlUtil crawlUtil;
    private String keyword;
    public GoCrawl() {
    }

    public void crawlInit(FoodController controller, String keyword) {
        this.keyword = keyword;
        this.controller = controller;
    }

    @Override
    public void doFetchPage() throws Exception {
        String result =  HttpClientUtil.downloadPage("http://www.boohee.com/food/search?keyword="+ keyword +"");
        foods = crawlUtil.findFoodInfoAndSaveInDB(result);
    }

     public void RegexString(String targetStr, String patternStr) {
        // 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容
        // 相当于埋好了陷阱匹配的地方就会掉下去
        Pattern pattern = Pattern.compile(patternStr);
        // 定义一个matcher用来做匹配
        Matcher matcher = pattern.matcher(targetStr);
        // 如果找到了
        while (matcher.find()) {
            // 打印出结果
            System.out.println(matcher.group(1));
        }
    }

    @Override
    public void afterRun(){
        sendFoodListToController();
    }

    private void sendFoodListToController()  {
        controller.resultsFromBohe(foods);
    }
}
