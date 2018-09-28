package com.jyh.bohe.foodService.util;

import com.jyh.bohe.foodService.service.FoodService;
import com.jyh.bohe.foodService.pojo.Food;
import com.jyh.bohe.foodService.thread.DownloadThread;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
//爬虫工具类
public class CrawlUtil {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/8/1 17:29
     * @Description: 爬虫工具类
     */

    @Autowired
    private FoodService foodService;

    public List<Food> findFoodInfoAndSaveInDB(String result) {
        List<Food> foods = new ArrayList<>();
        Set<String> foodImgs = new HashSet<>();
        Document doc = Jsoup.parse(result);
        Elements lis = doc.getElementsByClass("item clearfix");
        if (!lis.isEmpty()) {
            for (Element e : lis) {
                String subPath = findSubPath(e);
                String detailList = getDetailList(subPath);
                Document d = Jsoup.parse(detailList);
                Food food = prepareFood(d);
                foods.add(food);
                foodImgs.add(findImg(d));
            }
            //启一个线程去下载图片
            downloadPictures(foodImgs);
            saveFoodInDB(foods);
            return foods;
        } else {
            return null;
        }
    }

    private String findSubPath(Element element) {
        Elements textBox = element.getElementsByClass("text-box pull-left");
        Elements a = textBox.get(0).getElementsByTag("a");
        return a.attr("href");
    }

    private String getDetailList(String path) {
        String baseUrl = "http://www.boohee.com";
        return HttpClientUtil.downloadPage(baseUrl + path);
    }

    private void downloadPictures(Set<String> foodImgs) {
        DownloadThread dt = new DownloadThread(foodImgs);
        Thread t = new Thread(dt);
        t.start();
    }

    private Food prepareFood(Document doc) {
        Food food = new Food();
        food.setName(findName(doc));
        food.setHeat(findHeat(doc));
        food.setCategory(findCategory(doc));
        food.setEvaluation(findEvaluation(doc));
        String imgUrl = findImg(doc);
        food.setImg(imgUrl.substring(imgUrl.lastIndexOf("/") + 1));
        return food;
    }

    private String findCategory(Document doc) {
        String category = "";
        Element ul = doc.getElementsByClass("basic-infor ").get(0);
        category = regexString(ul.text(), "分类：([^\\s]*)");
        return category;
    }

    private String findHeat(Document doc) {
        String heat = "";
        Element ul = doc.getElementsByClass("basic-infor ").get(0);
        heat = regexString(ul.text(), "热量：([^（]*)");
        return heat;
    }

    private String findName(Document doc) {
        String name = "";
        Element content = doc.getElementsByClass("form-inline").get(0);
        name = content.text().substring(3);
        return name;
    }

    private  String findEvaluation(Document doc) {
        String evaluation = "";
        Element content = doc.getElementsByClass("content").get(1);
        Element p = content.getElementsByTag("p").get(0);
        evaluation = p.text().substring(3);
        return evaluation;
    }

    private static String findImg(Document doc) {
        Elements imgBox = doc.getElementsByClass("food-pic pull-left");
        Elements img = imgBox.get(0).getElementsByTag("img");
        return img.attr("src");
    }

    public void saveFoodInDB(List<Food> foods) {
        foodService.save(foods);
    }

    public String regexString(String targetStr, String patternStr) {
        // 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容
        // 相当于埋好了陷阱匹配的地方就会掉下去
        Pattern pattern = Pattern.compile(patternStr);
        // 定义一个matcher用来做匹配
        Matcher matcher = pattern.matcher(targetStr);
        // 如果找到了
        while (matcher.find()) {
            // 打印出结果
            return matcher.group(1);
        }
        return null;
    }
}
