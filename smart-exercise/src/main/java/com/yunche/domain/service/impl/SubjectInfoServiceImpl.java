package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.domain.dto.SubjectInfoDto;
import com.yunche.domain.service.SubjectInfoService;
import com.yunche.infra.entity.SubjectInfo;
import com.yunche.infra.mapper.SubjectInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SubjectInfoServiceImpl extends ServiceImpl<SubjectInfoMapper, SubjectInfo> implements SubjectInfoService {


    @Transactional // 确保方法运行在事务中
    @Override
    public void BatchList(Set<SubjectInfo> infoSet) {
        // 1. 参数校验
        if (infoSet == null || infoSet.isEmpty()) {
            throw new IllegalArgumentException("插入列表不能为空");
        }

        // 2. 设置批次大小（如果数据量较大，可以手动设置批次大小）
        int batchSize = 500; // 每批插入 500 条

        // 3. 执行批量插入
        saveBatch(infoSet, batchSize);
    }

    @Override
    public List<SubjectInfoDto> getInfoId() {

        LambdaQueryWrapper<SubjectInfo> queryWrapper = Wrappers.<SubjectInfo>lambdaQuery()
                .select(SubjectInfo::getId, SubjectInfo::getSubjectName,SubjectInfo::getSubjectType);
        List<SubjectInfo> list = this.list(queryWrapper);
        List<SubjectInfoDto> subjectInfoDtoList = new ArrayList<>();
        for (SubjectInfo subjectInfo : list) {
            SubjectInfoDto subjectInfoDto = new SubjectInfoDto();
            subjectInfoDto.setId(subjectInfo.getId());
            subjectInfoDto.setSubjectName(subjectInfo.getSubjectName());
            subjectInfoDto.setSubjectType(subjectInfo.getSubjectType());
            subjectInfoDtoList.add(subjectInfoDto);
        }
        return subjectInfoDtoList;

    }
}
