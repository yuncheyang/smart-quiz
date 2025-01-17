package com.yunche.common.enums;

public enum SubjectTypeEnum {

    RADIO(1,"单选"),
    MULTIPLE(2,"多选"),
    JUDGE(3,"判断");

    public final int code;

    public final String desc;

   SubjectTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SubjectTypeEnum getByCode(int code) {
        for (SubjectTypeEnum subjectTypeEnum : SubjectTypeEnum.values()) {
            if (subjectTypeEnum.code == code) {
                return subjectTypeEnum;
            }
        }
        return null;
    }
}
