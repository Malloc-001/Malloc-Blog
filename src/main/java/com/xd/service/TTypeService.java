package com.xd.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.entity.TType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.entityVO.TBlogVo;
import com.xd.entityVO.TypeVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Malloc
 * @since 2020-07-20
 */
public interface TTypeService extends IService<TType> {
    /**
     * 根据博客类型把博客分类
     * @return
     */
    Page<TBlogVo> getBlogByType(long id);

    /**
     *
     * @return
     */
    List<TypeVo> getAllTypeAndBlog();

    /**
     * 分页显示所有Type
     * @return
     */
    Page<TType> getAllTypePage( Page<TType> page);

    TType getTypeByName(String name);
}
