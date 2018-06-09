package app.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Record;

import app.services.courseService;

public class courseController extends Controller {
	
	private static final Logger log  = LoggerFactory.getLogger(courseController.class);
	
	public void index(){
		//render("login.html");
		renderText("Course Controller");
	}
	
	/**
	 * 开始此次上课
	 */
	public void startCourse(){
		int courseId = getParaToInt("courseId");
		if(courseService.me.startCourse(courseId)){
			//自动建签到表
			renderJson(Ret.ok("msg", "创建并录入数据成功"));
		}
		else{
			renderJson(Ret.fail("msg", "内部错误"));
		}
	}
	
	/**
	 * 创建该课程学生花名册，禁止课程选课
	 */
	public void createStudentList(){
		int courseId = getParaToInt("courseId");
		if(courseService.me.CreateStudentOfCourseList(courseId)){
			renderJson(Ret.ok("msg", "创建并录入数据成功"));
		}
		else{
			renderJson(Ret.fail("msg", "内部错误"));
		}
	}
	
	/**
	 * 学生对某次课程签到, 签到时间为当前日期年月日
	 */
	public void studentSignIn()
	{
		int courseId = getParaToInt("courseId");
		int studentId = getParaToInt("studentId");
		int row = getParaToInt("row");
		int col = getParaToInt("col");
		//签到并返回学生信息
		Record model = courseService.me.studentSignIn(courseId, studentId, row, col);
		Ret ret = Ret.create();
		if(model != null){
			ret.put("code", 1);
			ret.put("msg", "签到成功");
			ret.put("data", model);
		}
		else{
			ret.put("code", -1);
			ret.put("msg", "该课程没有该学生ID，或者该座位已经被签到");
		}
		renderJson(ret);
	}
	
	/**
	 * 学生请假，限当日
	 */
	public void studentLeave()
	{
		int courseId = getParaToInt("courseId");
		int studentId = getParaToInt("studentId");
		//请假并返回学生信息
		Record model = courseService.me.studentLeave(courseId, studentId);
		Ret ret = Ret.create();
		if(model != null){
			ret.put("code", 1);
			ret.put("msg", "请假成功");
			ret.put("data", model);
		}
		else{
			ret.put("code", -1);
			ret.put("msg", "该次课程没有该学生ID, 或已经请假");
		}
		renderJson(ret);
	}
	
	/**
	 * 学生缺课记录更新
	 */
	public void studentAbsent(){
		int courseId = getParaToInt("courseId");
		int studentId = getParaToInt("studentId");
		Ret ret = Ret.create();
		//请假并返回学生信息
		boolean flag = courseService.me.studentAbsentUpdate(courseId, studentId);
		if(flag){
			ret.put("code", 1);
			ret.put("msg", "更新成功");
		}
		else{
			ret.put("code", -1);
			ret.put("msg", "操作失败");
		}
		renderJson(ret);
	}
	
	/**
	 * 获取某次课已经被占用的签到座位
	 */
	public void getOccupiedSeatList(){
		String mDate = getPara("mDate");
		int courseId = getParaToInt("courseId");
		Ret ret = Ret.create();
		List<Record> list = new ArrayList<Record>();
		if(mDate == null || mDate.equals("") || mDate.length()!=8){
			list = courseService.me.getOccupiedSeats(courseId);
		}
		else{
			list = courseService.me.getOccupiedSeats(courseId, mDate);
		}
		if(list != null && list.size()>0){
			ret.put("code", 1);
			ret.put("msg", "获取数据成功");
			ret.put("data", list);
		}
		else{
			ret.put("code", -1);
			ret.put("msg", "无座位信息");
		}
		renderJson(ret);
	}
	
	/**
	 * 获取某次课的所有 学生的签到表信息（教师端调用）
	 */
	public void getSignList(){
		int courseId = getParaToInt("courseId");   //获得传入的课程ID
		Ret ret = Ret.create();
		List<Record> list =  courseService.me.getSignList(courseId);
		if(list != null && list.size() > 0){
			ret.put("code", 1);
			ret.put("data", list);
		}
		else{
			ret.put("code", -1);
			ret.put("msg", "该次课未开课，尚未创建签到表");
		}
		renderJson(ret);
	}
	
	/**
	 * 获取所有学生记录名册
	 */
	public void getStudentList()
	{
		int courseId = getParaToInt("courseId");   //获得传入的课程ID
		List<Record> list =  courseService.me.getStudentList(courseId);
		Ret ret = Ret.create();
		if(list != null && list.size() > 0){
			ret.put("code", 1);
			ret.put("data", list);
		}
		else{
			ret.put("code", -1);
			ret.put("msg", "尚未创建该名册表");
		}
		renderJson(ret);
	}
	
	/**
	 * 获取某个学生所有缺席记录
	 */
	public void getStudentAbsentList(){
		int studentId = getParaToInt("studentId");
		List<Record> list =  courseService.me.getAbsentList(studentId);
		Ret ret = Ret.create();
		if(list != null && list.size() > 0){
			ret.put("code", 1);
			ret.put("data", list);
		}
		else{
			ret.put("code", -1);
			ret.put("msg", "没有缺席记录");
		}
		renderJson(ret);
	}
	
	/**
	 * 获取某个学生所有请假记录
	 */
	public void getStudentLeaveList(){
		int studentId = getParaToInt("studentId");
		List<Record> list =  courseService.me.getLeaveList(studentId);
		Ret ret = Ret.create();
		if(list != null && list.size() > 0){
			ret.put("code", 1);
			ret.put("data", list);
		}
		else{
			ret.put("code", -1);
			ret.put("msg", "没有请假记录");
		}
		renderJson(ret);
	}
}
