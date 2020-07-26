package com.xd.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xd.entity.TComment;
import com.xd.entity.TMessage;
import com.xd.entity.TUser;
import com.xd.entityVO.CommentVo;
import com.xd.entityVO.MessageVo;
import com.xd.mapper.TCommentMapper;
import com.xd.service.TCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class TCommentServiceImpl extends ServiceImpl<TCommentMapper, TComment> implements TCommentService {
    @Value("${message.avatar}")
    String avatar;

    @Override
    public List<CommentVo> getCommentByBlogId(Long blogId) {
//        一级评论
        QueryWrapper<TComment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("blog_id", blogId).eq("parent_comment_id", -1);
        List<TComment> highCommentList = this.list(commentQueryWrapper);
        List<CommentVo> highCommentVoList = new ArrayList<>();
        for(TComment comment :highCommentList){
            CommentVo commentVo = new CommentVo();
            BeanUtil.copyProperties(comment,commentVo);
            List<CommentVo> replayComment = findReplayComment(commentVo, new ArrayList<>());
            commentVo.setReplyComments(replayComment);
            highCommentVoList.add(commentVo);
        }
        return highCommentVoList;
    }

    @Override
    public void saveComment(CommentVo commentVo, TUser user) {
        TComment comment = new TComment();
        BeanUtil.copyProperties(commentVo,comment);
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            //设置头像
            comment.setAvatar(avatar);
            comment.setAdminComment(false);
        }
        if (commentVo.getParentComment().getId() != null) {
            comment.setParentCommentId(commentVo.getParentComment().getId());
        }
        this.save(comment);
    }

    //            查询此评论的二级评论以及三级评论等等和父评论
    private List<CommentVo> findReplayComment(CommentVo commentParentVo,List<CommentVo> replayComment){
        QueryWrapper<TComment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("parent_comment_id",commentParentVo.getId());
        List<TComment> comments = this.list(commentQueryWrapper);
        if (comments != null){
            for (TComment comment : comments){
                CommentVo commentVo = new CommentVo();
                BeanUtil.copyProperties(comment,commentVo);
                commentVo.setParentNickname(commentParentVo.getNickname());
                replayComment.add(commentVo);
                findReplayComment(commentVo,replayComment);
            }
        }
        return replayComment;
    }

}