package com.yunche.application.conreoller;

import com.yunche.common.entity.Result;
import com.yunche.domain.dto.SubjectLabelDto;
import com.yunche.domain.service.SubjectLabelService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 题目标签模块
 */

@RestController
@RequestMapping("/subject/label")
@Slf4j
public class SubjectLabelController {

    @Resource
    private SubjectLabelService subjectLabelService;

    @GetMapping("/getAllLabel")
    public Result<SubjectLabelDto> getAllLabel(){
        try {
            return Result.ok(subjectLabelService.getAllLabel());
        } catch (Exception e) {
            log.error("SubjectLabelController.getAllLabel.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }
}
