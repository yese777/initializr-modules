package com.yese.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.yese.common.annotation.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 验证码
 *
 * @author 张庆福
 * @date 2020/9/2
 */
@RestController
public class VerifyCodeController {

    /**
     * 生成验证码
     */
    @Log(module = "验证码", content = "生成验证码")
    @GetMapping("/verifyCode")
    public void createVerifyCode(HttpServletResponse response, HttpSession session) throws IOException {
        // 定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
        try (ServletOutputStream out = response.getOutputStream()) {
            captcha.write(out);
        }
        session.setAttribute("verify_code", captcha.getCode());
    }
}