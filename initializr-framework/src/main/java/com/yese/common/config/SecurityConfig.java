// package com.yese.common.config;
//
// /**
//  * TODO
//  *
//  * @author 张庆福
//  * @date 2020/9/1
//  */
//
// import com.baomidou.mybatisplus.extension.api.R;
// import com.yese.common.enums.ResultCodeEnum;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.*;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
//
// import java.io.PrintWriter;
//
// @Configuration
// public class SecurityConfig extends WebSecurityConfigurerAdapter {
//     @Bean
//     PasswordEncoder passwordEncoder() {
//         return NoOpPasswordEncoder.getInstance();
//     }
//
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.authorizeRequests()
//                 .anyRequest().authenticated()
//                 .and()
//                 .formLogin()
//                 .successHandler((req, resp, authentication) -> {
//                     Object principal = authentication.getPrincipal();
//                     resp.setContentType("application/json;charset=utf-8");
//                     PrintWriter out = resp.getWriter();
//                     // out.write(new ObjectMapper().writeValueAsString(principal));
//                     out.write("登陆成功");
//                     out.flush();
//                     out.close();
//                 })
//                 .failureHandler((req, resp, e) -> {
//                     resp.setContentType("application/json;charset=utf-8");
//                     PrintWriter out = resp.getWriter();
//                     R<Object> r = new R<>();
//                     r.setData(e.getMessage());
//                     if (e instanceof LockedException) {
//                         r.setCode(ResultCodeEnum.USER_LOCKED.getCode()).setMsg(ResultCodeEnum.USER_LOCKED.getMsg());
//                     } else if (e instanceof CredentialsExpiredException) {
//                         r.setCode(ResultCodeEnum.USER_CREDENTIALS_EXPIRED.getCode()).setMsg(ResultCodeEnum.USER_CREDENTIALS_EXPIRED.getMsg());
//                     } else if (e instanceof AccountExpiredException) {
//                         r.setCode(ResultCodeEnum.USER_ACCOUNT_EXPIRED.getCode()).setMsg(ResultCodeEnum.USER_ACCOUNT_EXPIRED.getMsg());
//                     } else if (e instanceof DisabledException) {
//                         r.setCode(ResultCodeEnum.USER_DISABLED.getCode()).setMsg(ResultCodeEnum.USER_DISABLED.getMsg());
//                     } else if (e instanceof BadCredentialsException) {
//                         r.setCode(ResultCodeEnum.USER_BAD_CREDENTIALS.getCode()).setMsg(ResultCodeEnum.USER_BAD_CREDENTIALS.getMsg());
//                     }
//                     out.write("登录失败");
//                     out.flush();
//                     out.close();
//
//
//                     // resp.setContentType("application/json;charset=utf-8");
//                     // PrintWriter out = resp.getWriter();
//                     // out.write(e.getMessage());
//                     // out.flush();
//                     // out.close();
//
//                 })
//                 .permitAll()
//                 .and()
//                 .logout()
//                 .logoutUrl("/logout")
//                 .permitAll()
//                 .and()
//                 .csrf().disable();
//     }
// }