package com.xd.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.entity.TBlog;
import com.xd.entity.TFriend;
import com.xd.entity.TPicture;
import com.xd.entityVO.TimeAxisVo;
import com.xd.mapper.TBlogMapper;
import com.xd.mapper.TFriendMapper;
import com.xd.service.TBlogService;
import com.xd.service.TFriendService;
import com.xd.service.TimeAxisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Malloc
 * @since 2020-07-20
 */
@Service
public class TimeAxisServiceImpl extends ServiceImpl<TBlogMapper, TBlog> implements TimeAxisService {

    @Autowired
    TBlogService blogService;
    @Override
    public List<TimeAxisVo> getAllTimeAxisVo() {
        List<TBlog> blogList = this.blogService.list();
        List<TimeAxisVo> timeAxisVoList = new ArrayList<>();
        for (TBlog blog : blogList){
            TimeAxisVo timeAxisVo = new TimeAxisVo();
            BeanUtil.copyProperties(blog,timeAxisVo);
            timeAxisVoList.add(timeAxisVo);
        }
        return timeAxisVoList;
    }
}
