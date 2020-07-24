package com.xd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xd.entity.TUser;
import com.xd.mapper.TUserMapper;
import com.xd.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.util.MD5Utils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Malloc
 * @since 2020-07-20
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {

    @Override
    public TUser checkUser(String username,String password) {
        QueryWrapper<TUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",username)
                .eq("password", MD5Utils.code(password));
        return this.getOne(userQueryWrapper);
    }
}
