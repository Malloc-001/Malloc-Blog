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

    /**
     * 得到所有友链通过list对象返回
     * @return
     */
    List<TFriend> getAllFriend();

    /**
     * 判断是否有相同的链接
     * @return
     * @param blogAddress
     */
    boolean isSameLink(String blogAddress);
}
