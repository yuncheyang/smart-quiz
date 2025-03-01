package com.yunche.application.conreoller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Collection;

@Tag(name = "测试")
@RestController
public class test {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "测试接口" ,description = "通过测试学习")
    @GetMapping("/test")
    @PreAuthorize("hasAnyAuthority('user:manage')")
    public String test(@Parameter(name = "id", description = "用户ID", required = true) Long userId){
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println(authorities);
        return "test" + userId  ;
    }

    @Operation(summary = "dd")
    @GetMapping("/dd")
    @Parameters({
            @Parameter(name = "userId", description = "用户ID", required = true),
            @Parameter(name = "status", description = "用户状态", required = false)
    })
    public String test1( @RequestParam(required = false) Integer id){
        return "test" + id;
    }

    @RequestMapping("/test2")
    public void  test2() throws NoSuchAlgorithmException {
        String encode = passwordEncoder.encode("222");
        System.out.println(encode);

        // 生成 HMAC 密钥
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        keyGen.init(256); // 设置密钥长度为 256 位
        Key key = keyGen.generateKey();
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Generated Key: " + base64Key);
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
