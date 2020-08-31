package com.yese.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.yese.common.annotation.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@Api(tags = "测试")
@Slf4j
@RestController
@Validated
public class TestController {
    @ApiOperation("测试参数校验")
    @Log(module = "测试模块", content = "测试参数校验")
    @GetMapping("validation")
    public R<String> validation(@RequestParam("a") @NotNull(message = "{id.NotNull}") String a) {
        return R.ok(a);
    }

    @ApiOperation("测试异常")
    @Log(module = "测试模块", content = "测试异常")
    @GetMapping("exception")
    public void exception() {
        int a = 1 / 0;
    }

    @ApiOperation("测试session设置值")
    @Log(module = "测试模块", content = "测试session设置值")
    @PostMapping("/session")
    public R session(HttpSession session, @RequestParam("key") String key, @RequestParam("value") String value) {
        session.setAttribute(key, value);
        return R.ok(null);
    }

    @ApiOperation("测试获取session中的值")
    @Log(module = "测试模块", content = "测试获取session中的值")
    @GetMapping("/session/All")
    public R getAll(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        // 遍历
        for (Enumeration<String> enumeration = session.getAttributeNames(); enumeration.hasMoreElements(); ) {
            String key = enumeration.nextElement();
            Object value = session.getAttribute(key);
            result.put(key, value);
        }
        // 返回
        return R.ok(result);
    }
}