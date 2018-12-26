package com.score.service;

import com.github.pagehelper.PageInfo;
import com.score.bean.ResultObject;
import com.score.bean.TScore;
import com.score.bean.TStudent;

public interface IScoreService {
	/**
	 * 模糊查询学生成绩
	 * @param score
	 * @return
	 */
	public PageInfo<TScore> getAllScore(TScore score,int limit,int page);
	
	/**
	 * 增加学生成绩
	 * @param score
	 * @return
	 */
	public ResultObject<Object> insertScore(TScore score);
	
	/**
	 * 更改学生成绩
	 * @param score
	 * @return
	 */
	public Integer updateScore(TScore score);
	
	/**
	 * 删除学生学生成绩
	 * @param score
	 * @return
	 */
	public Integer deleteScore(int scoreId);
	
	/**
	 * 根据编号查询学生成绩
	 * @param score
	 * @return
	 */
	public TScore selectScoreById(int scoreId);
	
	/**
	 * 查询成绩报表
	 * @param student
	 * @param limit
	 * @param page
	 * @return
	 */
	public PageInfo<TStudent> getAllFinalScore(TStudent student, int limit, int page);
}
