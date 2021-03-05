package org.inlighting.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.inlighting.entity.po.Labels;
import org.inlighting.entity.po.Nfts;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * auth_state:0-未锟斤拷证锟斤拷1-锟斤拷证通锟斤拷锟斤拷2-锟斤拷证锟斤拷通锟斤拷
 * </p>
 *
 * @author pugaofei
 * @since 2021-01-28
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer           id;

    private String loginName;

    private String password;

    private String salt;

    private String name;

    private String nickName;

    private String idCard;

    private String authState;

    private Integer role;

    private String photo;

    private String email;

    private String phone;

    private Date createDate;

    private String descr;

    private Integer hots;

    private Integer catalogId;
    
    private List<Labels> tags;
    
    private List<Nfts> nfts;
    


}
