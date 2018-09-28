package com.jyh.bohe.foodService.crawler;

/*用一个抽象类来实现IJJob 否者每个具体的实现类都得实现这一大堆的方法
*这样一来子类继承这个抽象类就可以只实现它所关心的抽象方法
* */

import java.io.IOException;

public abstract class AbstractJob implements IJob {

    public void beforeRun() {
    }

    public void afterRun() throws IOException {
    }


    @Override
    public void run() {
        this.beforeRun();

        try {
            this.doFetch();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.afterRun();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 具体的抓去网页的方法， 需要子类来补全实现逻辑
     *
     * @throws Exception
     */
    public abstract void doFetch() throws Exception;
}
