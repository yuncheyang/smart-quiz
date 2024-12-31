package com.yunche.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunche.domain.dto.questionDto;
import com.yunche.infra.entity.Question;

import java.util.List;

public interface QuestionService extends IService<Question> {

    List<questionDto> GetDataBase();
}
