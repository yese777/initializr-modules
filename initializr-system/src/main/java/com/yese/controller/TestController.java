package com.yese.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.yese.common.annotation.Log;
import com.yese.common.enums.BusinessType;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;


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


}