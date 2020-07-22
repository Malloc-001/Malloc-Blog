package com.xd.entityVO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xd.entity.TMessage;
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
@ApiModel(value="MessageVo对象", description="")
public class MessageVo {
    @ApiModelProperty(value = "留言ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    private String nickname;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "是不是管理员")
    private Boolean adminMessage;

    @ApiModelProperty(value = "回复的子留言")
    private List<MessageVo> replyMessages = new ArrayList<>();

    @ApiModelProperty(value = "父留言")
    private TMessage parentMessage;

    @ApiModelProperty(value = "父留言的昵称")
    private String parentNickname;

    @ApiModelProperty(value = "父留言ID")
    private Long parentMessageId;
}
