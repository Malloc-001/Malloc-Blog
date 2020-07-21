package com.xd.service;

import com.xd.entity.TMessage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Malloc
 * @since 2020-07-20
 */
public interface TMessageService extends IService<TMessage> {
    List<TMessage> getAllMessage();
}
