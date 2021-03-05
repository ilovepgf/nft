package org.inlighting.common;

import java.security.Principal;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.inlighting.entity.po.User;

public class UserUtils {

	public User getUser() {
		Subject subject = SecurityUtils.getSubject();
		Principal principal = (Principal)subject.getPrincipal();
		return null;
	}
}
