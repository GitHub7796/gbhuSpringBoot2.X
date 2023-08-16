package com.gbhu.xdkt.exception;

import com.gbhu.xdkt.utils.JsonData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)//处理那种异常
    @ResponseBody//是否返回数据
    public JsonData excetionHandler(Exception e) {
        if (e instanceof MyException) {
            MyException myException = (MyException) e;
            return JsonData.Fail(myException.getCode(), myException.getMsg());
        }
        return JsonData.Fail("未知异常");
    }
}
