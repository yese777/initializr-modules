package com.yese.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用注册表(Application)表控制层扩展类，一般初次生成，后续不再覆盖。这个类提供编写自己定义的方法。
 *
 * @author 张庆福
 * @since 2020-08-30 01:05:28
 */
@Api(tags = "应用注册表接口")
@Slf4j
@RestController
@RequestMapping("application")
public class ApplicationExtendController extends ApplicationController {

}