package org.inlighting.service.impl;

import java.util.List;

import org.inlighting.common.Msg;
import org.inlighting.entity.po.Catalog;
import org.inlighting.mapper.CatalogMapper;
import org.inlighting.service.ICatalogService;
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
public class CatalogServiceImpl extends ServiceImpl<CatalogMapper, Catalog> implements ICatalogService {

	@Override
	public Msg catalogs() {
		List<Catalog> list = list();
		return Msg.returnObj(true, "", "", list);
	}

}
