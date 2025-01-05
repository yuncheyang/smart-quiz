package com.yunche.domain.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserRolePermission {

    private Long userId;

    private String userName;

    private Map<String, List<String>> roleMap;

    private Map<String,List<String>> permissionMap;
}
