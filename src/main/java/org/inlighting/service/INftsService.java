package org.inlighting.service;

import org.inlighting.common.Msg;
import org.inlighting.entity.po.Nfts;
import org.inlighting.entity.query.NftsQuery;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * file_type:1-图片锟斤拷2-锟斤拷频
market_type:1-一锟斤拷锟叫筹拷  2-锟斤拷锟斤拷锟叫筹拷
s 服务类
 * </p>
 *
 * @author pugaofei
 * @since 2021-01-28
 */
public interface INftsService extends IService<Nfts> {

	Msg getDrop(NftsQuery nfts);

	Msg nfts(NftsQuery nfts);

	Msg like(Integer id);

}
