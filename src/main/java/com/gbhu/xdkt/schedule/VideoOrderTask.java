package com.gbhu.xdkt.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class VideoOrderTask {

    //定时查询订单
    @Scheduled(cron = "/1*****")//每秒执行
//    @Scheduled(fixedDelay = 40000)
//    @Scheduled(fixedRate = 40000)
    public void sum() {
        //TODO
    }
}
