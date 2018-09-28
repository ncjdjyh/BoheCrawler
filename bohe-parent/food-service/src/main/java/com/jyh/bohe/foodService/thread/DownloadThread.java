package com.jyh.bohe.foodService.thread;

import com.jyh.bohe.foodService.crawler.AbstractJob;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Date;

public class DownloadThread extends AbstractJob {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/8/11 17:29
     * @Description: 下载工具类
     */
    private Collection<String> urls;

    public DownloadThread(Collection<String> urls) {
        this.urls = urls;
    }

    private void downloadPicture() {
        try {
            //开始时间
            Date begindate = new Date();
            for (String url : urls) {
                //开始时间
                Date begindate2 = new Date();
                String imageName = url.substring(url.lastIndexOf("/") + 1);
                URL uri = new URL(url);
                InputStream in = uri.openStream();
                FileOutputStream fo = new FileOutputStream(new File("C:\\projects\\BoheCrawler\\craw-bohe-font\\static\\foods\\" + imageName));//文件输出流
                byte[] buf = new byte[1024];
                int length = 0;
                System.out.println("开始下载:" + url);
                while ((length = in.read(buf, 0, buf.length)) != -1) {
                    fo.write(buf, 0, length);
                }
                //关闭流
                in.close();
                fo.close();
                System.out.println(imageName + "下载完成");
                //结束时间
                Date overdate2 = new Date();
                double time = overdate2.getTime() - begindate2.getTime();
                System.out.println("耗时：" + time / 1000 + "s");
            }
            Date overdate = new Date();
            double time = overdate.getTime() - begindate.getTime();
            System.out.println("总耗时：" + time / 1000 + "s");
        } catch (Exception e) {
            System.out.println("下载失败");
        }
    }

    @Override
    public void doFetch() {
        downloadPicture();
    }
}
