package org.inlighting.entity.query;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import org.inlighting.common.entity.QueryEntity;

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
public class LabelsQuery extends QueryEntity {

    private static final long serialVersionUID = 1L;
    
    private Integer           id;

    private String name;

    private Integer catalogId;


}
