package app.services;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import app.model.User;

public class UserService {

	// 日志对象
	private static final Log log = Log.getLog(UserService.class);
	// 单例对象
	public final static UserService me = new UserService();
	
	//获取某个教职工用户的所有信息
	public Record getTeacherInfo(int id){
		String sql = "select u.id,u.name,u.sex,u.phone,u.email,t.proTitle,c.name as cname from t_user as u "
					+"join t_teacher as t on u.id = t.id "
					+"join t_college as c on t.collegeId = c.id "
					+"where u.id="+id;
		Record model = null;
		try {
			model = Db.findFirst(sql);
			if(model != null){
				return model;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//用户修改密码
	public boolean changePwd(int id, String oldPassword, String password)
	{
		User user = User.dao.findById(id);   //根据id查找到对应用户
		if(user != null && user.getPassword().equals(oldPassword)){
			//允许修改密码
			user.setPassword(password);
			user.update();
			return true;
		}
		return false;
	}
	
}
