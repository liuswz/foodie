//package com.lanke.foodie.config;
//
//import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.cas.ServiceProperties;
//import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
//import org.springframework.security.cas.authentication.CasAuthenticationProvider;
//import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
//import org.springframework.security.cas.web.CasAuthenticationFilter;
//import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.logout.LogoutFilter;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
//
//@Configuration
//public class SecurityConfiguration {
////    @Autowired
////    private CasServerConfig casServerConfig;
////
////    @Autowired
////    private CasServiceConfig casServiceConfig;
//
//    @Value("${cas.server.host.url}")
//    private String casServerUrl;
//
//    @Value("${cas.server.host.login_url}")
//    private String casServerLoginUrl;
//
//    @Value("${cas.server.host.logout_url}")
//    private String casServerLogoutUrl;
//
//    @Value("${app.server.host.url}")
//    private String appServerUrl;
//
//    @Value("${app.login.url}")
//    private String appLoginUrl;
//
//    @Value("${app.logout.url}")
//    private String appLogoutUrl;
//
//
//    @Bean
//    public ServiceProperties serviceProperties() {
//        ServiceProperties serviceProperties = new ServiceProperties();
//        serviceProperties.setService(appServerUrl+appLoginUrl);
//        serviceProperties.setSendRenew(false);
//        return serviceProperties;
//    }
//
//    @Bean
//    public CasAuthenticationFilter casAuthenticationFilter(AuthenticationManager authenticationManager, ServiceProperties serviceProperties) {
//        CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
//        casAuthenticationFilter.setAuthenticationManager(authenticationManager);
//        casAuthenticationFilter.setServiceProperties(serviceProperties);
//        casAuthenticationFilter.setFilterProcessesUrl(appLoginUrl);
//        casAuthenticationFilter.setContinueChainBeforeSuccessfulAuthentication(false);
//        casAuthenticationFilter.setAuthenticationSuccessHandler(
//                new SimpleUrlAuthenticationSuccessHandler("/")
//        );
//        return casAuthenticationFilter;
//    }
//
//    @Bean
//    public CasAuthenticationEntryPoint casAuthenticationEntryPoint(ServiceProperties serviceProperties) {
//        CasAuthenticationEntryPoint entryPoint = new CasAuthenticationEntryPoint();
//        entryPoint.setLoginUrl(casServerLoginUrl);
//        entryPoint.setServiceProperties(serviceProperties);
//        return entryPoint;
//    }
//
//    @Bean
//    public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
//        return new Cas20ServiceTicketValidator(casServerUrl);
//    }
//
//    @Bean
//    public CasAuthenticationProvider casAuthenticationProvider(
//            AuthenticationUserDetailsService<CasAssertionAuthenticationToken> userDetailsService,
//            ServiceProperties serviceProperties, Cas20ServiceTicketValidator ticketValidator) {
//        CasAuthenticationProvider provider = new CasAuthenticationProvider();
//        provider.setKey("casProvider");
//        provider.setServiceProperties(serviceProperties);
//        provider.setTicketValidator(ticketValidator);
//        provider.setAuthenticationUserDetailsService(userDetailsService);
//
//        return provider;
//    }
//
//    @Bean
//    public LogoutFilter logoutFilter() {
//        String logoutRedirectPath = casServerLogoutUrl;
//        LogoutFilter logoutFilter = new LogoutFilter(logoutRedirectPath, new SecurityContextLogoutHandler());
//        logoutFilter.setFilterProcessesUrl(appLogoutUrl);
//        return logoutFilter;
//    }
//}