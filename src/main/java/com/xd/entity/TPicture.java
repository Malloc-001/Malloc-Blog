package com.xd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="TPicture对象", description="")
public class TPicture implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "图片ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "图片链接地址")
    private String pictureaddress;

    @ApiModelProperty(value = "图片描述")
    private String picturedescription;

    @ApiModelProperty(value = "图片名称")
    private String picturename;

    @ApiModelProperty(value = "图片地点")
    private String picturetime;


}
