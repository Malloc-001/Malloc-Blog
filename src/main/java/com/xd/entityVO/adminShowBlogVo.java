package com.xd.entityVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 后台展示博客列表
 * @author Malloc
 */
@Data
@ApiModel(value="后台博客展示Vo", description="")
public class adminShowBlogVo {

    @ApiModelProperty(value = "文章id")
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "推荐")
    private Boolean recommend;

    @ApiModelProperty(value = "发布(1是发布，0是保存)")
    private Boolean published;
    /**
     * 博客所属类型
     */
    @ApiModelProperty(value = "type名")
    private String typeName;

}
