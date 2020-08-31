package com.yese.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.yese.common.annotation.Log;
import com.yese.common.enums.BusinessType;
import io.swagger.annotations.Api;
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

    @Log(module = "测试模块", content = "测试参数校验")
    @GetMapping("validation")
    public R<String> validation(@RequestParam("a") @NotNull(message = "{id.NotNull}") String a) {
        return R.ok(a);
    }

    @Log(module = "测试模块", content = "测试异常", businessType = BusinessType.OTHER)
    @GetMapping("exception")
    public void exception() {
        int a = 1 / 0;
    }

    @PostMapping("/set")
    public void set(HttpSession session, @RequestParam("key") String key, @RequestParam("value") String value) {
        session.setAttribute(key, value);
    }

    @GetMapping("/getAll")
    public Map<String, Object> getAll(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        // 遍历
        for (Enumeration<String> enumeration = session.getAttributeNames(); enumeration.hasMoreElements(); ) {
            String key = enumeration.nextElement();
            Object value = session.getAttribute(key);
            result.put(key, value);
        }
        // 返回
        return result;
    }
}