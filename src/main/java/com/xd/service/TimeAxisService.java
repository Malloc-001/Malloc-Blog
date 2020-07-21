package com.xd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.entity.TBlog;
import com.xd.entity.TPicture;
import com.xd.entityVO.TimeAxisVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Malloc
 * @since 2020-07-20
 */
public interface TimeAxisService extends IService<TBlog> {
    List<TimeAxisVo> getAllTimeAxisVo();
}
