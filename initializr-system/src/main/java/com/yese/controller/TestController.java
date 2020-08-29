package com.yese.controller;


import com.yese.common.annotation.Log;
import com.yese.common.enums.BusinessType;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "测试")
@Slf4j
@RestController
public class TestController {


    @Log(module = "测试模块", content = "测试get")
    @GetMapping("get")
    public String get(String aa, @RequestParam("cc") String bb) {
        return aa;
    }

    @Log(module = "测试模块", content = "测试异常", businessType = BusinessType.OTHER)
    @GetMapping("exception")
    public void exception() {
        int a = 1 / 0;
    }

    @Log(module = "测试模块", content = "简单测试", businessType = BusinessType.OTHER)
    @GetMapping("test")
    public void test() {
        System.out.println("---------------");
    }


}