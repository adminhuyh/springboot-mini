package com.zyxx.xcx.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zyxx.sys.entity.SysConfigure;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("care_user")
@ApiModel(value = "CareUser对象", description = "系统设置")
public class CareUser extends Model<CareUser> {


    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "系统名称(简称)")
    @TableField("user_no")
    private Long userNo;

    @ApiModelProperty(value = "系统名称(全称)")
    @TableField("care_user_number")
    private Long careUserNumber;

    @ApiModelProperty(value = "系统logo")
    @TableField("createdAt")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "网站标题")
    @TableField("updatedAt")
    private LocalDateTime updatedAt;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
