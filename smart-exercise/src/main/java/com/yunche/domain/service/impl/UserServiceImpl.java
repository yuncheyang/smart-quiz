package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.application.conreoller.po.UserDoLoginPO;
import com.yunche.common.config.redis.RedisUtil;
import com.yunche.common.constant.AuthConstant;
import com.yunche.common.enums.IsDeleteFlagEnum;
import com.yunche.common.exception.UserAlreadyExistsException;
import com.yunche.common.exception.UserLoginException;
import com.yunche.common.utils.JwtUtil;
import com.yunche.common.utils.RedisCache;
import com.yunche.domain.dto.LoginUser;
import com.yunche.domain.dto.UserRolePermission;
import com.yunche.domain.service.*;
import com.yunche.infra.entity.AuthRole;
import com.yunche.infra.entity.AuthUser;
import com.yunche.infra.mapper.AuthUserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<AuthUserMapper, AuthUser> implements UserService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private RedisCache redisCache;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RoleService roleService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RolePermissionService rolePermissionService;

    @Resource
    private PermissionService permissionService;

    private static final String LOGIN_PREFIX = "loginCode";

    private static final String LOGIN_USER = "loginUser";


    @Override
    public Map<String, String> doLogin(UserDoLoginPO userDoLoginPO) {

        //通过UsernamePasswordAuthenticationToken获取用户名和密码
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDoLoginPO.getUserName(), userDoLoginPO.getPassword());
        //AuthenticationManager委托机制对authenticationToken 进行用户认证
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        //如果认证没有通过，给出对应的提示
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("登录失败");
        }

        //如果认证通过，使用user生成jwt  jwt存入ResponseResult 返回

        //如果认证通过，拿到这个当前登录用户信息
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//        System.out.println(authorities);
        String userId = loginUser.getAuthUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        //存到redis中
        String buildKey = redisUtil.buildKey(LOGIN_PREFIX, userId);
        redisCache.setCacheObject(buildKey, loginUser);

        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean register(UserDoLoginPO userDoLoginPO) {
        String userName = userDoLoginPO.getUserName();
        String password = userDoLoginPO.getPassword();

        // 判断 username 或者 password 是否为空
        if (userName == null || userName.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new UserLoginException("用户名或密码不能为空");
        }
        boolean userExists = lambdaQuery()
                .eq(AuthUser::getUserName, userName)
                .exists();
        if (userExists) {
            throw new UserAlreadyExistsException("用户已经存在");
        }
        AuthUser authUser = saveUser(userDoLoginPO);
        //插入user表中
        boolean saveUser = save(authUser);
        //插入user_role关联表
        Long userId = authUser.getId();
        //根据roleKey查询获得roleId
        AuthRole authRole = roleService.selectByKey(AuthConstant.ADMIN_USER);
        Long roleId = authRole.getId();
        //插入user_role表
        userRoleService.saveUserRole(userId, roleId);
        //获取role_permission的值存到redis中
        List<Long> permissionIds = rolePermissionService.selectByRoleId(roleId);
        //根据permissionid查询permission表，获取permissionKey
        List<String> authPermissionList = permissionService.selectKeyByIds(permissionIds);
        //获取当前用户的id查询关联的角色id
        List<Long> roleIds = userRoleService.selectRoleIdByUserId(userId);
        //根据roleIDs查询role表的key
        List<String> authRoleList = roleService.selectKeyByIds(roleIds);
        //存到redis中
        Map<String, List<String>> authPermissionMap = new HashMap<>();
        Map<String, List<String>> authRoleMap = new HashMap<>();
        authPermissionMap.put("Permissions", authPermissionList);
        authRoleMap.put("Roles", authRoleList);
        UserRolePermission userRolePermission = new UserRolePermission();
        userRolePermission.setUserId(userId);
        userRolePermission.setUserName(userName);
        userRolePermission.setRoleMap(authRoleMap);
        userRolePermission.setPermissionMap(authPermissionMap);
        String buildKey = redisCache.buildKey(LOGIN_USER, userId.toString());
        redisCache.setCacheObject(buildKey, userRolePermission);

        return saveUser;
    }

    private AuthUser saveUser(UserDoLoginPO userDoLoginPO) {
        AuthUser authUser = new AuthUser();
        String password = userDoLoginPO.getPassword();
        authUser.setUserName(userDoLoginPO.getUserName());
        authUser.setPassword(passwordEncoder.encode(password));
        authUser.setNickName("yunche");
        authUser.setStatus(0);
        authUser.setCreatedBy("yunche");
        authUser.setCreatedTime(LocalDateTime.now());
        authUser.setIsDeleted(IsDeleteFlagEnum.UNDELETED.getCode());
        return authUser;
    }

    @Override
    public Boolean outLogin() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getAuthUser().getId();
        String buildKey = redisCache.buildKey(LOGIN_PREFIX, userid.toString());
        return redisCache.deleteObject(buildKey);
    }


}
