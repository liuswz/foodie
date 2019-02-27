//package com.lanke.foodie.config2;
//
//import com.lanke.foodie.config2.AuthorityInfo;
//import com.lanke.foodie.config2.UserInfo;
//import com.lanke.foodie.service.DetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
//import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * 用于加载用户信息 实现UserDetailsService接口，或者实现AuthenticationUserDetailsService接口
// * @author ChengLi
// *
// */
//public class CustomUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {
//		/*
//	//实现UserDetailsService接口，实现loadUserByUsername方法
//	implements UserDetailsService {
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.out.println("当前的用户名是："+username);
//		//这里我为了方便，就直接返回一个用户信息，实际当中这里修改为查询数据库或者调用服务什么的来获取用户信息
//		UserInfo userInfo = new UserInfo();
//		userInfo.setUsername("admin");
//		userInfo.setName("admin");
//		Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();
//		AuthorityInfo authorityInfo = new AuthorityInfo("TEST");
//		authorities.add(authorityInfo);
//		userInfo.setAuthorities(authorities);
//		return userInfo;
//	}*/
//
//
//	//实现AuthenticationUserDetailsService，实现loadUserDetails方法
//
//
//
//	@Autowired
//	private DetailService detailService;
//	@Override
//	public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
//		System.out.println("当前的用户名是："+token.getName());
//		/*这里我为了方便，就直接返回一个用户信息，实际当中这里修改为查询数据库或者调用服务什么的来获取用户信息*/
//		String username = token.getName();
//		String authority = detailService.getAuthorityByUsername(username);
//
//		UserInfo userInfo = new UserInfo();
//		userInfo.setUsername(username);
//
//		Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();
//		AuthorityInfo authorityInfo = new AuthorityInfo(authority);
//
//		authorities.add(authorityInfo);
//		userInfo.setAuthorities(authorities);
//
//
//		return userInfo;
//
//
//
//
//
//
//
//
//	}
//
//}
