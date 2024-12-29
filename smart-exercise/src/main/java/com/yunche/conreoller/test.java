package com.yunche.conreoller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "测试")
@RestController
public class test {

    @Operation(summary = "测试接口" ,description = "通过测试学习")
    @GetMapping("/test")
    public String test(@Parameter(name = "id", description = "用户ID", required = true) Long userId){
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
}
