package com.java668.oxadmin.config.secrity;

import com.java668.oxadmin.config.secrity.components.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


/**
 * @author Jerry.chen
 * @desc SpringSecurity 配置
 * @date 2023/02/01 16:32
 **/
@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    private final AuthenticationFailureHandler authenticationFailureHandler;

    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private final LogoutSuccessHandler logoutSuccessHandler;

    private final SessionRegistry sessionRegistry;

    /**
     * 认证管理器：
     * 1. 认证信息（用户名，密码）
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }


    /**
     * 当你认证成功之后 ，springsecurity它会重写向到你上一次请求上
     * 资源权限配置：
     * 1. 被拦截的资源
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http     // 认证请求
                .authorizeRequests()
                .anyRequest()
                //所有访问该应用的http请求都要通过身份认证才可以访问
                .authenticated()
                .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .logout()
                //登出成功处理（对应流程4）
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling()
                // 未登录时的处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement()
                .maximumSessions(-1)
                // 设置为 true，表示某用户达到最大会话并发数后，新会话请求会被拒绝登录
                .maxSessionsPreventsLogin(true)
                // 设置所要使用的 sessionRegistry，默认为 SessionRegistryImpl 实现类
                .sessionRegistry(sessionRegistry);

    }

    /**
     * 一般是针对静态资源放行
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/doc.html",
                "/swagger-resources",
                "/v2/api-docs",
                "/webjars/**",
                "/favicon.ico"
        );
    }


    @Configuration
    static class SpringSecurityConfigurer {

        /**
         * 密码加密
         * @return
         */
        @Bean
        public PasswordEncoder passwordEncoder() {
            // 明文+随机盐值》加密存储
            return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }

        /**
         * 加载中文的认证提示信息
         * @return
         */
        @Bean
        public ReloadableResourceBundleMessageSource messageSource() {
            ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
            // .properties 不要加到后面
            messageSource.setBasename("classpath:org/springframework/security/messages_zh_CN");
            return messageSource;
        }

        @Bean
        public SessionRegistry sessionRegistry() {
            return new SessionRegistryImpl();
        }
    }
}
