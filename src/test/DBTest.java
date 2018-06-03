package test;

import java.util.Date;
import java.util.List;

import org.beetl.core.misc.PrimitiveArrayUtil;
import org.junit.Test;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.template.expr.ast.Id;

import app.model.Elecourse;

public class DBTest extends JFinalModelCase{

	// 测试方法，添加多条测试选课数据
	@Test
	public void testAddEleCourse() {
		int num = 4;
		for (int i = 0; i < num; i++) {
			Elecourse elecourse = new Elecourse();
			elecourse.setId(StrKit.getRandomUUID());
			elecourse.setStudentId(170327020 + i);
			elecourse.setCourseId(30002);
			elecourse.setCreateDate(new Date());
			elecourse.save();
		}
		for (int i = 0; i < num; i++) {
			Elecourse elecourse = new Elecourse();
			elecourse.setId(StrKit.getRandomUUID());
			elecourse.setStudentId(170327020 + i + 4);
			elecourse.setCourseId(30003);
			elecourse.setCreateDate(new Date());
			elecourse.save();
		}
	}
	
	//测试方法，建立花名册表，并导入初始信息
	@Test
	public void testAddStudentOfCourseList(){
		int courseId = 30003;   //写死，测试用
		//查询语句
		String sql = "select e.courseId,c.teacherId,e.studentId,u.name as studentName "+
				"from t_eleCourse as e join t_user as u on e.studentId = u.id "+
				"join t_course as c on e.courseId = c.id "+
				"where e.courseId = " + courseId +" ORDER BY e.studentId ASC";
		String tableName = "course_"+courseId+"_student_list";
		autoCreateNewTable(tableName);  //建立新表
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
	}
	
	//测试方法，使用Db+Record方法自动建表
	public void autoCreateNewTable(String tableName)
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
}














