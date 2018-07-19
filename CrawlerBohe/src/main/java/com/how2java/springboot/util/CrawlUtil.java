package com.how2java.springboot.util;

import com.how2java.springboot.dao.FoodDao;
import com.how2java.springboot.pojo.Food;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CrawlUtil {
    @Autowired
    private FoodDao dao;

    public CrawlUtil() {
    }

    public List<Food> findFoodInfoAndSaveInDB(String result) {
        List<Food> foods = new ArrayList<>();
        Document doc = Jsoup.parse(result);
        Elements lis = doc.getElementsByClass("item clearfix");
        if (!lis.isEmpty()) {
            for (Element e : lis) {
                String heat = findHeat(e);
                String name = findName(e);
                String description = findDescription(e);
                String img = findImg(e);
                Food food = new Food(name, description, img, heat);
                foods.add(food);
            }
            saveFoodInDB(foods);
            return foods;
        } else {
            return null;
        }
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
        Elements title = a.get(0).getElementsByAttribute("title");
        name = title.get(0).text();
        return name;
    }

    private  String findDescription(Element element) {
        return "";
    }

    private static String findImg(Element element) {
        Elements imgBox = element.getElementsByClass("img-box pull-left");
        Elements img = imgBox.get(0).getElementsByTag("img");
        Elements src = img.get(0).getElementsByAttribute("src");
        return src.text();
    }

    public void saveFoodInDB(List<Food> foods) {
        dao.save(foods);
    }
}