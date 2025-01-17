package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.application.conreoller.po.SubjectDetailsPO;
import com.yunche.application.conreoller.po.SubjectInfoPO;
import com.yunche.common.entity.PageResult;
import com.yunche.domain.dto.SubjectDetailsDto;
import com.yunche.domain.dto.SubjectInfoDto;
import com.yunche.domain.handler.SubjectTypeFactory;
import com.yunche.domain.handler.SubjectTypeHandler;
import com.yunche.domain.service.SubjectInfoService;
import com.yunche.domain.service.SubjectMappingService;
import com.yunche.infra.entity.SubjectInfo;
import com.yunche.infra.entity.SubjectMapping;
import com.yunche.infra.mapper.SubjectInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SubjectInfoServiceImpl extends ServiceImpl<SubjectInfoMapper, SubjectInfo> implements SubjectInfoService {

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectTypeFactory subjectTypeFactory;


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
                .select(SubjectInfo::getId, SubjectInfo::getSubjectName, SubjectInfo::getSubjectType);
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

    @Override
    public PageResult<SubjectInfoDto> getSubjectByPage(SubjectInfoPO subjectInfoPO) {
        Integer labelId = subjectInfoPO.getLabelId();
        Integer pageNo = subjectInfoPO.getPageNo();
        Integer pageSize = subjectInfoPO.getPageSize();
        List<SubjectMapping> subjectIds = subjectMappingService.selectSubjectIdByLabelId(labelId);
        List<Integer> subjectIDS = subjectIds.stream().map(SubjectMapping::getSubjectId).toList();

        Page<SubjectInfo> pagers = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<SubjectInfo> queryWrapper = Wrappers.<SubjectInfo>lambdaQuery()
                .in(SubjectInfo::getId, subjectIDS)
                .select(SubjectInfo::getId, SubjectInfo::getSubjectName, SubjectInfo::getSubjectType, SubjectInfo::getSubjectDifficult)
                .orderByAsc(SubjectInfo::getId);
        Page<SubjectInfo> subjectInfoPage = super.page(pagers, queryWrapper);
        PageResult<SubjectInfoDto> pageResult = new PageResult<>();
        List<SubjectInfo> records = subjectInfoPage.getRecords();
        List<SubjectInfoDto> record = records.stream().map(info -> {
            SubjectInfoDto subjectInfoDto = new SubjectInfoDto();
            subjectInfoDto.setId(info.getId());
            subjectInfoDto.setSubjectName(info.getSubjectName());
            subjectInfoDto.setSubjectDifficult(info.getSubjectDifficult());
            subjectInfoDto.setSubjectType(info.getSubjectType());
            return subjectInfoDto;
        }).toList();
        pageResult.setRecords(record);
        pageResult.setPageNo(pageNo);
        pageResult.setPageSize(pageSize);
        pageResult.setTotal((int) subjectInfoPage.getTotal());
        return pageResult;
    }

    @Override
    public SubjectDetailsDto getSubjectDetails(SubjectDetailsPO subjectDetailsPO) {
        Integer id = subjectDetailsPO.getId();
        LambdaQueryWrapper<SubjectInfo> queryWrapper = Wrappers.<SubjectInfo>lambdaQuery()
                .eq(SubjectInfo::getId, id);
        SubjectInfo subjectInfo = getOne(queryWrapper);
        Integer subjectType = subjectInfo.getSubjectType();
        SubjectTypeHandler handler = subjectTypeFactory.getSubjectTypeHandler(subjectType);
        SubjectDetailsDto subjectDetailsDto = handler.query(id);
        subjectDetailsDto.setSubjectName(subjectInfo.getSubjectName());
        return subjectDetailsDto;
    }

    @Override
    public SubjectInfo queryByCondition(Integer subjectId) {
        LambdaQueryWrapper<SubjectInfo> queryWrapper = Wrappers.<SubjectInfo>lambdaQuery()
                .eq(SubjectInfo::getId, subjectId);
        return getOne(queryWrapper);
    }
}
