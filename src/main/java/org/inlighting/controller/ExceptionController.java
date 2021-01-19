package org.inlighting.controller;

import org.apache.shiro.ShiroException;
import org.inlighting.bean.Msg;
import org.inlighting.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {

    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public Msg handle401(ShiroException e) {
        return Msg.returnObj(false, "", e.getMessage(), null);
    }

    // 捕捉UnauthorizedException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public Msg handle401() {
        return Msg.returnObj(false, "", "Unauthorized", null);
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Msg globalException(HttpServletRequest request, Throwable ex) {
        return Msg.returnObj(false, "", ex.getMessage(), null);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}

