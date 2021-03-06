package com.xd.service;

import com.xd.entity.TPicture;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.*;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Malloc
 * @since 2020-07-20
 */
public interface TPictureService extends IService<TPicture> {

    List<TPicture> getAllPicture();
}
