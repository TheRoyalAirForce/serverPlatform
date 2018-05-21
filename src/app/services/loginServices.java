package app.services;

import com.jfinal.log.Log;
import app.model.User;

public class loginServices {

	// 日志对象
	private static final Log log = Log.getLog(mainService.class);
	// 单例对象
	public final static loginServices me = new loginServices();

	// 登录用户
	public User findUser(String username, String password) {
		String sql = "select * from t_user where phone = '" + username + "' or email = '" + username + "'";
		User user = User.dao.findFirst(sql);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

}
