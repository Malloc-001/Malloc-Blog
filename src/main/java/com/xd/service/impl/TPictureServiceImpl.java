package com.xd.service.impl;

import com.xd.entity.TPicture;
import com.xd.mapper.TPictureMapper;
import com.xd.service.TPictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Malloc
 * @since 2020-07-20
 */
@Service
public class TPictureServiceImpl extends ServiceImpl<TPictureMapper, TPicture> implements TPictureService {

    @Override
    public List<TPicture> getAllPicture() {
        return this.list();
    }
}
