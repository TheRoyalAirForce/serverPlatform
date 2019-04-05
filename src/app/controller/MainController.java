package app.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import app.interceptors.LoginInterceptor;

@Before(LoginInterceptor.class)
public class MainController extends Controller{

	/**
	 * 进入首页
	 */
	public void index() {
		render("index.html");
	}

	/**
	 * 其他页面的访问接口
	 */
	public void head(){
		render("head.html");
	}
	public void changePwd(){
		render("changePwd.html");
	}
	public void main(){
		render("main.html");
	}
	public void left(){
		render("left.html");
	}
	public void news(){
		render("news.html");
	}
	public void courseInfo(){
		render("courseInfo.html");
	}
	public void studentList(){
		render("studentList.html");
	}
	public void leaveList(){
		render("leaveList.html");
	}
	public void absentList(){
		render("absentList.html");
	}
	public void autoSignList(){
		render("autoSignList.html");
	}
	public void autoSignDetail(){
		render("autoSignDetail.html");
	}
	public void roll(){
		render("roll.html");
	}
	public void myInfo(){
		render("myInfo.html");
	}
}
