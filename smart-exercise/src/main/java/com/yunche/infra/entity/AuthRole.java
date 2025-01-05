package com.yunche.infra.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("auth_role")
public class AuthRole implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String roleName;

    private String roleKey;

    private String createdBy;

    private LocalDateTime createdTime;

    private String updateBy;

    private LocalDateTime updateTime;

    private Integer isDeleted;
}
