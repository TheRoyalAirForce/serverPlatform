package app.services;

import com.jfinal.log.Log;

public class MainService {

	// 日志对象
	private static final Log log = Log.getLog(MainService.class);
	// 单例对象
	public final static MainService me = new MainService();
	
}
