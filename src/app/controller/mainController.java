package app.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import app.Consts;
import app.interceptors.loginInterceptor;
import app.model.User;

@Before(loginInterceptor.class)
public class mainController extends Controller{

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
	public void changepwd(){
		render("changepwd.html");
	}
	public void main(){
		render("main.html");
	}
	public void left(){
		render("left.html");
	}
	public void p3(){
		render("p3.html");
	}
	public void courseInfo(){
		render("courseinfo.html");
	}
	public void studentList(){
		render("studentlist.html");
	}
	public void leaveList(){
		render("leavelist.html");
	}
	public void absentList(){
		render("absentlist.html");
	}
	public void autoSignList(){
		render("autosignlist.html");
	}
	public void autoSignDetail(){
		render("autosigndetail.html");
	}
	public void roll(){
		render("roll.html");
	}
	public void myInfo(){
		render("myinfo.html");
	}
}
