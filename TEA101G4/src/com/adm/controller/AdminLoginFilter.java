package com.adm.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adm.model.AdminnoVO;

public class AdminLoginFilter implements Filter{
	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}
	public void destroy() {
		config = null;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
	
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
	
		HttpSession session = req.getSession();
	
		AdminnoVO userVO = (AdminnoVO) session.getAttribute("userVO");
		if (userVO == null) {
			session.setAttribute("location", req.getRequestURI());
			System.out.println(req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/back-end/login.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	
	
	}
}
