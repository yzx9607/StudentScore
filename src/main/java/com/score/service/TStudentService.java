package com.score.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.score.bean.TStudent;

public interface TStudentService {
	/**
	 *全量查询用户
	 * @return
	 */
	PageInfo<TStudent> getAll(TStudent student,int page,int size);
	
	/**
	 *查询是否存在
	 * @return
	 */
	TStudent selectByNo(int studentNo);
	
	/**
	 * 增加学生信息
	 * @param student
	 * @return
	 */
	Integer addStudent(TStudent student);
	
	
	/**
	 * 修改学生信息
	 * @param student
	 * @return
	 */
	Integer updateStudent(TStudent student);
	
	
	/**
	 * 删除学生信息
	 * @param student
	 * @return
	 */
	Integer deleteStudent(int studentNo);
	/**
	 *全量查询用户为下拉框
	 * @return
	 */
	List <TStudent> selectAllStudent();
	   
    /**
     * 查询学生账号信息
     * @param record
     * @return
     */
    List<TStudent> selectloginStudent(TStudent student);
	
}
