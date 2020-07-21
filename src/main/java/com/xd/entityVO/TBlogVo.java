package com.xd.entityVO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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

//      type
    @ApiModelProperty(value = "type名")
    private String typeName;

//      User
    @ApiModelProperty(value = "用户名称")
    private String nickName;

    @ApiModelProperty(value = "用户头像")
    private String avatar;
}
