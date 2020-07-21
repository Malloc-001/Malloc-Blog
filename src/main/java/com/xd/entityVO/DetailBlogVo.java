package com.xd.entityVO;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class DetailBlogVo {

    @ApiModelProperty(value = "文章id")
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "浏览量")
    private Integer views;

    @ApiModelProperty(value = "留言数")
    private Integer commentCount;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "首图")
    private String firstPicture;

    @ApiModelProperty(value = "文章来源(原创，转自，翻译)")
    private String flag;

    @ApiModelProperty(value = "转载声明")
    private Boolean shareStatement;

    @ApiModelProperty(value = "赞赏")
    private Boolean appreciation;

    @ApiModelProperty(value = "评论")
    private Boolean commentabled;

    /**
     *  Type
     */
    @ApiModelProperty(value = "type名")
    private String typeName;
    /**
     *  User
     */
    @ApiModelProperty(value = "用户名称")
    private String nickName;

    @ApiModelProperty(value = "用户头像")
    private String avatar;
}
