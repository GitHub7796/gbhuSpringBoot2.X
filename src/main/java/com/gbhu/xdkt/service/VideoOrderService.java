package com.gbhu.xdkt.service;

import com.gbhu.xdkt.model.entity.VideoOrder;

import java.util.List;

public interface VideoOrderService {
    int saveOrder(Integer userId, Integer orderId);

    List<VideoOrder> getOrderByUserId(Integer userId);
}
