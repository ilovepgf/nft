package org.inlighting.entity;

import java.math.BigDecimal;

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
 * status锟斤拷1-锟斤拷锟阶成癸拷
market_type锟斤拷1-一锟斤拷锟叫筹拷锟斤拷2-锟斤拷锟斤拷锟叫筹拷
 * </p>
 *
 * @author pugaofei
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("business_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @TableId(type = IdType.AUTO)
    private Integer           id;

    private Integer nftId;

    private String buyAddress;

    private String sellAddress;

    private BigDecimal price;

    private Integer gas;

    private String status;

    private Integer buyUserId;

    private Integer sellUserId;

    private Date createDate;

    private String marketType;


}
