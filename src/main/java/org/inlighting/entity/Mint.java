package org.inlighting.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * status锟斤拷1-锟斤拷锟斤拷耍锟?-锟斤拷锟酵?拷锟斤拷锟?-锟斤拷瞬锟酵?拷锟斤拷锟?-锟斤拷锟较架ｏ拷5-锟斤拷锟斤拷锟斤拷锟斤拷6-锟斤拷锟铰硷拷

t
 * </p>
 *
 * @author pugaofei
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("business_mint")
public class Mint implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @TableId(type = IdType.AUTO)
    private Integer           id;

    private String name;

    private String url;

    private BigDecimal price;

    private String status;

    private String descr;

    private Integer totalNum;

    private Integer availableNum;


}
