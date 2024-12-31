package com.yunche.infra.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 题目信息实体类
 */
@Data
@TableName("subject_info_new")
@Schema(name = "SubjectInfo",description = "题目信息实体类")
public class SubjectInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "主键")
    private Long id;

    @Schema(description = "题目名称")
    private String subjectName;

    @Schema(description = "题目难度")
    private Integer subjectDifficult;

    @Schema(description = "题目类型")
    private Integer subjectType;

    @Schema(description = "出题人名")
    private String settleName;

    @Schema(description = "题目分数")
    private Integer subjectScore;

    @Schema(description = "题目解析")
    private String subjectParse;

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
