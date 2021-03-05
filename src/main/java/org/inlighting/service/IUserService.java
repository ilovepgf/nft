package org.inlighting.service;

import org.inlighting.common.Msg;
import org.inlighting.common.entity.UserBean;
import org.inlighting.entity.po.User;
import org.inlighting.entity.query.UserQuery;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * auth_state:0-未锟斤拷证锟斤拷1-锟斤拷证通锟斤拷锟斤拷2-锟斤拷证锟斤拷通锟斤拷 服务类
 * </p>
 *
 * @author pugaofei
 * @since 2021-01-19
 */
public interface IUserService extends IService<User> {

	UserBean getUser(String username);

	Msg arties(UserQuery query);

	Msg hots();

	Msg detail(Integer id);

	Msg relate(Integer id);

}
