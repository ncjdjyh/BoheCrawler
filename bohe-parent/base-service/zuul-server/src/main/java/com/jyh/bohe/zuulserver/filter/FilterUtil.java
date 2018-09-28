package com.jyh.bohe.zuulserver.filter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FilterUtil {
    public static Set<String> freeServiceSet;
    public static Set<String> authServiceSet;
    public static Map<String, String> roleServiceMap;

    static {
        freeServiceSet = new HashSet();
        authServiceSet = new HashSet();
        roleServiceMap = new HashMap<>();

        freeServiceSet.add("food-service");
        freeServiceSet.add("auth-service");

        authServiceSet.add("favorite-service");
    }

    public static boolean filterFree(String url) {
        for (String s : freeServiceSet) {
            if (url.matches(".*"+s+".*")) {
                return true;
            }
        }
        return false;
    }

    public static boolean filterAuth(String url) {
        for (String s : authServiceSet) {
            if (url.matches(".*"+s+".*")) {
                return true;
            }
        }
        return false;
    }
}
