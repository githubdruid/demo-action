package com.hzslb.securitydemo.config;

import com.hzslb.securitydemo.service.impl.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)// 控制权限注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserService());
        //自定义的MD5加密
//        auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder(){
//            /**
//             * 对方法加密
//             * @param rawPassword 原始密码
//             * @return  密文
//             */
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return MD5Util.encode((String)rawPassword);
//            }
//
//            /**
//             * 验证密码和加密后密码是否一致
//             * @param rawPassword   原密码
//             * @param encodedPassword   加密后的密码
//             * @return  是否一致
//             */
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return encodedPassword.equals(MD5Util.encode((String)rawPassword));
//            }});
        //多重加密————迭代SHA算法+密钥+随机盐来对密码加密
        //encode()每次生产的密码都不相同 需要保存好secret密钥
        auth.userDetailsService(customUserService()).passwordEncoder(new StandardPasswordEncoder("github"));
    }

    @Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated()//任何请求，登录后都可以访问
                .and()
                .formLogin()
                .loginPage("/login")
//                .defaultSuccessUrl("/hello")
                .failureUrl("/login?error")
                .permitAll()//登陆页面用户任意访问
                .and()
                .logout().permitAll()//注销行为任意访问
                .and().httpBasic();//进行http Basic认证
//                .and().csrf().disable()//关闭csrf
    }
}
