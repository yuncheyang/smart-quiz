package com.yunche.domain.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class questionDto {

    private Long id;

    private String type;

    private String content;

    private String options;

    private String answer;

    private String explain;

    private Integer difficulty;

    private String source;

    private String knowledge;

    private List<String[]> parsedOptions; // 每个选项是 [key, value] 的数组

    public List<String[]> getParsedOptions() {
        if (parsedOptions == null && options != null) {
            // 正则表达式提取每对双引号中的内容
            Pattern pattern = Pattern.compile("\"(.*?)\"");  // 匹配双引号内的内容
            Matcher matcher = pattern.matcher(options);

            // 使用stream将每个选项处理为 [key, value] 的形式
            parsedOptions = new ArrayList<>();

            while (matcher.find()) {
                String option = matcher.group(1);  // 获取每个双引号内的内容

                // 按照第一个"."来分割为 [key, value]
                String[] splitOption = option.split("\\.", 2);
                if (splitOption.length == 2) {
                    parsedOptions.add(splitOption);  // 添加到列表
                }
            }
        }

        return parsedOptions;
    }
}
