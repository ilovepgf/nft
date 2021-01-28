package org.inlighting.config;


import org.inlighting.common.Msg;
import org.inlighting.common.exception.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Msg errorHander(Exception e) {
    	e.printStackTrace();
    	String s=e.toString();
    	if(e instanceof UnauthorizedException) {
    		s=e.getMessage();
    	}
        return Msg.returnObj(false, "", s, null);
    }

}