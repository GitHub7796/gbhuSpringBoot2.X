package com.gbhu.xdkt.mapper;

import com.gbhu.xdkt.model.entity.Video;
import com.gbhu.xdkt.model.entity.VideoBanner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Mapper
//可以不加 Mapper 注释，需启动类中添加 MapperScan
public interface VideoMapper {
    List<Video> listVideo();

    List<VideoBanner> listBanner();

    Video findDetailById(@Param("video_id") int videoId);

    Video getVideoSimpleInfo(@Param("order_id") Integer orderId);
}
