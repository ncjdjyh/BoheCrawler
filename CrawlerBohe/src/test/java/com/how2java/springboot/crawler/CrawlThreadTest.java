package com.how2java.springboot.crawler;

import org.junit.Test;

public class CrawlThreadTest {

    @Test
    public void doFetchPage() throws Exception {
        CrawlThread ct = new CrawlThread();
        ct.setKeyword("西瓜");
        ct.doFetch();
    }
}