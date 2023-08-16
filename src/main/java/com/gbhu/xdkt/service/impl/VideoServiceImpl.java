package com.gbhu.xdkt.service.impl;

import com.gbhu.xdkt.config.CacheKeyManager;
import com.gbhu.xdkt.mapper.VideoMapper;
import com.gbhu.xdkt.model.entity.Video;
import com.gbhu.xdkt.model.entity.VideoBanner;
import com.gbhu.xdkt.service.VideoService;
import com.gbhu.xdkt.utils.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private BaseCache baseCache;

    @Override
    public List<Video> listVideo() {
        try {
            Object cache = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEO, () -> {
                return videoMapper.listVideo();
            });
            if (cache instanceof List) {
                List<Video> videoList = (List<Video>) cache;
                return videoList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //大厂中会准备兜底数据
        return null;
    }

    @Override
    public List<VideoBanner> listBanner() {
        try {
            Object cache = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_CHAPTER, () -> {
                return videoMapper.listBanner();
            });
            if (cache instanceof List) {
                List<VideoBanner> videoList = (List<VideoBanner>) cache;
                return videoList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //大厂中会准备兜底数据
        return null;
    }

    @Override
    public Video findDetailById(int videoId) {
        String INDEX_EPISODE = String.format(CacheKeyManager.INDEX_EPISODE, videoId);
        try {
            Object cache = baseCache.getTenMinuteCache().get(INDEX_EPISODE, () -> {
                return videoMapper.findDetailById(videoId);
            });
            if (cache instanceof Video) {
                Video video = (Video) cache;
                return video;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //大厂中会准备兜底数据
        return null;
    }
}
