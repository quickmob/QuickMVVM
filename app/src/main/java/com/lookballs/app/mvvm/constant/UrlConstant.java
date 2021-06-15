package com.lookballs.app.mvvm.constant;

/**
 * 创建人：lucas
 * 创建时间：2021/5/21 18:14
 * 类描述：url相关常量
 */
public class UrlConstant {
    //分模块

    public static final String baseUrl = "https://www.wanandroid.com/";

    public static class Banner {
        public static final String banner_json = baseUrl + "banner/json";
    }

    public static class Article {
        public static String article = baseUrl + "article/list/%s/json?author=鸿洋";
    }
}
