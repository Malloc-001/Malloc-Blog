package com.xd.service.impl;

import com.xd.entity.TMessage;
import com.xd.mapper.TMessageMapper;
import com.xd.service.TMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Malloc
 * @since 2020-07-20
 */
@Service
public class TMessageServiceImpl extends ServiceImpl<TMessageMapper, TMessage> implements TMessageService {

    @Override
    public List<TMessage> getAllMessage() {
        return this.list();
    }
}
