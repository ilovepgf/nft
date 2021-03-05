package org.inlighting.service.impl;

import java.util.List;

import org.inlighting.common.Msg;
import org.inlighting.common.StringUtils;
import org.inlighting.entity.po.Labels;
import org.inlighting.entity.query.LabelsQuery;
import org.inlighting.mapper.LabelsMapper;
import org.inlighting.service.ILabelsService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pugaofei
 * @since 2021-01-28
 */
@Service
public class LabelsServiceImpl extends ServiceImpl<LabelsMapper, Labels> implements ILabelsService {

	@Override
	public Msg tags(LabelsQuery query) {
		// 
		QueryWrapper<Labels> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq(query.getCatalogId()!=null,"catalog_id", query.getCatalogId());
		queryWrapper.like(StringUtils.isNotEmpty(query.getName()),"name", query.getName());
		List<Labels> list = list(queryWrapper);
		return Msg.returnObj(true, "", "", list);
	}

}
