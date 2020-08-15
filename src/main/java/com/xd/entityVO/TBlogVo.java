package com.xd.entityVO;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value="TBlogVo对象", description="")
public class TBlogVo {

    @ApiModelProperty(value = "文章id")
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "浏览量")
    private Integer views;

    @ApiModelProperty(value = "留言数")
    private Integer commentCount;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "首图")
    private String firstPicture;

    @ApiModelProperty(value = "描述")
    private String description;
    /**
     * 博客所属类型
     */
    @ApiModelProperty(value = "type名")
    private String typeName;

    @ApiModelProperty(value = "分类id")
    private Long typeId;

//      User
    @ApiModelProperty(value = "用户名称")
    private String nickName;

    @ApiModelProperty(value = "用户头像")
    private String avatar;
}
