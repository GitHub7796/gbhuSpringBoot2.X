package com.gbhu.xdkt.service;

import com.gbhu.xdkt.model.entity.Video;
import com.gbhu.xdkt.model.entity.VideoBanner;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VideoService {

    List<Video> listVideo();

    List<VideoBanner> listBanner();

    Video findDetailById(int videoId);
}
