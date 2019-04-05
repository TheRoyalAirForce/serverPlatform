package app.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Record;

import app.Consts;
import app.services.UserService;

public class UserController extends Controller{

	public void index(){
		renderText("user controller");
	}
	
	/**
	 * 获得某个登陆用户（教职工）的所有信息
	 */
	public void getTeacherInfo(){
		int id = getParaToInt("teacherId");
		Record model = UserService.me.getTeacherInfo(id);
		Ret ret = Ret.create();
		if(model != null){
			ret.put("code", 1);
			ret.put("msg", "查询成功");
			ret.put("data", model);
		}
		else{
			ret.put("code", -1);
			ret.put("msg", "查询失败，没有该用户信息");
		}
		renderJson(ret);
	}
	
	/**
	 * 用户修改密码
	 */
	public void changePwd(){
		int id = getParaToInt("userId");
		String oldPwd = getPara("oldPwd");
		String pwd = getPara("pwd");
		boolean flag = UserService.me.changePwd(id, oldPwd, pwd);
		if(flag){
	    	setSessionAttr(Consts.ADMIN_SESSION_USER, null);   //清除用户会话
			renderJson(Ret.ok());
		}
		else{
			renderJson(Ret.fail("msg","旧密码输入错误"));
		}
	}
}
