//package com.lanke.foodie.config;
//
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//@Component
//@WebFilter(urlPatterns={"/**"})
//public class LoginFilter  implements Filter {
//
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        HttpSession session =request.getSession();
//
//        String url = request.getRequestURI();
//       if (!(url.equals("/consumer/shopdetail/redirect")||url.equals("/consumer/shopdetail/logout")||url.equals("/consumer/shopdetail/login"))){
//           if(session.getAttribute("username")!=null){
//
//               filterChain.doFilter(servletRequest, servletResponse);
//           }else{
//
//               response.sendRedirect("/consumer/shopdetail/redirect");
//           }
//       }else{
//
//           System.out.println( session.getId()+"33333333333333333333");
//       }
//
//
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
