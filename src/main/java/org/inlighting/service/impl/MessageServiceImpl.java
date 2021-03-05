package org.inlighting.service.impl;

import org.inlighting.entity.po.Message;
import org.inlighting.mapper.MessageMapper;
import org.inlighting.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * status锟斤拷1-未锟斤拷锟斤拷2-锟窖讹拷
type:1-锟斤拷锟斤拷锟斤拷息锟斤拷2-系统通知锟斤拷3-锟斤拷锟斤拷锟斤拷息 服务实现类
 * </p>
 *
 * @author pugaofei
 * @since 2021-01-28
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
