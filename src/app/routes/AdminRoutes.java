package app.routes;

import com.jfinal.config.Routes;

import app.controller.CourseController;
import app.controller.LoginController;
import app.controller.MainController;
import app.controller.UserController;
import app.interceptors.CorsInterceptor;

/**
 * 后端路由
 */
public class AdminRoutes extends Routes {

	@Override
	public void config() {
		//设置全局视图层根目录
        setBaseViewPath("/WEB-INF/view");
		//添加路由级别的跨域拦截器
		addInterceptor(new CorsInterceptor());
		//添加路由级别的登陆拦截器
		//addInterceptor(new LoginInterceptor());
		//静态路由
		add("/", MainController.class);
		add("/login", LoginController.class);
		add("/course", CourseController.class);
		add("/user", UserController.class);
	}
	
}
