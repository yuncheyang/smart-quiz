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
@TableName("subject_mapping_new")
@Schema(name = "SubjectMapping", description = "题目分类关系表")
public class SubjectMapping implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id" ,type = IdType.AUTO)
    @Schema(description = "主键")
    private Long id;

    @Schema(description = "题目id")
    private Integer subjectId;

    @Schema(description = "标签id")
    private Integer labelId;

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
