package org.inlighting.entity;

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
 * status锟斤拷1-未锟斤拷锟斤拷2-锟窖讹拷
type:1-锟斤拷锟斤拷锟斤拷息锟斤拷2-系统通知锟斤拷3-锟斤拷锟斤拷锟斤拷息
 * </p>
 *
 * @author pugaofei
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("business_message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @TableId(type = IdType.AUTO)
    private Integer           id;

    private String type;

    private Integer userId;

    private Date createDate;

    private String status;

    private String content;

    private String descr;


}
