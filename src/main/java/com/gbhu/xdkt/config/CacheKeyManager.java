package com.gbhu.xdkt.config;

/**
 * 缓存管理类
 */
public class CacheKeyManager {
    //首页视频列表缓存key
    //key 的设置方式采用模块进行划分
    public static final String INDEX_VIDEO = "index:video:list";

    //首页轮播图缓存ket
    public static final String INDEX_CHAPTER = "index:banner:list";

    //视频详情缓存key，%s是视频id
    public static final String INDEX_EPISODE = "index:episode:%s";


}
