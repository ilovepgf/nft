package org.inlighting.service.impl;

import org.inlighting.entity.po.Order;
import org.inlighting.mapper.OrderMapper;
import org.inlighting.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * status锟斤拷1-锟斤拷锟阶成癸拷
market_type锟斤拷1-一锟斤拷锟叫筹拷锟斤拷2-锟斤拷锟斤拷锟叫筹拷 服务实现类
 * </p>
 *
 * @author pugaofei
 * @since 2021-01-28
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
