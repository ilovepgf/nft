package org.inlighting.common;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;

import cn.hutool.core.util.IdcardUtil;
import lombok.extern.log4j.Log4j;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final char   SEPARATOR    = '_';
    private static final String CHARSET_NAME = "UTF-8";

    /**
     * 转换为字节数组
     * 
     * @param str
     * @return
     */
    public static byte[] getBytes(String str) {
        if (str != null) {
            try {
                return str.getBytes(CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 转换为字节数组
     * 
     * @param str
     * @return
     */
    public static String toString(byte[] bytes) {
        try {
            return new String(bytes, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return EMPTY;
        }
    }

    /**
     * 是否包含字符串
     * 
     * @param str
     *            验证字符串
     * @param strs
     *            字符串组
     * @return 包含返回true
     */
    public static boolean inString(String str, String... strs) {
        if (str != null) {
            for (String s : strs) {
                if (str.equals(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 替换掉HTML标签方法
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }

    /**
     * 替换为手机识别的HTML，去掉样式及属性，保留回车。
     * 
     * @param html
     * @return
     */
    public static String replaceMobileHtml(String html) {
        if (html == null) {
            return "";
        }
        return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
    }

    /**
     * 缩略字符串（不区分中英文字符）
     * 
     * @param str
     *            目标字符串
     * @param length
     *            截取长度
     * @return
     */
    public static String abbr(String str, int length) {
        if (str == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
                currentLength += String.valueOf(c).getBytes("GBK").length;
                if (currentLength <= length - 3) {
                    sb.append(c);
                } else {
                    sb.append("...");
                    break;
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 转换为Double类型
     */
    public static Double toDouble(Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * 转换为Float类型
     */
    public static Float toFloat(Object val) {
        return toDouble(val).floatValue();
    }

    /**
     * 转换为Long类型
     */
    public static Long toLong(Object val) {
        return toDouble(val).longValue();
    }

    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(Object val) {
        return toLong(val).intValue();
    }

    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (StringUtils.isNotBlank(ip) && ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }

        return ip;
    }

    /**
     * 驼峰命名法工具
     * 
     * @return toCamelCase("hello_world") == "helloWorld"
     *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
     *         toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     * 
     * @return toCamelCase("hello_world") == "helloWorld"
     *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
     *         toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 驼峰命名法工具
     * 
     * @return toCamelCase("hello_world") == "helloWorld"
     *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
     *         toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 如果不为空，则设置值
     * 
     * @param target
     * @param source
     */
    public static void setValueIfNotBlank(String target, String source) {
        if (isNotBlank(source)) {
            target = source;
        }
    }

    /**
     * 转换为JS获取对象值，生成三目运算返回结果
     * 
     * @param objectString
     *            对象串 例如：row.user.id
     *            返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
     */
    public static String jsGetVal(String objectString) {
        StringBuilder result = new StringBuilder();
        StringBuilder val = new StringBuilder();
        String[] vals = split(objectString, ".");
        for (int i = 0; i < vals.length; i++) {
            val.append("." + vals[i]);
            result.append("!" + (val.substring(1)) + "?'':");
        }
        result.append(val.substring(1));
        return result.toString();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @param scale
     *            表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 添加字符空格 例如str ="ss" len=3 "ss "
     * 
     * @param str
     *            操作的字符串
     * @param len
     *            操作的记录长度
     * @return 操作后的字符串
     */
    public static String addStringTrim(String str, int len) {
        // 得到字符的长度
        int strlen = str.length();
        int cha = len - strlen;
        // 添加空格占位
        for (int i = 0; i < cha; i++) {
            str += " ";
        }
        return str;
    }

    /**
     * 给数字不够长度的前面添加0占位 如：str="34" len=3 返回034
     * 
     * @param str
     *            操作字符串
     * @param len
     *            操作字符串的长度
     * @return 返回处理好的字符串
     */
    public static String addNum0(String str, int len) {
        // 得到字符长度
        int strlen = str.length();
        String s = "";
        int cha = len - strlen;
        for (int i = 0; i < cha; i++) {
            s += "0";
        }
        str = s + str;
        return str;
    }

    /**
     * 
     * 根据输入的字符串，里面有空格和tab键 通过本方法可以把数据排列成以空格隔开的数据。 <br>
     * 例子： "d fdf d" 排列好的为："d fdf d"
     * 
     * @param str
     *            字符串 里面有空格和tab键
     * @return 排列好的字符串
     */
    public static String characterCollation(String str) {

        // 要处理的字符串
        String name = str;
        // 临时的变量
        String data = "";
        // 标记
        boolean flag = false;
        // 得到数据文件的长度
        int length = name.length();
        char[] c = name.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if ((c[i] + "").equals(" ")) {
                if (!flag) {
                    flag = true;
                    data += c[i] + "";
                } else {
                    flag = true;
                }
            } else if ((c[i] + "").equals("\t")) {
                if (!flag) {
                    flag = true;
                    data += " ";
                } else {
                    flag = true;
                }
            } else {
                flag = false;
                data += c[i] + "";
            }
        }
        return data;
    }

    /**
     * 检查字符串是否不是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
     * 
     * <pre>
     * StringUtil.isBlank(null)      = false
     * StringUtil.isBlank("")        = false
     * StringUtil.isBlank(" ")       = false
     * StringUtil.isBlank("bob")     = true
     * StringUtil.isBlank("  bob  ") = true
     * </pre>
     *
     * @param str
     *            要检查的字符串
     *
     * @return 如果为空白, 则返回<code>true</code>
     */
    public static boolean isNotBlank(String str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断字符是否属于
     * 
     * @Title: isNumber
     * @author: pugaofei
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        // 采用正则表达式的方式来判断一个字符串是否为数字，这种方式判断面比较全
        // 可以判断正负、整数小数
        boolean isInt = Pattern.compile("^-?[1-9]\\d*$").matcher(str).find();
        boolean isDouble = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$")
            .matcher(str).find();
        return isInt || isDouble;
    }

    /**
     * 
     * @Title: UserPattern 正则验证
     * @Description: TODO
     * @param reg
     *            正则表达式
     * @param passWord
     *            验证的字符串
     * @param message
     *            验证失败返回的信息
     * @return
     */
    public static Msg checkStr(String reg, String passWord, String message) {
        Msg msg = new Msg();
        Pattern compile = Pattern.compile(reg);
        Matcher matcher = compile.matcher(passWord);
        boolean matches = matcher.matches();
        if (matches) {// 成功
            msg.setSuccess(true);
        } else {// 失败
            msg.setSuccess(false);
            msg.setMsg(message);
        }
        return msg;
    }

    /**
     * sql 字段查询 处理
     * 
     * @Title: sqlEscapeStr
     * @author: ganzenghui
     * @param str
     * @return
     */
    public static String sqlEscapeStr(String str) {
        if (str == null) {
            return "";
        }
        str = str.replace("|", "||").replace("%", "|%").replace("_", "|_");
        return str;
    }

    /**
     * 下划线转驼峰法
     * 
     * @Title: underline2Camel
     * @author: ganzenghui
     * @param line
     * @param smallCamel
     * @return
     */
    public static String underline2Camel(String line, boolean smallCamel) {
        if (line == null || "".equals(line)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(smallCamel && matcher.start() == 0 ? Character.toLowerCase(word.charAt(0))
                : Character.toUpperCase(word.charAt(0)));
            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * 驼峰法转下划线
     * 
     * @Title: camel2Underline
     * @author: ganzenghui
     * @param line
     * @param isUpperCase
     *            是否大写
     * @return
     */
    public static String camel2Underline(String line, boolean isUpperCase) {
        if (line == null || "".equals(line)) {
            return "";
        }
        line = String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(isUpperCase ? word.toUpperCase() : word.toLowerCase());
            sb.append(matcher.end() == line.length() ? "" : "_");
        }
        return sb.toString();
    }

    /**
     * 首字母转小写
     * 
     * @Title: toLowerCaseFirst
     * @author: ganzenghui
     * @param str
     * @return
     */
    public static String toLowerCaseFirst(String str) {
        if (str.length() == 0) {
            return str;
        }
        return new StringBuilder().append(Character.toLowerCase(str.charAt(0)))
            .append(str.substring(1)).toString();
    }

    /**
     * 首字母转大写
     * 
     * @Title: toUpperCaseFirst
     * @author: ganzenghui
     * @param str
     * @return
     */
    public static String toUpperCaseFirst(String str) {
        if (str.length() == 0) {
            return str;
        }
        return new StringBuilder().append(Character.toUpperCase(str.charAt(0)))
            .append(str.substring(1)).toString();
    }

    /**
     * 保留小数
     * 
     * @Title: retainDecimal
     * @author: pugaofei
     * @param v1
     *            数值
     * @param scale
     *            保留几位小数
     * @return
     */
    public static double retainDecimal(double v1, int scale) {

        BigDecimal bg = BigDecimal.valueOf(v1);
        double f1 = bg.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }
    
    
    /**
     * 处理表名与数据库关键字相同的问题
     * @param dbType
     * @param name
     * @return
     */
    public static String dealTableForSql(String dbType, String name) {
        int dotIndex = name.indexOf(".");
        if ("oracle".equalsIgnoreCase(dbType) && dotIndex != -1) {
            String dbName = name.substring(0, dotIndex);
            String tableName = name.substring(dotIndex + 1);

            String ret = dealFieldForSql(dbType, dbName) + "." + dealFieldForSql(dbType, tableName);
            return ret;
        }
        return dealFieldForSql(dbType, name);
    }

    /**
     * 处理字段名与数据库关键字相同的问题
     * 
     * @param dbType
     *            数据库类型
     * @param name
     *            字段名or表名
     * @return
     */
    public static String dealFieldForSql(String dbType, String name) {
        if (isBlank(dbType)) {
            return name;
        }
        switch (dbType.toLowerCase()) {
            case "oracle":
                name = "\"" + name + "\"";
                break;
            case "mysql":
                name = "`" + name + "`";
                break;
            case "mssql":
                name = "[" + name + "]";
                break;
            case "postgresql":
                name = "\"" + name + "\"";
                break;
            default:
                break;
        }
        return name;
    }

    /**
     * 处理用作select的字段名
     * @param dbType
     * @param fieldType
     * @param name
     * @return
     */
    public static String dealField4Select(String dbType, String fieldType, String name) {
        String alias = "";
        if (isBlank(dbType)) {
            return name;
        }
        switch (dbType.toLowerCase()) {
            case "oracle":
                name = "\"" + name + "\"";
                alias = name.toUpperCase();
                if ("date".equalsIgnoreCase(fieldType)) {
                    name = "to_char(" + name + ", 'yyyy-MM-dd HH24:mi:ss')";
                }
                
                break;
            case "mysql":
                name = "`" + name + "`";
                alias = name.toUpperCase();
                if ("date".equalsIgnoreCase(fieldType)) {
                    name = "DATE_FORMAT(" + name + ", '%Y-%m-%d %H:%i:%s')";
                }
                if("string".equalsIgnoreCase(fieldType)) {
                	name="CONCAT("+name+",'')";//数字类型不能直接转成number的，默认转string
                }
                break;
            case "mssql":
                name = "[" + name + "]";
                alias = name.toUpperCase();
                if ("date".equalsIgnoreCase(fieldType)) {
                    name = "CONVERT(varchar(100)," + name + ", 20)";
                }
                
                break;
            case "postgresql": 
            	 name = "\"" + name + "\"";
                 alias = name.toUpperCase();
            default:
                break;
        }
        name = name + " as " + alias;
        return name;
    }


    /**
     * 根据数据库类型添加分页信息
     * 
     * @param dbType
     * @param sql
     * @return
     */
    public static String addPageLimit(String dbType, String sql, int pageIndex, int pageSize,String pageField) {
        String ret = "";
        if (isBlank(dbType)) {
            return ret;
        }
        int fromIndex = sql.toLowerCase().indexOf("from");
        String selectPart = sql.substring(0, fromIndex);
        String conditionPart = sql.substring(fromIndex);

        long start = (pageIndex - 1) * pageSize;
        long end = start+pageSize;
        String rowCondiStr = sql.toLowerCase().indexOf("where") < 0 ? "where" : "and";
        if ("oracle".equalsIgnoreCase(dbType)) {
            start += 1;
            ret = selectPart + "from(select *  " + conditionPart + "  " + rowCondiStr
                  + " rownum between " + start + " and " + end + ")";
        } else if ("mySql".equalsIgnoreCase(dbType)) {
            ret = selectPart + "from(select *  " + conditionPart + "  limit " + start + "," + pageSize
                  + ") t";
        } else if ("mssql".equalsIgnoreCase(dbType)) {
            start += 1;
            ret = selectPart + "from(select row_number() over(ORDER BY "+pageField+") rownumber,*  " + conditionPart + ") a  " 
                  + " where rownumber> " + start + " and rownumber<=" + end + "";
        }else if ("postgresql".equalsIgnoreCase(dbType)) {
            ret = selectPart + "from(select *  " + conditionPart + "  limit " + pageSize + " offset " + start
                    + ") t";
          } 
        return ret;
    }
    
    /**
     * 普通sql转统计sql
     * @param sql
     * @return
     */
    public static String sql2countSql( String sql){
    	String ret = "";
        if (isBlank(sql)) {
            return ret;
        }
        int fromIndex = sql.toLowerCase().indexOf("from");
        String selectPart = sql.substring(0, fromIndex);
        String conditionPart = sql.substring(fromIndex);
        ret="select count(*) "+conditionPart;
        return ret;
    }
    
    

    public static boolean isPhone(String phone) {
        String check = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(phone);
        return matcher.matches();
    }

    public static boolean isNum(String key) {
        Pattern digit = Pattern.compile("\\d+");
        return digit.matcher(key).matches();
    }

    /**
     * 数字的值大小范围
     * @Title: isNumValue
     * @author: pugaofei
     * @param key
     * @param min
     * @param max
     * @return
     */
    public static boolean isNumValue(String key, int min, int max) {
        boolean flag = false;
        if (StringUtils.isNotBlank(key)) {
            Pattern digit = Pattern.compile("\\d+");
            flag = digit.matcher(key).matches();
            if (flag) {
                int num = Integer.parseInt(key);
                if (num < min || num > max) {
                    flag = false;
                } else {
                    flag = true;
                }
            }
        }
        return flag;
    }

    /**
     * 字符长度校验
     * @Title: strLenValid
     * @author: pugaofei
     * @param key
     * @param min
     * @param max
     * @return
     */
    public static boolean strLenValid(String key, int min, int max) {
        boolean flag = false;
        if (StringUtils.isNotBlank(key)) {
            int len = key.length();
            if (len < min || len > max) {
                flag = false;
            } else {
                flag = true;
            }
        }
        return flag;
    }

    public static boolean isIdCard(String cardNo) {
    	return IdcardUtil.isValidCard(cardNo);
    }

    /**
     * 判断字符串中是否包含中文
     * @param str
     * 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 表的关键字处理,这个方法没有考虑了用户名.表名情况
     * @Title: tableHandler
     * @author: pugaofei
     * @param table
     * @param type 1 oracle 2 mysql 3 sqlserver 4.postgresql
     * @return
     */
    public static String tableHandler(String keyword, String type) {

        if (type.equals("1")) {
            keyword = "\"" + keyword + "\"";
        } else if (type.equals("2")) {
            keyword = "`" + keyword + "`";
        } else if (type.equals("3")) {
            keyword = "[" + keyword + "]";
        } else if (type.equals("4")) {
            keyword = "\"" + keyword + "\"";
        }
        return keyword;
    }

    /**
     * 过滤sql注入的方法 针对 like方式
     * @Title: filtrateLikeSql
     * @author: pugaofei
     * @param value
     * @return
     */
    public static String filtrateLikeSql(String value) {
        if (null != value) {
            String newValue = "";
            newValue = value.replaceAll("\\\\", "\\\\\\\\");
            newValue = newValue.replaceAll("'", "\\\\'");
            newValue = newValue.replaceAll("_", "\\\\_");
            newValue = newValue.replaceAll("\"", "\\\\\"");
            newValue = newValue.replaceAll("%", "\\\\%");
            newValue = newValue.replaceAll("&lt;", "<");
            newValue = newValue.replaceAll("&gt;", ">");

            return newValue;
        }
        return value;
    }

    public static boolean sqlTSString(String value) {
        boolean flag = false;
        List<String> list = new ArrayList();
        list.add("\\\\");
        list.add("'");
        list.add(";");
        //list.add("_");
        list.add("\"");
        list.add("%");
        list.add("&lt;");
        list.add("&gt;");
        list.add("<");
        list.add(">");
        list.add("\\");
        list.add("//");

        for (String str : list) {
            if (value.indexOf(str) >= 0) {
                flag = true;
                break;
            }
        }

        return flag;

    }

    /**
     * 过滤sql注入的方法 针对 非like方式
     * @Title: filtrateNotLikeSql
     * @author: pugaofei
     * @param value
     * @return
     */
    public static String filtrateNotLikeSql(String value) {
        if (null != value) {
            String newValue = "";
            newValue = value.replaceAll("\\\\", "\\\\\\\\");
            newValue = newValue.replaceAll("\"", "\\\\\"");
            return newValue;
        }
        return value;
    }

    /**
     * 字符去空格
     * @Title: StringTrim
     * @author: pugaofei
     * @param obj
     * @return
     */
    public static String StringTrim(String str) {
        if (str != null) {
            return str.trim();
        } else {
            return str;
        }
    }

    /**
     * 判断日期
     * @Title: isDate
     * @author: pugaofei
     * @param str
     * @return
     */
    public static boolean isDate(String str) {
        boolean flag = false;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (str.length() == 19) {
                format.setLenient(true);
                Date date = format.parse(str);
                flag = true;
            }
            return flag;
        } catch (Exception e) {
            flag = false;
        }
        return flag;

    }

    public static boolean judgeDecimal(String str, int len) {
        boolean flag = isNumber(str);
        if (flag) {
            String arr[] = str.split("\\.");
            if (arr.length == 1) {
                flag = true;
            } else if (arr.length == 2) {

                if (arr[1].length() > len) {
                    flag = false;
                } else {
                    flag = true;
                }
            } else {
                flag = false;
            }
        }

        return flag;

    }

  

    public static void main(String[] args) {
        //double d = StringUtils.retainDecimal(34.523, 0);
        //System.out.println("2019-09-09 12:12:12".length());
        //System.out.println(StringUtils.isDate("2019-09-9 12:12:12"));
        //System.out.println(JsoupUtil.clean("--and--"));

        //System.out.println("sdfsd.dfd".judgeDecimal("\\.").length);

        //System.out.println(judgeDecimal("123.23", 2));

        //System.out.println(StringUtils.sqlTSString("dfdfdf\\ // ;"));
    }
}
