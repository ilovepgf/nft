package org.inlighting.common;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.inlighting.entity.po.User;

public class UserUtils {

	public static User getUser() {
		Subject subject = SecurityUtils.getSubject();
		String name = JWTUtil.getUsername(subject.getPrincipal().toString());
		User user =new User();
		user.setLoginName(name);
		return user;
	}
}
