package com.how2java.springboot.util;

import com.how2java.springboot.dao.FoodDao;
import com.how2java.springboot.pojo.Food;
import com.how2java.springboot.service.FoodService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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
        List<String> foodImgs = new ArrayList<>();
        Document doc = Jsoup.parse(result);
        Elements lis = doc.getElementsByClass("item clearfix");
        if (!lis.isEmpty()) {
            for (Element e : lis) {
                String heat = findHeat(e);
                String name = findName(e);
                String description = findDescription(e);
                String img = findImg(e);
                //换一下名字
                String imgName = img.substring(img.lastIndexOf("/") + 1, img.length());
                Food food = new Food(name, description, imgName, heat);
                foods.add(food);
                foodImgs.add(img);
            }
            //启一个线程去下载图片
            downloadPictures(foodImgs);
            saveFoodInDB(foods);
            return foods;
        } else {
            return null;
        }
    }

    private void downloadPictures(List<String> foodImgs) {
        DownloadThread dt = new DownloadThread(foodImgs);
        Thread t = new Thread(dt);
        t.start();
    }

    private  String findHeat(Element element) {
        String heat = "";
        Elements p = element.getElementsByTag("p");
        heat = p.get(0).text();
        return heat;
    }

    private String findName(Element element) {
        String name = "";
        Elements textBox = element.getElementsByClass("text-box pull-left");
        Elements a = textBox.get(0).getElementsByTag("a");
        return a.attr("title");
    }

    private  String findDescription(Element element) {
        return "";
    }

    private static String findImg(Element element) {
        Elements imgBox = element.getElementsByClass("img-box pull-left");
        Elements img = imgBox.get(0).getElementsByTag("img");
        return img.attr("src");
    }

    public void saveFoodInDB(List<Food> foods) {
        foodService.save(foods);
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
}
