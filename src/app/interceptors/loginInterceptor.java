package app.interceptors;

import javax.servlet.http.HttpSession;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import app.Consts;
import app.model.User;

public class loginInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
//		System.out.println("Before method invoking");
//		System.out.println("拦截方法名:"+inv.getMethodName());
//		System.out.println("拦截方法Actionkey值："+inv.getActionKey());  //只能用于控制层面
//		inv.invoke();  //传递本次调用，调用剩下的拦截器与目标方法
//		System.out.println("After method invoking");
		Controller controller = inv.getController();
		HttpSession session = controller.getSession();
		if(session == null){
			controller.redirect("/login");
		}
		else{
			User sessionUser = (User)session.getAttribute(Consts.ADMIN_SESSION_USER);
			if(sessionUser == null){
				controller.redirect("/login");
			}
			else{
				//获取当前用户会话
			}
		}
	}

}
