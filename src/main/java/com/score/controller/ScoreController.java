package com.score.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.score.bean.ResultObject;
import com.score.bean.TScore;
import com.score.bean.TStudent;
import com.score.service.IScoreService;
import com.score.util.Constant;

@RequestMapping("/score")
@RestController //标识为返回类型为Json的控制
public class ScoreController {
	/**
	 * 注入成绩
	 */
	@Autowired
	private IScoreService scoreService;
	
	  //标识请求地址
    @RequestMapping("/addScore")
    public ResultObject<Object> addScore(TScore score) {
    	ResultObject<Object> rs=scoreService.insertScore(score);
    	return rs;
    }
    
	  //标识请求地址
    @RequestMapping("/getAllScore")
    public ResultObject<List<TScore>> getAllScore(TScore score,@RequestParam("limit") int limit,@RequestParam("page") int page) {
    	PageInfo<TScore> pageInfo=scoreService.getAllScore(score,limit, page);
    	ResultObject<List<TScore>> rs=new ResultObject<List<TScore>>();
    	List<TScore> list=pageInfo.getList();
    	for(TScore temp:list) {
    		String type=temp.getScoreType();
    		if("1".equals(type)) {
    			temp.setScoreTypeName("习题");
    		}
    		if("2".equals(type)) {
    			temp.setScoreTypeName("测验");
    		}
    		if("3".equals(type)) {
    			temp.setScoreTypeName("考试成绩");
    		}
    	}
    	rs.setCode(Constant.SUCCESS_RETUEN_CODE);
    	rs.setMsg("查询成功");
    	rs.setData(list);
    	rs.setCount(pageInfo.getTotal());
    	return rs;
    }
    
    
    
    //标识请求地址
    @RequestMapping("/updateScore")
    public ResultObject<Object> updateScore(TScore score) {
    	Integer total=scoreService.updateScore(score);
    	//统一返回
    	ResultObject<Object> rs=new ResultObject<Object>();
    	if(null==total||0==total) {
    		rs.setCode(Constant.FAILURE_RETUEN_CODE);
    		rs.setMsg("修改学生成绩信息失败");
    	}else {
    		rs.setCode(Constant.SUCCESS_RETUEN_CODE);
    		rs.setMsg("修改学生成绩信息成功");
    	}
    	return rs;
    }
    //标识请求地址
    @RequestMapping("/deleteScore")
    public ResultObject<Object> deleteScore(TScore score) {
    	Integer total=scoreService.deleteScore(score.getScoreId());
    	//统一返回
    	ResultObject<Object> rs=new ResultObject<Object>();
    	if(null==total||0==total) {
    		rs.setCode(Constant.FAILURE_RETUEN_CODE);
    		rs.setMsg("删除学生成绩信息失败");
    	}else {
    		rs.setCode(Constant.SUCCESS_RETUEN_CODE);
    		rs.setMsg("删除学生成绩信息成功");
    	}
    	return rs;
    }
    
    @RequestMapping("/getAllSumScore")
    public ResultObject<List<TStudent>> getAllSumScore(TStudent student,@RequestParam("limit") int limit,@RequestParam("page") int page) {
    	PageInfo<TStudent> pageInfo =scoreService.getAllFinalScore(student, limit, page);
    	ResultObject<List<TStudent>> rs=new ResultObject<List<TStudent>>();
    	rs.setCode(Constant.SUCCESS_RETUEN_CODE);
    	rs.setMsg("查询成功");
    	rs.setData(pageInfo.getList());
    	rs.setCount(pageInfo.getTotal());
    	return rs;
    	
    }
    //获取我的成绩
    @RequestMapping("/getMyScore")
    public ResultObject<List<TStudent>> getAllSumScore(HttpServletRequest request,@RequestParam("limit") int limit,@RequestParam("page") int page) {
    	TStudent student = (TStudent)request.getSession().getAttribute("student");
    	PageInfo<TStudent> pageInfo =scoreService.getAllFinalScore(student, limit, page);
    	ResultObject<List<TStudent>> rs=new ResultObject<List<TStudent>>();
    	rs.setCode(Constant.SUCCESS_RETUEN_CODE);
    	rs.setMsg("查询成功");
    	rs.setData(pageInfo.getList());
    	rs.setCount(pageInfo.getTotal());
    	return rs;
    	
    } 
    
    @RequestMapping("/getMyScoreInfo")
    public ResultObject<List<TScore>> getMyScoreInfo(HttpServletRequest request,@RequestParam("limit") int limit,@RequestParam("page") int page) {
    	TStudent student = (TStudent)request.getSession().getAttribute("student");
    	TScore score=new TScore();
    	score.setStudentId(student.getStudentNo());
    	PageInfo<TScore> pageInfo=scoreService.getAllScore(score,limit, page);
    	ResultObject<List<TScore>> rs=new ResultObject<List<TScore>>();
    	List<TScore> list=pageInfo.getList();
    	for(TScore temp:list) {
    		String type=temp.getScoreType();
    		if("1".equals(type)) {
    			temp.setScoreTypeName("习题");
    		}
    		if("2".equals(type)) {
    			temp.setScoreTypeName("测验");
    		}
    		if("3".equals(type)) {
    			temp.setScoreTypeName("考试成绩");
    		}
    	}
    	rs.setCode(Constant.SUCCESS_RETUEN_CODE);
    	rs.setMsg("查询成功");
    	rs.setData(list);
    	rs.setCount(pageInfo.getTotal());
    	return rs;
    	
    } 
}
