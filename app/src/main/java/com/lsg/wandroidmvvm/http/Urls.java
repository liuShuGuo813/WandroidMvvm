package com.lsg.wandroidmvvm.http;

import rxhttp.wrapper.annotation.DefaultDomain;
import rxhttp.wrapper.annotation.Domain;

/**
 * Created by lsg on 2019/12/23
 */
public class Urls {

    @DefaultDomain //设置为默认域名
    public static final String baseUrl = "https://www.wanandroid.com";

    public static final String banner_url = "/banner/json";

    /**
     * %d page
     */
    public static final String main_article_url = "article/list/%d/json";

    /**
     * 置顶文章
     */
    public static final String top_article_url = "article/top/json";

    /**
     * 项目分类
     */
    public static final String project_tree_url = "project/tree/json";

    /**
     * 项目列表 %d page cid
     */
    public static final String project_article_url = "project/list/%d/json?cid=%d";





}
