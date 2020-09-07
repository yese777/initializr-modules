package com.yese.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户表(SysUser)表控制层扩展类，一般初次生成，后续不再覆盖。这个类提供编写自己定义的方法。
 *
 * @author 张庆福
 * @since 2020-09-07 23:44:51
 */
@Api(tags = "系统用户表接口")
@Slf4j
@RestController
@RequestMapping("sysUser")
public class SysUserExtendController extends SysUserController {

}