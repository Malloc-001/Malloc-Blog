package com.xd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Malloc
 * @since 2020-07-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TBlog对象", description="")
public class TBlog implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "文章id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "赞赏")
    private Boolean appreciation;

    @ApiModelProperty(value = "评论")
    private Boolean commentabled;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "首图")
    private String firstPicture;

    @ApiModelProperty(value = "文章来源(原创，转自，翻译)")
    private String flag;

    @ApiModelProperty(value = "发布(1是发布，0是保存)")
    private Boolean published;

    @ApiModelProperty(value = "推荐")
    private Boolean recommend;

    @ApiModelProperty(value = "转载声明")
    private Boolean shareStatement;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "浏览量")
    private Integer views;

    @ApiModelProperty(value = "分类id")
    private Long typeId;

    @ApiModelProperty(value = "作者id")
    private Long userId;

    @ApiModelProperty(value = "留言数")
    private Integer commentCount;

    @ApiModelProperty(value = "删除")
    @TableLogic
    private Integer deleted;


}
