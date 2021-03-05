package org.inlighting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.inlighting.entity.po.Labels;
import org.inlighting.entity.po.User;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * auth_state:0-未锟斤拷证锟斤拷1-锟斤拷证通锟斤拷锟斤拷2-锟斤拷证锟斤拷通锟斤拷 Mapper 接口
 * </p>
 *
 * @author pugaofei
 * @since 2021-01-19
 */
public interface UserMapper extends BaseMapper<User> {

	@Select("SELECT bl.* FROM business_user_label bul,business_labels bl where bul.label_id=bl.id and bul.user_id =#{id}")
	List<Labels> getLabelsByUserId(Integer id);

}
