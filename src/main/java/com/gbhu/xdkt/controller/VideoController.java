package com.gbhu.xdkt.controller;

import com.gbhu.xdkt.model.entity.Video;
import com.gbhu.xdkt.model.entity.VideoBanner;
import com.gbhu.xdkt.service.VideoService;
import com.gbhu.xdkt.utils.BaseCache;
import com.gbhu.xdkt.utils.JsonData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Mapper
@Controller
@RestController
@RequestMapping("api/v1/pub/video")
public class VideoController {

    @Autowired
    private VideoService videoService;


    @GetMapping("list")
    public JsonData listVideo() {
        List<Video> videos = videoService.listVideo();
        return JsonData.Sucess(videos);
    }

    @GetMapping("list_banner")
    public JsonData listBanner() {
        List<VideoBanner> videoBannerList = videoService.listBanner();
        return JsonData.Sucess(videoBannerList);
    }


    @GetMapping("find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "video_id", required = true) int videoId) {
        Video video = videoService.findDetailById(videoId);
        return JsonData.Sucess(video);
    }
}
