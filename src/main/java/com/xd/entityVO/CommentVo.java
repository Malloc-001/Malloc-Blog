package com.xd.entityVO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * <p>
 *
 * </p>
 *
 * @author Malloc
 * @since 2020-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CommentVo对象", description="")
public class CommentVo {
    @ApiModelProperty(value = "评论id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "姓名")
    private String nickname;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "博客id")
    private Long blogId;

    @ApiModelProperty(value = "父评论id")
    private Long parentCommentId;

    @ApiModelProperty(value = "是不是管理员")
    private Boolean adminComment;

    @ApiModelProperty(value = "父评论用户昵称")
    private String parentNickname;

    @ApiModelProperty(value = "子评论")
    private List<CommentVo> replyComments = new ArrayList<CommentVo>();

    @ApiModelProperty(value = "博客")
    private DetailBlogVo blog;

    @ApiModelProperty(value = "父评论")
    private CommentVo parentComment;
}
