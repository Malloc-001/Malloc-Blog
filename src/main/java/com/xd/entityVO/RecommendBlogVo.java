package com.xd.entityVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="RecommendBlogVo对象", description="")
public class RecommendBlogVo {

    @ApiModelProperty(value = "文章id")
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "分类id")
    private Long typeId;

    @ApiModelProperty(value = "首图")
    private String firstPicture;
}
