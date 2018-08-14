package com.how2java.springboot.crawler;

import com.how2java.springboot.thread.CrawlThread;
import org.junit.Test;

public class CrawlThreadTest {

    @Test
    public void doFetchPage() throws Exception {
        CrawlThread ct = new CrawlThread();
        ct.setKeyword("西瓜");
        ct.doFetch();
    }
}