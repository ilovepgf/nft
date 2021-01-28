package org.inlighting.common;


import java.security.SecureRandom;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;

import cn.hutool.core.util.IdUtil;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * 
 */

public class IdGen {

    private static SecureRandom random = new SecureRandom();

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return Math.abs((long) random.nextInt());
    }
    
    /**
     * Twitter Id生成
     * @return
     */
    public static String nextIdStr() {
        return IdUtil.createSnowflake(1, 1).nextIdStr();
    }
    
    public static String randomStr() {
    	return RandomStringUtils.randomAlphabetic(8).toLowerCase();
    }

    public static void main(String[] args) {
        		System.out.println(UUID.randomUUID().toString());
        //		System.out.println(IdGen.uuid().length());
    	String out_trade_no=randomLong()+"";
    	System.out.println(out_trade_no);
    }

}
