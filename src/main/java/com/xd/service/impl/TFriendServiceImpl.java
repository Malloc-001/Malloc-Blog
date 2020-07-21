package com.xd.service.impl;

import com.xd.entity.TFriend;
import com.xd.mapper.TFriendMapper;
import com.xd.service.TFriendService;
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
public class TFriendServiceImpl extends ServiceImpl<TFriendMapper, TFriend> implements TFriendService {

    @Override
    public List<TFriend> getAllFriend() {
        List<TFriend> friendList = this.list();
        return friendList;
    }
}
