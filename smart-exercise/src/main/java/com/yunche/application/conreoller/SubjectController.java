package com.yunche.application.conreoller;


import com.google.common.base.Preconditions;
import com.yunche.application.conreoller.po.SubjectDetailsPO;
import com.yunche.application.conreoller.po.SubjectInfoPO;
import com.yunche.common.entity.PageResult;
import com.yunche.common.entity.Result;
import com.yunche.domain.dto.SubjectInfoDto;
import com.yunche.domain.service.SubjectInfoService;
import com.yunche.domain.service.SubjectMappingService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subject")
@Slf4j
public class SubjectController {

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectInfoService subjectInfoService;

    @PostMapping("/getSubjectByLabelId")
    public Result<PageResult<SubjectInfoDto>> getSubjectByLabelId(@RequestBody SubjectInfoPO subjectInfoPO) {
        try {
            Preconditions.checkNotNull(subjectInfoPO.getLabelId(),"id不能为空");
            return Result.ok(subjectInfoService.getSubjectByPage(subjectInfoPO));
        } catch (Exception e) {
            log.error("SubjectController.getSubjectByLabelId.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/getSubjectDetails")
    public Result<PageResult<SubjectInfoDto>> getSubjectDetails(@RequestBody SubjectDetailsPO subjectDetailsPO) {
        try {
            Preconditions.checkNotNull(subjectDetailsPO.getId(),"题目id不能为空");
            return Result.ok(subjectInfoService.getSubjectDetails(subjectDetailsPO));
        } catch (Exception e) {
            log.error("SubjectController.getSubjectByLabelId.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }
}
