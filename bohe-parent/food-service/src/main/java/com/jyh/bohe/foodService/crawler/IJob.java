package com.jyh.bohe.foodService.crawler;

import java.io.IOException;

interface IJob extends Runnable {

    /**
     * 在job执行之前回调的方法
     */
    void beforeRun();


    /**
     * 在job执行完毕之后回调的方法
     */
    void afterRun() throws IOException;
}
