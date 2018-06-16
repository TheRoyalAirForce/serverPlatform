package app.routes;

import com.jfinal.config.Routes;

import app.controller.courseController;
import app.controller.loginController;
import app.controller.mainController;
import app.controller.userController;
import app.interceptors.corsInterceptor;
import app.interceptors.loginInterceptor;

/**
 * 后端路由
 */
public class adminRoutes extends Routes {

	@Override
	public void config() {
		//设置全局视图层根目录
        setBaseViewPath("/WEB-INF/view");
		//添加路由级别的跨域拦截器
		addInterceptor(new corsInterceptor());
		//添加路由级别的登陆拦截器
		//addInterceptor(new loginInterceptor());
		//静态路由
		add("/",mainController.class);  
		add("/login",loginController.class);
		add("/course",courseController.class);
		add("/user",userController.class);
	}
	
}
