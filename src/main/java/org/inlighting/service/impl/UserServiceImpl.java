package org.inlighting.service.impl;

import org.inlighting.database.UserBean;
import org.inlighting.entity.User;
import org.inlighting.mapper.UserMapper;
import org.inlighting.service.IUserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * auth_state:0-未锟斤拷证锟斤拷1-锟斤拷证通锟斤拷锟斤拷2-锟斤拷证锟斤拷通锟斤拷 服务实现类
 * </p>
 *
 * @author pugaofei
 * @since 2021-01-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Override
	public UserBean getUser(String username) {
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("login_name", username);
		User one = getOne(queryWrapper);
		UserBean bean=new UserBean();
		bean.setUsername(one.getLoginName());
		bean.setPassword("e10adc3949ba59abbe56e057f20f883e");
		bean.setRole("admin");
		bean.setPermission("view,edit");
		return bean;
	}

}
