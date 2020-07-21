package com.xd.service;

import com.xd.entity.TFriend;
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
public interface TFriendService extends IService<TFriend> {

    List<TFriend> getAllFriend();
}
