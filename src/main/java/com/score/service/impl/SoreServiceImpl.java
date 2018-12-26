package com.score.service.impl;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.score.bean.ResultObject;
import com.score.bean.TScore;
import com.score.bean.TStudent;
import com.score.dao.TScoreMapper;
import com.score.dao.TStudentMapper;
import com.score.service.IScoreService;
import com.score.util.Constant;

@Service
public class SoreServiceImpl implements IScoreService {

	@Autowired
	private TScoreMapper scoreMapper;
	
	@Autowired
	private TStudentMapper studentMapper;

	@Override
	public PageInfo<TScore> getAllScore(TScore score, int limit, int page) {
		// 首先开启PageHelper的分页
		PageHelper.startPage(page, limit);
		// 查询分页信息 调用方式与普通方式一致
		List<TScore> list = scoreMapper.selectAll(score);
		// 生成PageInfo对象
		PageInfo<TScore> pageInfo = new PageInfo<TScore>(list);
		return pageInfo;
	}

	@Override
	public ResultObject<Object> insertScore(TScore score) {

		int count = scoreMapper.countByType(score);
		String type = score.getScoreType();
		boolean flag = true;
		String str="";
		switch (type) {
		case "1":
			if (count >= 16) {
				flag = false;
				str = "该学生的习题成绩已经达到16条";
			}
			break;

		case "2":
			if (count >= 3) {
				flag = false;
				str = "该学生的测验成绩已经达到3条";
			}
			break;
		case "3":
			if (count >= 1) {
				flag = false;
				str = "该学生的考试成绩已经达到1条";
			}
			break;
		default:
			flag = true;
			break;
		}
		// 统一返回
		ResultObject<Object> rs = new ResultObject<Object>();
		if(flag) {
			Integer total = scoreMapper.insert(score);
			if (null == total || 0 == total) {
				rs.setCode(Constant.HASE_RETUEN_CODE);
				rs.setMsg("成绩添加失败");
			} else {
				rs.setCode(Constant.SUCCESS_RETUEN_CODE);
				rs.setMsg("成绩添加成功");
			}
		}else {
			rs.setCode(Constant.HASE_RETUEN_CODE);
			rs.setMsg(str);
		}
		return rs;
	}

	@Override
	public Integer updateScore(TScore score) {
		// TODO Auto-generated method stub
		return scoreMapper.updateByPrimaryKey(score);
	}

	@Override
	public Integer deleteScore(int scoreId) {
		// TODO Auto-generated method stub
		return scoreMapper.deleteByPrimaryKey(scoreId);
	}

	@Override
	public TScore selectScoreById(int scoreId) {
		// TODO Auto-generated method stub
		return scoreMapper.selectByPrimaryKey(scoreId);
	}
	@Override
	public PageInfo<TStudent> getAllFinalScore(TStudent student, int limit, int page) {
		// 首先开启PageHelper的分页
		PageHelper.startPage(page, limit);
		// 查询分页信息 调用方式与普通方式一致
		List<TStudent> list = studentMapper.selectAll(student);
		
		for(TStudent temp:list) {
			double sum=0;
			Map<String,Object> map=studentMapper.selectFinalScore(temp);
			DecimalFormat df = new DecimalFormat("#.00");
			double score1=Double.parseDouble(map.get("score1").toString());
			temp.setScore1(Double.parseDouble(df.format(score1)));
			double score2=Double.parseDouble(map.get("score2").toString());
			temp.setScore2(Double.parseDouble(df.format(score2)));
			double score3=Double.parseDouble(map.get("score3").toString());
			temp.setScore3(Double.parseDouble(df.format(score3)));
			sum=(score1*0.1)+(score2*0.2)+(score3*0.7);
			temp.setSumScore(Double.parseDouble(df.format(sum)));
		}
		// 生成PageInfo对象
		PageInfo<TStudent> pageInfo = new PageInfo<TStudent>(list);
		return pageInfo;
	}

}
