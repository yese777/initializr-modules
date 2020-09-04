package com.yese.common.config;

/**
 * TODO
 *
 * @author 张庆福
 * @date 2020/9/3
 */

import com.baomidou.mybatisplus.extension.api.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yese.common.enums.ResultCodeEnum;
import com.yese.common.filter.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;

@Configuration
public class Security extends WebSecurityConfigurerAdapter {
    // 密码加密方式不加密
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 配置忽略认证的 URL 地址(静态资源,验证码生成地址)
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/verifyCode");
    }

    // @Autowired
    // FindByIndexNameSessionRepository sessionRepository;
    //
    // @Bean
    // SpringSessionBackedSessionRegistry sessionRegistry() {
    //     return new SpringSessionBackedSessionRegistry(sessionRepository);
    // }

    @Bean
    LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            // 这里不知道为什么用户密码没有自动设置为 null,就手动设置
            Object principal = authentication.getPrincipal();
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            R<Object> r = new R<>();
            r.setMsg("登录成功").setData(principal);
            out.write(new ObjectMapper().writeValueAsString(r));
            out.flush();
            out.close();
        });
        loginFilter.setAuthenticationFailureHandler((request, response, e) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            R<Object> r = new R<>();
            if (e instanceof LockedException) {
                r.setCode(ResultCodeEnum.USER_LOCKED.getCode()).setMsg(ResultCodeEnum.USER_LOCKED.getMsg());
            } else if (e instanceof CredentialsExpiredException) {
                r.setCode(ResultCodeEnum.USER_CREDENTIALS_EXPIRED.getCode()).setMsg(ResultCodeEnum.USER_CREDENTIALS_EXPIRED.getMsg());
            } else if (e instanceof AccountExpiredException) {
                r.setCode(ResultCodeEnum.USER_ACCOUNT_EXPIRED.getCode()).setMsg(ResultCodeEnum.USER_ACCOUNT_EXPIRED.getMsg());
            } else if (e instanceof DisabledException) {
                r.setCode(ResultCodeEnum.USER_DISABLED.getCode()).setMsg(ResultCodeEnum.USER_DISABLED.getMsg());
            } else if (e instanceof BadCredentialsException) {
                r.setCode(ResultCodeEnum.USER_BAD_CREDENTIALS.getCode()).setMsg(ResultCodeEnum.USER_BAD_CREDENTIALS.getMsg());
            } else if (e instanceof AuthenticationServiceException) {
                r.setCode(ResultCodeEnum.VERIFY_CODE_ERROR.getCode()).setMsg(ResultCodeEnum.VERIFY_CODE_ERROR.getMsg());
            }
            out.write(new ObjectMapper().writeValueAsString(r));
            out.flush();
            out.close();
        });
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setFilterProcessesUrl("/auth/login");
        return loginFilter;
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .anyRequest().authenticated().and()
                .logout().logoutUrl("/auth/logout")
                // 注销
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    R<Object> r = new R<>();
                    r.setMsg("注销成功");
                    out.write(new ObjectMapper().writeValueAsString(r));
                    out.flush();
                    out.close();
                }).permitAll().and()
                .csrf().disable()
                .exceptionHandling()
                // 未登录,未认证
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    R<Object> r = new R<>();
                    r.setCode(ResultCodeEnum.USER_NOT_LOGIN.getCode()).setMsg(ResultCodeEnum.USER_NOT_LOGIN.getMsg());
                    out.write(new ObjectMapper().writeValueAsString(r));
                    out.flush();
                    out.close();
                })
        ;
        // .and()
        // .sessionManagement()
        // .maximumSessions(1)
        // .maxSessionsPreventsLogin(true)
        // .sessionRegistry(sessionRegistry());

        httpSecurity.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}