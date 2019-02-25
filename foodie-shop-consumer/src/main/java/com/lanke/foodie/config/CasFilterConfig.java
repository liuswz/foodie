package com.lanke.foodie.config;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;

import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

/**

 * cas过滤器配置

 */

@Configuration
class CasFilterConfig {



    private static final String CAS_SERVER_URL_PREFIX = "http://127.0.0.1:8088/cas";


    private static final String SERVER_NAME = "http://127.0.0.1:9002";


    private static final String CAS_SERVER_URL_LOGIN = "http://127.0.0.1:8088/cas/login";



    /**
     * description: 登录过滤器
     * @param: []
     * @return: org.springframework.boot.web.servlet.FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean filterSingleRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SingleSignOutFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/*");
        Map<String,String> initParameters = new HashMap<String, String>();
        initParameters.put("casServerUrlPrefix", CAS_SERVER_URL_PREFIX);

        registration.setInitParameters(initParameters);
        // 设定加载的顺序
        registration.setOrder(1);
        return registration;
    }


    /**
     * description:过滤验证器
     *     * @param: []
     * @return: org.springframework.boot.web.servlet.FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean filterValidationRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new Cas30ProxyReceivingTicketValidationFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/*");
        Map<String,String>  initParameters = new HashMap<String, String>();
        initParameters.put("casServerUrlPrefix", CAS_SERVER_URL_PREFIX);
        initParameters.put("serverName", SERVER_NAME);
        initParameters.put("useSession", "true");
        registration.setInitParameters(initParameters);
        // 设定加载的顺序
        registration.setOrder(1);
        return registration;
    }


    /**
     * description:授权过滤器
     * @param: []
     * @return: org.springframework.boot.web.servlet.FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean filterAuthenticationRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthenticationFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/*");
        Map<String,String>  initParameters = new HashMap<String, String>();
        initParameters.put("casServerLoginUrl", CAS_SERVER_URL_LOGIN);
        initParameters.put("serverName", SERVER_NAME);
        //忽略/logout的路径
        initParameters.put("ignorePattern", "/common/logout");
        initParameters.put("ignorePattern", "/consumer/shopdetail/register");
        initParameters.put("ignorePattern", "/common/redirectlogin");
        initParameters.put("ignoreUrlPatternType", "com.lanke.foodie.config.SimpleUrlPatternMatcherStrategy");

      //  initParameters.put("ignoreUrlPatternType", "com.zhang.springbootcasclient1.auth.SimpleUrlPatternMatcherStrategy");

        registration.setInitParameters(initParameters);
        // 设定加载的顺序
        registration.setOrder(1);
        return registration;
    }

    /**
     * wraper过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filterWrapperRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new HttpServletRequestWrapperFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/*");
        // 设定加载的顺序
        registration.setOrder(1);
        return registration;
    }

    /**
     * 添加监听器
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean<EventListener> singleSignOutListenerRegistration(){
        ServletListenerRegistrationBean<EventListener> registrationBean = new ServletListenerRegistrationBean<EventListener>();
        registrationBean.setListener(new SingleSignOutHttpSessionListener());
        registrationBean.setOrder(1);
        return registrationBean;
    }



//        @Value("${cas.server-url-prefix}")
//        private String serverUrlPrefix;
//        @Value("${cas.server-login-url}")
//        private String serverLoginUrl;
//        @Value("${cas.client-host-url}")
//        private String clientHostUrl;
//
//
//
//        /**
//
//         * 授权过滤器
//
//         * @return
//
//         */
//
//        @Bean
//
//        public FilterRegistrationBean filterAuthenticationRegistration() {
//
//            FilterRegistrationBean registration = new FilterRegistrationBean();
//
//            registration.setFilter(new AuthenticationFilter());
//
//            // 设定匹配的路径
//
//            registration.addUrlPatterns("/*");
//
//            Map<String,String> initParameters = new HashMap<String, String>();
//
//            initParameters.put("casServerLoginUrl", serverUrlPrefix);
//
//            initParameters.put("serverName", clientHostUrl);
//
//            //忽略的url，"|"分隔多个url
//
//
//            //忽略/logout的路径
//        initParameters.put("ignorePattern", "/common/logout");
//        initParameters.put("ignorePattern", "/consumer/shopdetail/register");
//        initParameters.put("ignorePattern", "/common/redirectlogin");
//        initParameters.put("ignoreUrlPatternType", "com.lanke.foodie.config.SimpleUrlPatternMatcherStrategy");
//
//      //  initParameters.put("ignoreUrlPatternType", "com.zhang.springbootcasclient1.auth.SimpleUrlPatternMatcherStrategy");
//            registration.setInitParameters(initParameters);
//
//            // 设定加载的顺序
//
//            registration.setOrder(1);
//
//            return registration;
//
//        }



}
