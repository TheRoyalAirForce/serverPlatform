package app.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import app.interceptors.loginInterceptor;

@Before(loginInterceptor.class)
public class mainController extends Controller{

	/**
	 * 进入首页
	 */
	public void index() {
		render("index.html");
	}

}
