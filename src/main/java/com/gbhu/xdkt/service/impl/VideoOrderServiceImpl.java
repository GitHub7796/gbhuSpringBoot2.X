package com.gbhu.xdkt.service.impl;

import com.gbhu.xdkt.mapper.VideoMapper;
import com.gbhu.xdkt.mapper.VideoOrderMapper;
import com.gbhu.xdkt.model.entity.Video;
import com.gbhu.xdkt.model.entity.VideoOrder;
import com.gbhu.xdkt.service.VideoOrderService;
import com.gbhu.xdkt.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private VideoMapper videoMapper;
    @Override
    public int saveOrder(Integer userId, Integer orderId) {
        //查看是否购买过
        VideoOrder videoOrder = videoOrderMapper.isBuyed(userId, orderId, 1);
        if (videoOrder != null) {
            return 0;
        }

        Video video=videoMapper.getVideoSimpleInfo(orderId);
        VideoOrder newVideoOrder = new VideoOrder();
        newVideoOrder.setVideoId(video.getId());
        newVideoOrder.setCreateTime(new Date());
        newVideoOrder.setVideoImg(video.getCoverImg());
        newVideoOrder.setVideoTitle(video.getTitle());
        newVideoOrder.setState(1);
        newVideoOrder.setUserId(userId);
        newVideoOrder.setTotalFee(video.getPrice());
        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        return videoOrderMapper.saveOrder(newVideoOrder);
    }

    @Override
    public List<VideoOrder> getOrderByUserId(Integer userId) {
        return videoOrderMapper.getOrderByUserId(userId);
    }
}
