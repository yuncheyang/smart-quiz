package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.domain.service.SubjectLabelService;
import com.yunche.infra.entity.SubjectLabel;
import com.yunche.infra.mapper.SubjectLabelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectLabelServiceImpl extends ServiceImpl<SubjectLabelMapper, SubjectLabel> implements SubjectLabelService {

    @Transactional
    @Override
    public void batchSave(List<SubjectLabel> labelList) {
        // 1. 参数校验
        if (labelList == null || labelList.isEmpty()) {
            throw new IllegalArgumentException("插入列表不能为空");
        }

        // 2. 设置批次大小（如果数据量较大，可以手动设置批次大小）
        int batchSize = 500; // 每批插入 500 条

        // 3. 执行批量插入
        saveBatch(labelList, batchSize);
    }

    @Override
    public long selectIdByName(String labelName) {
        LambdaQueryWrapper<SubjectLabel> query = Wrappers.<SubjectLabel>lambdaQuery()
                .eq(SubjectLabel::getLabelName, labelName)
                .select(SubjectLabel::getId);
        // 执行查询并获取 id
        SubjectLabel subjectLabel = getOne(query);
        if (subjectLabel != null) {
            return subjectLabel.getId();  // 返回查询到的 id
        } else {
            return -1;  // 如果没有查询到数据，返回 -1 或者适当的值
        }

    }
}
