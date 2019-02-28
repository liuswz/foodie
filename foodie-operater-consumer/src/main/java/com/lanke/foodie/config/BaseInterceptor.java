package com.lanke.foodie.config;

import com.alibaba.fastjson.JSON;
import com.lanke.foodie.json.BaseJson;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class BaseInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object arg2) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession();
		if(session.getAttribute("username")!=null){
			return true;
		}else{

			return reLogin(response);
		}

	}

	private boolean reLogin(HttpServletResponse response) throws IOException {
		PrintWriter out;
		try{
			BaseJson baseJson = new BaseJson();
			baseJson.setCode(1);
			baseJson.setMessage("失败");
			baseJson.setResult("请登入");
			//response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			out.append(JSON.toJSONString(baseJson));
			return false;
		} catch (Exception e){
			e.printStackTrace();
			response.sendError(500);
			return false;
		}
	}


}
