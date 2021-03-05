package org.inlighting.service;

import org.inlighting.common.Msg;
import org.inlighting.entity.po.Catalog;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pugaofei
 * @since 2021-01-28
 */
public interface ICatalogService extends IService<Catalog> {

	Msg catalogs();

}
