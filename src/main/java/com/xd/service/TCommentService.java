package com.xd.service;

import com.xd.entity.TComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.entity.TUser;
import com.xd.entityVO.CommentVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Malloc
 * @since 2020-07-20
 */
public interface TCommentService extends IService<TComment> {
    List<CommentVo> getCommentByBlogId(Long blogId);
    void saveComment(CommentVo commentVo, TUser user);
}
