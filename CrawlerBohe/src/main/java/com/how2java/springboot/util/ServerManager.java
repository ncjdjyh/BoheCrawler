package com.how2java.springboot.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ServerManager {
    private static Collection<FoodsServer> servers = Collections.synchronizedCollection(new ArrayList<FoodsServer>());

    public static void broadCast(String msg) {
        for (FoodsServer foodServer : servers) {
            try {
                foodServer.sendMessage(msg);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static int getTotal(){
        return servers.size();
    }
    public static void add(FoodsServer server) {
        System.out.println("有新连接加入！ 当前总连接数是："+ (servers.size() + 1));
        servers.add(server);
    }
    public static void remove(FoodsServer server) {
        System.out.println("有连接退出！ 当前总连接数是："+ servers.size());
        servers.remove(server);
    }
}