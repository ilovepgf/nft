package org.inlighting.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * auth_state:0-未锟斤拷证锟斤拷1-锟斤拷证通锟斤拷锟斤拷2-锟斤拷证锟斤拷通锟斤拷
 * </p>
 *
 * @author pugaofei
 * @since 2021-01-19
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
public class User  {

    private static final long serialVersionUID = 1L;
    
    @TableId
    private Integer id;

    /**
     * 登录名称
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 姓名
     */
    private String name;

    /**
     * 角色Id
     */
    private Integer role;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 账号状态
     */
    private String authState;

    /**
     * 社会信用代码证路径
     */
    private String photo;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 社会信用代码
     */
    private String orgCode;

    /**
     * 创建账号时间
     */
    private LocalDateTime createDate;

    /**
     * 是否是管理员创建
     */
    private String isAdminCreate;

    /**
     * 账号创建者登录名称
     */
    private String creator;

    /**
     * 是否上线
     */
    private String isOnline;

    /**
     * 运营相关人员部门
     */
    private String adminDepartName;


}
