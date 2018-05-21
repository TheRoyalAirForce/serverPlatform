package app.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dateTimeUtil {
	// 获取当前时间
	public long getTimel() {
		return (new Date()).getTime();
	}
	// 时间转换
	public String getChangeTime(long timel) {
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return timeFormat.format(timel);
	}
	// 时间转换2
	public String getChangeTime2(long timel) {
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
		return timeFormat.format(timel);
	}
}
