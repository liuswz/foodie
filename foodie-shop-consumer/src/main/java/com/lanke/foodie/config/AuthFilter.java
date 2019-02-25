package com.lanke.foodie.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
@Slf4j
@Component
@Order(value=0)
@WebFilter(urlPatterns = "/webapi/*", filterName = "authFilter")
public class AuthFilter implements Filter {



    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        Assertion assertion = (Assertion) request.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
//        if(assertion!=null){
//            chain.doFilter(request,response);
//        } else {
//            response.sendRedirect("");
//        }


//        res.setHeader("Access-Control-Allow-Origin", "*");
//
//        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
//
//        res.setHeader("Access-Control-Max-Age", "1728000");
//
//        res.setHeader("Access-Control-Allow-Headers",
//
//                "Authentication, Authorization, content-type, Accept, x-requested-with, Cache-Control");
//
//        Principal principal  = assertion.getPrincipal();
//
//        String loginName =principal.getName();
//        System.out.printf("登录用户名:%s\r\n",loginName);
//
//        System.out.printf("ValidFromDate:%s\r\n",assertion.getValidFromDate());
//        System.out.printf("ValidUntilDate:%s\r\n",assertion.getValidUntilDate());
//        System.out.printf("AuthenticationDate:%s\r\n",assertion.getAuthenticationDate());

        HttpServletRequest request_ = (HttpServletRequest)request;
        HttpServletResponse response_ = (HttpServletResponse) response;
        String loginName = CASUtil.getAccountNameFromCas(request_);
        if(StringUtils.isNotEmpty(loginName)){
            log.info("访问者 ：" +loginName);
          //  request_.getSession().setAttribute("loginName", loginName);
         //   chain.doFilter(request, response);
        }else{
            //request_.getRequestDispatcher("/redirectlogin").forward(request_, response_);
            String path=request_.getRequestURI();
            //log.info(path+"------------------------------------");
            if(path.indexOf("redirectlogin")<0&&path.indexOf("gotoAdmin")<0) {
                request_.getRequestDispatcher("/redirectlogin").forward(request_, response_);
                return ;
            }

        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }


}


