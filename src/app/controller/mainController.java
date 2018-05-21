package app.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import app.interceptors.loginInterceptor;

@Before(loginInterceptor.class)
public class mainController extends Controller{

	public void index() {
		render("index.html");
	}

}
