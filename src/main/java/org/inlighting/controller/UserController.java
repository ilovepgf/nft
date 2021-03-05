package org.inlighting.controller;


import org.inlighting.common.Msg;
import org.inlighting.entity.po.User;
import org.inlighting.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * auth_state:0-未锟斤拷证锟斤拷1-锟斤拷证通锟斤拷锟斤拷2-锟斤拷证锟斤拷通锟斤拷 前端控制器
 * </p>
 *
 * @author pugaofei
 * @since 2021-01-19
 */
@RestController
@RequestMapping("/user")
public class UserController  {
	
	@Autowired
	IUserService userService;

	@PostMapping("/detail")
    public Msg detail(User po) {
		Msg msg=userService.detail(po.getId());
		return msg;
	}
	
	@PostMapping("/relate")
    public Msg relate(User po) {
		Msg msg=userService.relate(po.getId());
		return msg;
	}
}
