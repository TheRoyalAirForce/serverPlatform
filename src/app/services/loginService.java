package app.services;

import com.jfinal.log.Log;
import app.model.User;
import app.util.MD5Util;

public class loginService {

	// 日志对象
	private static final Log log = Log.getLog(mainService.class);
	// 单例对象
	public final static loginService me = new loginService();

	// 登录用户
	public User findUser(String username, String password) {
		//String pwd = MD5Util.md5(password);
		String sql = "select * from t_user where phone = '" + username + "' or email = '" + username + "'";  // + "' or id=" + username;    
 		User user = User.dao.findFirst(sql);
 		if(user == null){
 			try {
				//对学号做查询
 				sql = "select * from t_user where id=" + username;
 				user = User.dao.findFirst(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}
 		}
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

}
