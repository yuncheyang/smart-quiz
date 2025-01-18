package com.yunche.application.conreoller;

import com.alibaba.fastjson.JSON;
import com.yunche.domain.dto.SubjectInfoDto;
import com.yunche.domain.dto.questionDto;
import com.yunche.domain.service.*;
import com.yunche.infra.entity.*;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 处理文件类
 */
@RestController
@Slf4j
public class ProcessFileController {

    @Resource
    private QuestionService questionService;

    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private SubjectRadioService subjectRadioService;

    @Resource
    private SubjectMultipleService subjectMultipleService;

    @Resource
    private SubjectJudgeService subjectJudgeService;

    @Resource
    private SubjectAnswerService subjectAnswerService;

    @Resource
    private SubjectMappingService subjectMappingService;


    @RequestMapping("/process")
    public void ProcessDataBase() {
        List<questionDto> questionDtos = questionService.GetDataBase();

        writeJSONFile(questionDtos);

    }

    @PostConstruct
    public List<questionDto> GetDataBase() {
        List<questionDto> questionDtos = questionService.GetDataBase();
        return questionDtos;
    }

    @RequestMapping("/info")
    public void ProcessSubjectInfo() {
        List<questionDto> questionDtos = GetDataBase();
        Set<SubjectInfo> infoSet = questionDtos.stream().map(questionDto -> {
            int type = 0;
            if ("single_answer".equals(questionDto.getType())) {
                type = 1;
            } else if ("multi_answer".equals(questionDto.getType())) {
                type = 2;
            } else if ("true_false".equals(questionDto.getType())) {
                type = 3;
            }
            SubjectInfo subjectInfo = new SubjectInfo();
            if (questionDto.getContent().length() > 255) {
                log.error("长度超出255该内容为{}", questionDto.getContent());
            }
            subjectInfo.setSubjectName(questionDto.getContent());
            subjectInfo.setSubjectDifficult(questionDto.getDifficulty());
            subjectInfo.setSubjectType(type);
            subjectInfo.setSettleName("yunche");
            subjectInfo.setSubjectScore(10);
            subjectInfo.setCreatedBy("yunche");
            subjectInfo.setCreatedTime(LocalDateTime.now());
            subjectInfo.setUpdatedBy("yunche");
            subjectInfo.setUpdatedTime(LocalDateTime.now());
            subjectInfo.setIsDeleted(0);
            return subjectInfo;
        }).collect(Collectors.toSet());

        subjectInfoService.BatchList(infoSet);

    }

    @RequestMapping("/label")
    public void ProcessSubjectLabel() {
        List<questionDto> questionDtos = GetDataBase();
        Set<String> labelSet = new HashSet<>();
        List<SubjectLabel> labelList = new LinkedList<>();
        for (questionDto questionDto : questionDtos) {
            String knowledge = questionDto.getKnowledge();
            String result = knowledge.replaceAll("[\\[\\]\"]", "");// 匹配并替换
            if (!result.isEmpty()) {
                labelSet.add(result);
            }
        }
        for (String labelName : labelSet) {
            SubjectLabel subjectLabel = new SubjectLabel();
            subjectLabel.setLabelName(labelName);
            subjectLabel.setSort_num(1);
            subjectLabel.setCreatedBy("yunche");
            subjectLabel.setCreatedTime(LocalDateTime.now());
            subjectLabel.setUpdatedBy("yunche");
            subjectLabel.setUpdatedTime(LocalDateTime.now());
            subjectLabel.setIsDeleted(0);
            labelList.add(subjectLabel);
        }
        subjectLabelService.batchSave(labelList);
    }

    @RequestMapping("/answer")
    public void ProcessSubjectAnswer(){
        processRadioAnswer();
        processMultipleAnswer();
        processJudgeAnswer();
    }

    private void processJudgeAnswer() {
        Set<Integer> subjectId = subjectJudgeService.getSubjectId();
        subjectAnswerService.updateBatch(subjectId);
    }

    private void processMultipleAnswer() {
        Set<Integer> subjectId = subjectMultipleService.getSubjectId();
        subjectAnswerService.updateBatchMultiple(subjectId);

    }

    private void processRadioAnswer() {
        Set<Integer> subjectId = subjectRadioService.getSubjectId();
        subjectAnswerService.updateBatch(subjectId);
    }

    // 构建 subjectName 到 questionDto 映射
    private Map<String, List<questionDto>> buildSubjectQuestionMap(List<questionDto> questionDtos) {
        return questionDtos.stream()
                .collect(Collectors.groupingBy(questionDto::getContent));
    }

    // 解析单个 questionDto 的 options 并生成 SubjectRadio 列表
    private List<SubjectRadio> generateSubjectRadios(Long subjectId, questionDto questionDto) {
        return getSubjectRadios(subjectId, questionDto);
    }

    private List<SubjectRadio> getSubjectRadios(Long subjectId, questionDto questionDto) {
        return questionDto.getParsedOptions().stream()
                .map(option -> {
                    SubjectRadio subjectRadio = new SubjectRadio();
                    subjectRadio.setSubjectId(subjectId.intValue());
                    subjectRadio.setOptionKey(option[0]);
                    subjectRadio.setOptionContent(option[1]);
                    subjectRadio.setIsDeleted(0);
                    subjectRadio.setCreatedBy("yunche");
                    subjectRadio.setCreatedTime(LocalDateTime.now());
                    subjectRadio.setUpdatedBy("yunche");
                    subjectRadio.setUpdatedTime(LocalDateTime.now());
                    return subjectRadio;
                })
                .collect(Collectors.toList());
    }

    private List<SubjectMultiple> generateSubjectMultiples(Long subjectId, questionDto questionDto) {
        return questionDto.getParsedOptions().stream()
                .map(option -> {
                    SubjectMultiple subjectMultiple = new SubjectMultiple();
                    subjectMultiple.setSubjectId(subjectId.intValue());
                    subjectMultiple.setOptionKey(option[0]);
                    subjectMultiple.setOptionContent(option[1]);
                    subjectMultiple.setIsDeleted(0);
                    subjectMultiple.setCreatedBy("yunche");
                    subjectMultiple.setCreatedTime(LocalDateTime.now());
                    subjectMultiple.setUpdatedBy("yunche");
                    subjectMultiple.setUpdatedTime(LocalDateTime.now());
                    return subjectMultiple;
                })
                .collect(Collectors.toList());
    }

    private List<SubjectJudge> generateSubjectJudges(Long subjectId, questionDto questionDto) {
        return questionDto.getParsedOptions().stream()
                .map(option -> {
                    SubjectJudge subjectJudge = new SubjectJudge();
                    subjectJudge.setSubjectId(subjectId.intValue());
                    subjectJudge.setOptionKey(option[0]);
                    subjectJudge.setOptionContent(option[1]);
                    subjectJudge.setIsDeleted(0);
                    subjectJudge.setCreatedBy("yunche");
                    subjectJudge.setCreatedTime(LocalDateTime.now());
                    subjectJudge.setUpdatedBy("yunche");
                    subjectJudge.setUpdatedTime(LocalDateTime.now());
                    return subjectJudge;
                })
                .collect(Collectors.toList());
    }

    @RequestMapping("Radio")
    public void processSubjectRadio() {
        List<SubjectInfoDto> infoList = subjectInfoService.getInfoId();
        List<questionDto> questionDtos = GetDataBase();
        processSubjectRadios(questionDtos, infoList);
    }

    // 主逻辑
    public void processSubjectRadios(List<questionDto> questionDtos, List<SubjectInfoDto> infoList) {
        Map<String, List<questionDto>> subjectNameToQuestions = buildSubjectQuestionMap(questionDtos);
        List<SubjectRadio> subjectRadios = new ArrayList<>();
        List<SubjectMultiple> subjectMultiples = new ArrayList<>();
        List<SubjectJudge> subjectJudges = new ArrayList<>();
        List<SubjectAnswer> subjectAnswers = new ArrayList<>();
        List<SubjectMapping> subjectMappings = new ArrayList<>();

        for (SubjectInfoDto subjectInfoDto : infoList) {
            Long subjectId = subjectInfoDto.getId();
            String subjectName = subjectInfoDto.getSubjectName();
            Integer subjectType = subjectInfoDto.getSubjectType();

            if (subjectNameToQuestions.containsKey(subjectName)) {
                List<questionDto> relatedQuestions = subjectNameToQuestions.get(subjectName);
                for (questionDto questionDto : relatedQuestions) {
                    //插入到answwer表中
                    String answer = questionDto.getAnswer();
                    SubjectAnswer subjectAnswer = batchAnswer(answer, subjectId);
                    subjectAnswers.add(subjectAnswer);
                    //插入到mapping表中

                    String labelName = questionDto.getKnowledge().replaceAll("[\\[\\]\"]", "");
                    if (!labelName.isEmpty()) {
                        long labelId = subjectLabelService.selectIdByName(labelName);
                        if (labelId == -1) {
                            throw new RuntimeException("labelId is -1 请检查");
                        }
                        SubjectMapping subjectMapping = batchMapping(subjectId, labelId);
                        subjectMappings.add(subjectMapping);
                    }


                    // 根据题型选择处理方式
                    if (subjectType == 1) {  // 单选
                        subjectRadios.addAll(generateSubjectRadios(subjectId, questionDto));
                    } else if (subjectType == 2) {  // 多选
                        subjectMultiples.addAll(generateSubjectMultiples(subjectId, questionDto));
                    } else if (subjectType == 3) {  // 判断
                        subjectJudges.addAll(generateSubjectJudges(subjectId, questionDto));
                    }
                }
            }
        }

        // 批量保存到对应的表
        if (!subjectRadios.isEmpty()) {
            subjectRadioService.saveBatch(subjectRadios);
        }
        if (!subjectMultiples.isEmpty()) {
            subjectMultipleService.saveBatch(subjectMultiples);
        }
        if (!subjectJudges.isEmpty()) {
            subjectJudgeService.saveBatch(subjectJudges);
        }
        //批量插入到answer表
        if (!subjectAnswers.isEmpty()) {
            subjectAnswerService.saveBatch(subjectAnswers);
        }
        if (!subjectMappings.isEmpty()) {
            subjectMappingService.saveBatch(subjectMappings);
        }
    }

    private SubjectMapping batchMapping(Long subjectId, long labelId) {
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setSubjectId(subjectId.intValue());
        subjectMapping.setLabelId((int) labelId);
        subjectMapping.setCreatedBy("yunche");
        subjectMapping.setCreatedTime(LocalDateTime.now());
        subjectMapping.setUpdatedBy("yunche");
        subjectMapping.setUpdatedTime(LocalDateTime.now());
        subjectMapping.setIsDeleted(0);
        return subjectMapping;
    }

    private SubjectAnswer batchAnswer(String answer, Long subjectId) {
        SubjectAnswer subjectAnswer = new SubjectAnswer();
        subjectAnswer.setSubjectId(subjectId.intValue());
        subjectAnswer.setCorrectOption(answer);
        subjectAnswer.setCreatedBy("yunche");
        subjectAnswer.setCreatedTime(LocalDateTime.now());
        subjectAnswer.setUpdatedBy("yunche");
        subjectAnswer.setUpdatedTime(LocalDateTime.now());
        subjectAnswer.setIsDeleted(0);
        return subjectAnswer;
    }

    private void writeJSONFile(List<questionDto> questionDtos) {

        // 将 List 转换为 JSON 字符串
        String jsonString = JSON.toJSONString(questionDtos);

        // 指定输出文件路径
        String filePath = "/Users/yangjianyu/Documents/ProcessFile/file.json"; // 你可以自定义文件路径

        // 将 JSON 写入文件
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonString);
            System.out.println("数据已成功写入到文件：" + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("写入文件时发生错误！");
        }
    }
}
