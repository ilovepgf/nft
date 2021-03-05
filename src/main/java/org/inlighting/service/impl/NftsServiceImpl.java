package org.inlighting.service.impl;

import java.util.Date;
import java.util.List;

import org.inlighting.common.DateUtils;
import org.inlighting.common.Msg;
import org.inlighting.common.StringUtils;
import org.inlighting.entity.po.Nfts;
import org.inlighting.entity.query.NftsQuery;
import org.inlighting.mapper.NftsMapper;
import org.inlighting.service.INftsService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * file_type:1-图片锟斤拷2-锟斤拷频
market_type:1-一锟斤拷锟叫筹拷  2-锟斤拷锟斤拷锟叫筹拷
s 服务实现类
 * </p>
 *
 * @author pugaofei
 * @since 2021-01-28
 */
@Service
public class NftsServiceImpl extends ServiceImpl<NftsMapper, Nfts> implements INftsService {

	@Override
	public Msg getDrop(NftsQuery nfts) {
		//
		QueryWrapper<Nfts> queryWrapper=new QueryWrapper<>();
		queryWrapper.gt("drop_date", new Date());
		queryWrapper.like(StringUtils.isNotEmpty(nfts.getName()), "name", nfts.getName());
		List<Nfts> list = list(queryWrapper);
		return Msg.returnObj(true, "", "", list);
	}

	@Override
	public Msg nfts(NftsQuery query) {
		QueryWrapper<Nfts> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq(query.getCatalogId()!=null,"catalog_id", query.getCatalogId());
		queryWrapper.like(StringUtils.isNotEmpty(query.getName()), "name", query.getName());
		Page page = new Page(query.getPage(), query.getRows());
		IPage<Nfts> pages = page(page, queryWrapper);
		return Msg.returnObj(true, "", "", pages);
	}

	@Override
	public Msg like(Integer id) {
		Nfts nfts = getById(id);
		UpdateWrapper<Nfts> updateWrapper=new UpdateWrapper<>();
		updateWrapper.eq("id", id);
		updateWrapper.set("like_num", nfts.getLikeNum()+1);
		boolean update = update(updateWrapper);
		return Msg.returnObj(update, "", "失败", null);
	}

}
