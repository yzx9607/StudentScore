package com.score.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.score.bean.User;

/**
 * 用户服务接口
 * @author Yang
 *
 */
public interface IUserService {	
	/**
	 *全量查询用户
	 * @return
	 */
	List<User> getUser(User user);
	
	/**
	 * 分页查询用户
	 * @param page 页码
	 * @param size 数量
	 * @return
	 */
	PageInfo<User> getUsersLimit(int page,int size);
}
