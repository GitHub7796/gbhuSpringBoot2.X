package com.gbhu.xdkt.task;

import com.gbhu.xdkt.model.entity.User;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
@Async
public class AsyncTask {
    public void test1() {
        try {
            System.out.println("AsyncTask-task1 start");
            System.out.println(System.currentTimeMillis());
            Thread.sleep(8000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("AsyncTask-task1 end");
        System.out.println(System.currentTimeMillis());

    }

    public void test2() {
        try {
            System.out.println("AsyncTask-task2 start");
            System.out.println(System.currentTimeMillis());
            Thread.sleep(8000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("AsyncTask-task2 end");
        System.out.println(System.currentTimeMillis());
    }

    public Future<User> test3() {
        try {
            System.out.println("AsyncTask-task3 start");
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("AsyncTask-task3");
        User user = new User();
        user.setName("AsyncTask");
        //处理逻辑TODO
        return new AsyncResult<User>(user);
    }

}
