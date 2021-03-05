package org.inlighting.entity.vo;

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
 * file_type:1-图片锟斤拷2-锟斤拷频
market_type:1-一锟斤拷锟叫筹拷  2-锟斤拷锟斤拷锟叫筹拷
s
 * </p>
 *
 * @author pugaofei
 * @since 2021-01-28
 */
@Data
public class NftsVO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer           id;

    private String name;

    private BigDecimal price;

    private Date createDate;

    /**
     * 锟较硷拷时锟斤拷
     */
    private Date dropDate;

    private String url;

    private Integer likeNum;

    private Integer collectNum;

    private Integer userId;

    private Integer creatorId;

    private Integer catalogId;

    /**
     * 锟侥硷拷锟斤拷锟斤拷
     */
    private String fileType;

    /**
     * 锟叫筹拷锟斤拷锟斤拷
     */
    private String marketType;

    private Integer mintId;

    private Integer tokenId;

    /**
     * 状态
     */
    private String status;

    private String descr;


}
