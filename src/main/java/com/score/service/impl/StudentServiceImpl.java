package com.score.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.score.bean.TStudent;
import com.score.dao.TStudentMapper;
import com.score.service.TStudentService;

@Service // 标识为服务类
public class StudentServiceImpl implements TStudentService {
	@Autowired
	private TStudentMapper studentMapper;

	@Override
	public PageInfo<TStudent> getAll(TStudent student,int page,int size) {
		// 首先开启PageHelper的分页
		PageHelper.startPage(page, size);
		// 查询分页信息 调用方式与普通方式一致
		List<TStudent> list = studentMapper.selectAll(student);
		//生成PageInfo对象
		PageInfo<TStudent> pageInfo=new PageInfo<TStudent>(list);		
		return pageInfo;
	}
	/**
	 *查询是否存在
	 * @return
	 */
	public TStudent selectByNo(int studentNo) {
		TStudent student=studentMapper.selectByPrimaryKey(studentNo);
		return student;
	}
	
	/**
	 * 增加学生信息
	 * @param student
	 * @return
	 */
	public Integer addStudent(TStudent student) {
		Integer total=studentMapper.insert(student);
		return total;
	}
	/**
	 * 修改学生信息
	 * @param student
	 * @return
	 */
	public Integer updateStudent(TStudent student) {
		Integer total=studentMapper.updateByPrimaryKey(student);
		return total;
	}
	
	
	/**
	 * 删除学生信息
	 * @param student
	 * @return
	 */
	public Integer deleteStudent(int studentNo) {
		Integer total=studentMapper.deleteByPrimaryKey(studentNo);
		return total;
	}
	/**
	 *全量查询用户为下拉框
	 * @return
	 */
	public List<TStudent> selectAllStudent(){
		return studentMapper.selectAll(null);
	}
	   
    /**
     * 查询学生账号信息
     * @param record
     * @return
     */
    public List<TStudent> selectloginStudent(TStudent student){
    	return studentMapper.selectloginStudent(student);
    }
}
