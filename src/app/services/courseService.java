package app.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.template.stat.ast.Return;

import app.model.AutoSignInTableList;
import app.model.Course;
import app.model.Elecourse;
import app.model.User;
import app.util.dateTimeUtil;

public class courseService {
	// 日志对象
	private static final Log log = Log.getLog(courseService.class);
	// 单例对象
	public final static courseService me = new courseService();
	
	// 添加一条选课记录
	public void addElecEntry(Elecourse elec){
		elec.save();
	}
	
	// 获得某一课程的学生花名册
	public List<Record> getStudentList(int courseId){
		List<Record> list = new ArrayList<Record>();
		String tableName = "course_" + courseId + "_student_list";
		String sql = "select studentId,courseId,studentName,AbsentRecordCount,LeaveRecordCount,remark from " + tableName + " order by studentId asc";
		try {
			list = Db.find(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	//学生签到
	public Record studentSignIn(int courseId, int studentId, int row, int col){
		String mDate = dateTimeUtil.getChangeTime3(dateTimeUtil.getTimel());  //当前日期
		String tableName = "sign_in_" + courseId + "_" + mDate;
		Record record = Db.findFirst("select * from " + tableName + " where row=" + row +" and col=" + col);
		if(record == null){
			//String sql = "select * from " + tableName + " where studentId=" + studentId;
			//Record model = Db.findFirst(sql);   //找到当前学生记录
			Record model = Db.findById(tableName, "studentId", studentId);
			if(model != null){
				model.set("row", row);
				model.set("col", col);
				model.set("flag", 1);
				model.set("isAbsent", 0);
				model.set("isLeave", 0);
				model.set("signTime", new Date());
				Db.update(tableName, "studentId", model);
				return model;
			}
		}
		return null;
	}
	
	// 学生缺课记录更新，教师端做更新操作(在本次授课结束后)，或者每一天凌晨6点更新一次
	public boolean studentAbsentUpdate(int courseId, int studentId) {
		int num = 0;
		Record model = null;
		String sql = "select * from t_auto_sign_in_table_list where courseId=" + courseId;
		List<AutoSignInTableList> autoSignInTableList = AutoSignInTableList.dao.find(sql);
		for (AutoSignInTableList l : autoSignInTableList) {
			model = Db.findFirst("select * from " + l.getTableName() + " where studentId=" + studentId);
			if (model != null && model.getInt("isAbsent") == 1) {
				num++;
			}
		}
		// 统计旷课次数完毕，在名册表上更新
		try {
			String CoursetableName = "course_" + courseId + "_student_list";
			// 在名册表上更新记录
			Db.update("UPDATE " + CoursetableName + " SET AbsentRecordCount=" + num + " WHERE studentId=" + studentId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 学生请假
	public Record studentLeave(int courseId, int studentId) {
		String mDate = dateTimeUtil.getChangeTime3(dateTimeUtil.getTimel()); // 当前日期
		String tableName = "sign_in_" + courseId + "_" + mDate;
		Record model = Db.findById(tableName, "studentId", studentId);
		String CoursetableName = "course_" + courseId + "_student_list";
		if (model != null && model.getInt("isLeave") == 0) {
			try {
				model.set("isLeave", 1);
				model.set("isAbsent", 0);
				Db.update(tableName, "studentId", model);
				// 在名册表上更新记录
				Record record = Db.findFirst("select * from " + CoursetableName + " where studentId=" + studentId);
				int num = record.getInt("LeaveRecordCount") + 1;
				Db.update("UPDATE " + CoursetableName + " SET LeaveRecordCount=" + num + " WHERE studentId=" + studentId);
				return model;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	// 查询某一次课学生的签到座位（限今日）
	public List<Record> getOccupiedSeats(int courseId) {
		String mDate = dateTimeUtil.getChangeTime3(dateTimeUtil.getTimel()); // 当前日期
		return getOccupiedSeatsList(courseId, mDate);
	}

	// 查询指定日期某一课程学生签到座位情况
	public List<Record> getOccupiedSeats(int courseId, String mDate) {
		return getOccupiedSeatsList(courseId, mDate);
	}

	// 获得某次课所有已经被选择的签到座位
	public List<Record> getOccupiedSeatsList(int courseId, String mDate) {
		try {
			String tableName = "sign_in_" + courseId + "_" + mDate;
			//String sql = "select studentId,row,col from " + tableName + " where not row=0 and not col=0";
			String sql = "select s.studentId,s.row,s.col,u.name as stdname from " + tableName + " as s join t_user as u on s.studentId = u.id";
			return Db.find(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 獲得本次课签到情况，返回该签到表数据（限教师端调用,今日）
	public List<Record> getSignList(int courseId){
		String mDate = dateTimeUtil.getChangeTime3(dateTimeUtil.getTimel()); // 当前日期
		String tableName = "sign_in_" + courseId + "_" + mDate;
		String sql = "select * from "+ tableName;
		try {
			return Db.find(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// 獲得本次课签到情况，返回该签到表数据（限教师端调用,可选日期）
	public List<Record> getSignList(int courseId, String mDate){
		String tableName = "sign_in_" + courseId + "_" + mDate;
		String sql = "select * from "+ tableName;
		try {
			return Db.find(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 获得某个老师所有的开课信息
	public List<Course> getCourseList(int teacherId){
		List<Course> list = new ArrayList<Course>();
		try {
			String sql = "select * from t_course where teacherId=" + teacherId;
			list = Course.dao.find(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 查询某个老师的所有上课和签到记录（多张签到表表名，日期）
	public List<AutoSignInTableList> getCourseSignList(int teacherId){
		List<Course> list = getCourseList(teacherId);
		if(list.size() > 0){
			List<Integer> courseList = new ArrayList<Integer>();
			for(Course c:list){
				courseList.add(c.getId());
			}
			String sql = "select * from t_auto_sign_in_table_list";
			List<AutoSignInTableList> autoSignInTableList = AutoSignInTableList.dao.find(sql);
			List<AutoSignInTableList> sList = new ArrayList<AutoSignInTableList>();
			for(AutoSignInTableList l:autoSignInTableList){
				if(courseList.contains(l.getCourseId())){   
					sList.add(l);
				}
			}
			return sList;
		}
		return null;
	}
	
	// 获得该学生的所有缺席记录
	public List<Record> getAbsentList(int studentId) {
		String sql = "select * from t_auto_sign_in_table_list";
		List<AutoSignInTableList> tablelist = AutoSignInTableList.dao.find(sql);
		List<Record> list = new ArrayList<Record>();
		for (int i = 0; i < tablelist.size(); i++) {
			sql = "select isAbsent from " + tablelist.get(i).getTableName() + " where studentId=" + studentId;
			Record model = Db.findFirst(sql);
			if(model != null){
				int courseId = tablelist.get(i).get("courseId");
				Date mDate = tablelist.get(i).get("createDate");
				Course course = Course.dao.findById(courseId);
				User user = User.dao.findById(course.getTeacherId());
				if (model.getInt("isAbsent") == 1) {
					model.set("date", dateTimeUtil.getChangeTime2(mDate.getTime()));
					model.set("courseId", courseId);
					model.set("courseName", course.getName());
					model.set("TeacherName", user.getName());
					list.add(model);
				}
			}
		}
		return list;
	}
	
	// 获得该学生的所有请假记录
	public List<Record> getLeaveList(int studentId) {
		String sql = "select * from t_auto_sign_in_table_list";
		List<AutoSignInTableList> tablelist = AutoSignInTableList.dao.find(sql);
		List<Record> list = new ArrayList<Record>();
		for (int i = 0; i < tablelist.size(); i++) {
			sql = "select isLeave from " + tablelist.get(i).getTableName() + " where studentId=" + studentId;
			Record model = Db.findFirst(sql);
			if(model != null){
				int courseId = tablelist.get(i).get("courseId");
				Date mDate = tablelist.get(i).get("createDate");
				Course course = Course.dao.findById(courseId);
				User user = User.dao.findById(course.getTeacherId());
				if (model.getInt("isLeave") == 1) {
					model.set("date", dateTimeUtil.getChangeTime2(mDate.getTime()));
					model.set("courseId", courseId);
					model.set("courseName", course.getName());
					model.set("TeacherName", user.getName());
					list.add(model);
				}
			}
		}
		return list;
	}
	
	// 开始课程，建立签到表，并根据名册录入初始学生数据
	public boolean startCourse(int courseId) {
		try {
			String mDate = dateTimeUtil.getChangeTime3(dateTimeUtil.getTimel()); 
			String tableName = "sign_in_" + courseId + "_" + mDate;
			autoCreateNewSignInTable(courseId, tableName);
			// 新表建立完毕，开始录入初始学生数据
			String CoursetableName = "course_" + courseId + "_student_list";
			List<Record> list = Db.find("select * from " + CoursetableName); // 查询学生花名册
			for (int i = 0; i < list.size(); i++) {
				Record model = new Record();
				model.set("studentId", list.get(i).get("studentId"));
				model.set("row", 0);
				model.set("col", 0);
				model.set("flag", 0);
				model.set("isAbsent", 1);   //默认设置为缺课
				model.set("isLeave", 0);
				model.set("signTime", new Date(0));
				Db.save(tableName, model);  //插入数据
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		};
		return true;
	}
	
	// 根据传入的课程ID，创建学生花名册表并录入初始数据,，之后不能再进行选课
	public boolean CreateStudentOfCourseList(int courseId) {
		try {
			// 查询语句
			String sql = "select e.courseId,c.teacherId,e.studentId,u.name as studentName "
					+ "from t_eleCourse as e join t_user as u on e.studentId = u.id "
					+ "join t_course as c on e.courseId = c.id " + "where e.courseId = " + courseId
					+ " ORDER BY e.studentId ASC";
			String tableName = "course_" + courseId + "_student_list";
			autoCreateNewStudentListTable(courseId, tableName); // 建立新表
			List<Record> list = Db.find(sql);
			for (int i = 0; i < list.size(); i++) {
				Record model = new Record();
				model.set("courseId", list.get(i).get("courseId"));
				model.set("teacherId", list.get(i).get("teacherId"));
				model.set("studentId", list.get(i).get("studentId"));
				model.set("studentName", list.get(i).get("studentName"));
				model.set("AbsentRecordCount", 0);
				model.set("LeaveRecordCount", 0);
				model.set("performScore", 0);
				model.set("remark", "");
				Db.save(tableName, model);
			}
			// 禁止其他学生再选此课
			Course course = Course.dao.findById(courseId);
			course.setEnable(0);
			course.update();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//使用Db+Record方法自动建 课程学生名册表
	public void autoCreateNewStudentListTable(int courseId, String tableName)
	{
		//建表语句
		String createSQL = "CREATE TABLE "+tableName+" ("
				  +"studentId int(20) NOT NULL COMMENT '学生ID',"
				  +"courseId int(20) NOT NULL COMMENT '课程ID',"
				  +"teacherId int(20) NOT NULL COMMENT '任课教师ID',"
				  +"studentName varchar(50) NOT NULL COMMENT '学生姓名',"
				  +"AbsentRecordCount int(10) NOT NULL COMMENT '缺席次数',"
				  +"LeaveRecordCount int(10) NOT NULL COMMENT '请假次数',"
				  +"performScore int(10) NOT NULL COMMENT '平时分（满分100）',"
				  +"remark varchar(100) DEFAULT NULL COMMENT '备注',"
				  +"PRIMARY KEY (`studentId`)"
				+") DEFAULT CHARSET=utf8;";
		Db.update(createSQL);
	}
	
	//使用Db+Record方法自动建 课程学生签到表
	public void autoCreateNewSignInTable(int courseId, String tableName){
		String createSQL =  "CREATE TABLE " +tableName+ " ("
				  +"studentId int(20) NOT NULL COMMENT '学生ID',"
				  +"row int(10) NOT NULL COMMENT '签到座位（行）',"
				  +"col int(10) NOT NULL COMMENT '签到座位（列）',"
				  +"flag int(10) NOT NULL COMMENT '签到标志',"
				  +"isAbsent tinyint(2) NOT NULL COMMENT '是否缺席',"
				  +"isLeave tinyint(2) NOT NULL COMMENT '是否请假',"
				  +"signTime datetime NOT NULL COMMENT '签到时间 （精确到秒）',"
				  +"PRIMARY KEY (`studentId`)"
				+") DEFAULT CHARSET=utf8;";
		try {
			Db.update(createSQL);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		//在自动记录表上添加一条记录 
		AutoSignInTableList autoSignInTableList = new AutoSignInTableList();
		autoSignInTableList.setTableName(tableName);
		autoSignInTableList.setCreateDate(new Date());
		autoSignInTableList.setCourseId(courseId);
		autoSignInTableList.save();
	}
}










