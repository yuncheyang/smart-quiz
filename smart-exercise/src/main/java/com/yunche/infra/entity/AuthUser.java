package com.yunche.infra.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("auth_user")
@Schema(name = "AuthUser",description = "用户信息实体类")
public class AuthUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户名称")
    private String userName;

    @Schema(description = "用户昵称")
    private String nickName;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "性别")
    private Integer sex;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "介绍")
    private String introduce;

    @Schema(description = "特殊字段")
    private String extJson;

    @Schema(description = "创建人")
    private String createdBy;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "修改人")
    private String updateBy;

    @Schema(description = "修改时间")
    private LocalDateTime updateTime;

    @Schema(description = "是否删除")
    private Integer isDeleted;
}
