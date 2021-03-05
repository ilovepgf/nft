package org.inlighting.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author pugaofei
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("business_cert")
public class Cert implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @TableId(type = IdType.AUTO)
    private Integer           id;

    private String username;

    private String nftName;

    private Date createDate;

    private String address;

    private String descr;

    private Integer mintId;


}
