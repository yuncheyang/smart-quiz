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
@TableName("subject_radio_new")
@Schema(name = "SubjectRadio",description = "单选题")
public class SubjectRadio implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id" ,type = IdType.AUTO)
    @Schema(description = "主键")
    private Long id;

    @Schema(description = "题目id")
    private Integer subjectId;

    @Schema(description = "选项标识")
    private String optionKey;

    @Schema(description = "选项内容")
    private String optionContent;

    @Schema(description = "创建人")
    private String createdBy;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "修改人")
    private String updatedBy;

    @Schema(description = "修改时间")
    private LocalDateTime updatedTime;

    @Schema(description = "是否删除")
    private Integer isDeleted;
}
