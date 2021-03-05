package org.inlighting.service.impl;

import java.util.List;

import org.inlighting.common.Constant;
import org.inlighting.common.Msg;
import org.inlighting.common.entity.UserBean;
import org.inlighting.entity.po.Labels;
import org.inlighting.entity.po.Nfts;
import org.inlighting.entity.po.User;
import org.inlighting.entity.query.UserQuery;
import org.inlighting.entity.vo.NftsVO;
import org.inlighting.entity.vo.UserVO;
import org.inlighting.mapper.NftsMapper;
import org.inlighting.mapper.UserMapper;
import org.inlighting.service.IUserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	UserMapper userMapper;
	@Autowired
	NftsMapper nftsMapper;

	@Override
	public UserBean getUser(String username) {
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("login_name", username);
		User one = getOne(queryWrapper);
		UserBean bean=new UserBean();
		bean.setUsername(one.getLoginName());
		bean.setPassword(one.getPassword());
		bean.setRole(one.getRole().toString());
		bean.setPermission("view,edit");
		return bean;
	}

	@Override
	public Msg arties(UserQuery query) {
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("role", '2');
		queryWrapper.eq("auth_state", '1');
		List<User> list = list(queryWrapper);
		//每个艺术家作品数量 后台定时统计
		return Msg.returnObj(true, "", "", list);
	}

	@Override
	public Msg hots() {
		//
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("role", '2');
		queryWrapper.eq("auth_state", '1');
		queryWrapper.orderByDesc("hots");
		queryWrapper.last(" limit 0,10");
		List<User> list = list(queryWrapper);
		return Msg.returnObj(true, "", "", list);
	}

	@Override
	public Msg detail(Integer id) {
		//
		User user = getById(id);
		//标签
		List<Labels> labels=userMapper.getLabelsByUserId(id);
		//作品
		QueryWrapper<Nfts> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("creator_id", id);
		List<Nfts> list = nftsMapper.selectList(queryWrapper);
		UserVO vo = Constant.dozerBeanMapper.map(user, UserVO.class);
		vo.setTags(labels);
		vo.setNfts(list);
		return Msg.returnObj(true, "", "", vo);
	}

	@Override
	public Msg relate(Integer id) {
		
		return null;
	}

}
