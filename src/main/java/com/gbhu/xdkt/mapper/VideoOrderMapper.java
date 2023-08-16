package com.gbhu.xdkt.mapper;

import com.gbhu.xdkt.model.entity.VideoOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoOrderMapper {

    VideoOrder isBuyed(@Param("user_id") Integer userId, @Param("video_id") Integer videoId, @Param("state") Integer state);

    int saveOrder(VideoOrder videoOrder);

    List<VideoOrder> getOrderByUserId(@Param("user_id") Integer userId);
}
