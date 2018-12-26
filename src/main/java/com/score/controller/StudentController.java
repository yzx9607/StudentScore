package com.score.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.score.bean.ResultObject;
import com.score.bean.TStudent;
import com.score.service.TStudentService;
import com.score.util.Constant;

/**
 * @author hmg
 *
 */
@RequestMapping("/student")
@RestController //标识为返回类型为Json的控制
public class StudentController {
	@Autowired
	private TStudentService studentService;
	  //标识请求地址
    @RequestMapping("/getAllStudent")
    public ResultObject<List<TStudent>> getUsers(TStudent student,@RequestParam("limit") int limit,@RequestParam("page") int page) {
    	PageInfo<TStudent> pageInfo=studentService.getAll(student, page, limit);
    	ResultObject<List<TStudent>> rs=new ResultObject<List<TStudent>>();
    	rs.setCode(Constant.SUCCESS_RETUEN_CODE);
    	rs.setMsg("查询成功");
    	rs.setData(pageInfo.getList());
    	rs.setCount(pageInfo.getTotal());
    	return rs;
    }
	  //标识请求地址
    @RequestMapping("/addStudent")
    public ResultObject<Object> addStudent(TStudent student) {

    	Integer studentNo=student.getStudentNo();
    	student.setStuPass(studentNo.toString());
    	TStudent result=studentService.selectByNo(studentNo);
    	//统一返回
    	ResultObject<Object> rs=new ResultObject<Object>();
    	if(null==result) {
    		studentService.addStudent(student);
    		rs.setCode(Constant.SUCCESS_RETUEN_CODE);
    		rs.setMsg("增加学生信息成功");
    	}else {
    		rs.setCode(Constant.HASE_RETUEN_CODE);
        	rs.setMsg("学号已存在");
    	}
    	return rs;
    }
	  //标识请求地址
    @RequestMapping("/updateStudent")
    public ResultObject<Object> updateStudent(TStudent student) {
    	Integer total=studentService.updateStudent(student);
    	//统一返回
    	ResultObject<Object> rs=new ResultObject<Object>();
    	if(null==total||0==total) {
    		rs.setCode(Constant.FAILURE_RETUEN_CODE);
    		rs.setMsg("修改学生信息失败");
    	}else {
    		rs.setCode(Constant.SUCCESS_RETUEN_CODE);
    		rs.setMsg("修改学生信息成功");
    	}
    	return rs;
    }
	  //标识请求地址
    @RequestMapping("/deleteStudent")
    public ResultObject<Object> deleteStudent(@RequestParam("studentNo") int studentNo) {
    	Integer total=studentService.deleteStudent(studentNo);
    	//统一返回
    	ResultObject<Object> rs=new ResultObject<Object>();
    	if(null==total||0==total) {
    		rs.setCode(Constant.FAILURE_RETUEN_CODE);
    		rs.setMsg("修改学生信息失败");
    	}else {
    		rs.setCode(Constant.SUCCESS_RETUEN_CODE);
    		rs.setMsg("修改学生信息成功");
    	}
    	return rs;
    }
    
    /**
     * 获取学生下拉框
     * @return
     */
    @RequestMapping("/studentSelect")
    public ResultObject<List<TStudent>> studentSelect() {
    	ResultObject<List<TStudent>> rs=new ResultObject<List<TStudent>>();
    	List<TStudent> list=studentService.selectAllStudent();
    	rs.setCode(Constant.SUCCESS_RETUEN_CODE);
    	rs.setMsg("查询成功");
    	rs.setData(list);
    	int total=list.size();
    	Long a=Long.parseLong(String.valueOf(total));
    	rs.setCount(a);
    	return rs;
    }
    
}
