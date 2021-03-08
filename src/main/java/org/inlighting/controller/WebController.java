package org.inlighting.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.inlighting.common.JWTUtil;
import org.inlighting.common.Msg;
import org.inlighting.common.UserUtils;
import org.inlighting.common.entity.UserBean;
import org.inlighting.common.exception.UnauthorizedException;
import org.inlighting.entity.po.User;
import org.inlighting.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {

    private static final Logger LOGGER = LogManager.getLogger(WebController.class);

    @Autowired
    private IUserService userService;


    @PostMapping("/login")
    public Msg login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
    	UserBean userBean = userService.getUser(username);
    	Object result = new SimpleHash("md5", password, ByteSource.Util.bytes(""), 1);
        if (userBean.getPassword().equals(result.toString())) {
            return Msg.returnObj(true, "Login success", "", JWTUtil.sign(username, password));
        } else {
            throw new UnauthorizedException();
        }
    }

    @GetMapping("/article")
    public Msg article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
        	User user = UserUtils.getUser();
            return Msg.returnObj(true, "You are already logged in", "", null);
        } else {
            return Msg.returnObj(true, "You are guest", "", null);
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public Msg requireAuth() {
        return Msg.returnObj(true, "You are authenticated", "", null);
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public Msg requireRole() {
        return Msg.returnObj(true, "You are visiting require_role", "", null);
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public Msg requirePermission() {
        return Msg.returnObj(true, "You are visiting permission require edit,view", "", null);
    }

//    @RequestMapping(path = "/401")
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public Msg unauthorized() {
//        return Msg.returnObj(false, "", "Unauthorized", null);
//    }
}
