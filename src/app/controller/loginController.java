package app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

import app.Consts;
import app.model.User;
import app.services.loginServices;

/**
 * 	后台管理功能首页
 * @author lzy
 */
//@ControllerBind(controllerKey = "/admin/login")
//@Clear(AdminInterceptor.class)
public class loginController extends Controller {
	
	private static final Logger log  = LoggerFactory.getLogger(loginController.class);
	
	public void index(){
		render("login.html");
	}
	
	/**
	 * 登录验证码
	 */
	public static final String VALIDATE_CODE = "LOGIN_VALIDATE_CODE";
	
	
	/**
	 * 登录处理
	 * 进行用用户名和密码验证
	 */
	public void doLogin(){
		//变量获取与定义
		//String tenantNo = getPara(Consts.TENANTNO);
		String username = getPara("username");
		String password = getPara("password");
		//boolean remember = getParaToBoolean("remember", false);
		Ret ret = Ret.create();
    	//1.参数校验
		if(StrKit.isBlank(username)) {
			ret.fail("msg", "用户名不能为空");
		}
		if(StrKit.isBlank(password)) {
			ret.fail("msg", "密码不能为空");
		}
		if(ret.isFail()) {
			renderJson(ret);
			return;
		}
		//2.逻辑处理
		User user = loginServices.me.findUser(username, password);
		if(user == null) {
			renderJson(ret.fail("msg", "用户名或密码错误"));
			return;
		}
		//2.1用户状态判断
//		switch (user.getStatus()) {
//		case "01":
//			ret.fail("msg", "帐户已禁用");
//			break;
//		case "02":
//			ret.fail("msg", "帐户待激活");
//			break;
//		case "03":
//			ret.fail("msg", "帐户待审核");
//			break;
//		case "04":
//			ret.fail("msg", "帐户审核未能过");
//			break;
//		case "05":
//			ret.fail("msg", "帐户已过期");
//			break;
//		default:
//			ret.setOk();
//			break;
//		}
		ret.setOk();
		//2.2登录失败
		if(ret.isFail()) {
			renderJson(ret);
			return;
		}
		//2.3登录成功
		setSessionAttr(Consts.ADMIN_SESSION_USER, user);
		log.debug("login success, by user: {}", user);
		renderJson(ret);
		//render("/index.html");  //直供测试使用
    }
	 
    public void logout() {
    	setSessionAttr(Consts.ADMIN_SESSION_USER, null);   //清除用户会话
    	redirect("/login");
    }
	
	/**
	 * 是否是验证码登录
	 * @param useruame 用户名
	 * @param isFail 计数加1
	 * @param clean 计数清零
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean){
		
		//不验证
		if(true){
			return false;
		}
		
		final String loginFailMapKey = "login:fail:map";
		Cache cache = Redis.use();
		int loginFailNum = 0;
		if(cache.hexists("loginFailMap", useruame)){
			loginFailNum = cache.hget(loginFailMapKey, useruame);
		}
		if (isFail){
			loginFailNum++;
			cache.hset(loginFailMapKey, useruame, loginFailNum);
		}
		if (clean){
			cache.hdel(loginFailMapKey, useruame);
		}
		return loginFailNum >= 3;
	}
}
