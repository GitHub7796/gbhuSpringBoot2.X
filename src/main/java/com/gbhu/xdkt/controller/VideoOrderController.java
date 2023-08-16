package com.gbhu.xdkt.controller;

import com.gbhu.xdkt.model.entity.VideoOrder;
import com.gbhu.xdkt.model.request.VideoOrderRequest;
import com.gbhu.xdkt.service.VideoOrderService;
import com.gbhu.xdkt.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//视频订单
@RestController
@RequestMapping("/api/v1/pri/order")
public class VideoOrderController {


    @Autowired
    private VideoOrderService videoOrderService;
    //下单
    @PostMapping("save")
    public JsonData saveOrder(@RequestBody VideoOrderRequest videoOrderRequest, HttpServletRequest request) {
        Integer user_id = (Integer) request.getAttribute("user_id");
        int rows=videoOrderService.saveOrder(user_id, videoOrderRequest.getVideoId());
        return rows==0?JsonData.Fail("下单失败"):JsonData.Sucess();
    }

    //获取订单列表
    @RequestMapping("list")
    public JsonData getOrder(HttpServletRequest request) {
        Integer user_id = (Integer) request.getAttribute("user_id");
        List<VideoOrder> videoOrderList = videoOrderService.getOrderByUserId(user_id);
        return JsonData.Sucess(videoOrderList);
    }
}
