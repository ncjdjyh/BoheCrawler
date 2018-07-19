package com.how2java.springboot.crawl;

import com.how2java.springboot.dao.FoodDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class GoCrawlTest {
    @Autowired
    private FoodDao dao;
    @Autowired
    private GoCrawl crawl;

    @Test
    public void doFetchPage() throws Exception {
        //String result = HttpClientUtil.downloadPage("http://www.boohee.com/food/search?keyword=西瓜");
        //RegexString(result, "<a[^>]*>([^<]*)</a>");
        crawl.doFetchPage();
        //util.setDao(dao);
        //util.saveFoodInDB(new ArrayList<>());
    }
}