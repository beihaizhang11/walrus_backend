package com.walrus.server.controller;

import com.walrus.common.result.Result;
import com.walrus.pojo.dto.LoginRequest;
import com.walrus.pojo.dto.LoginResponse;
import com.walrus.pojo.entity.User;
import com.walrus.server.service.UserService;
import com.walrus.server.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "认证管理", description = "用户登录登出相关接口")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest request) {
        log.info("用户登录: {}", request.getUsername());
        User user = userService.findByUsername(request.getUsername());
        if (user == null) {
            return Result.error("用户不存在");
        }

        if (!userService.validatePassword(request.getPassword(), user.getPassword())) {
            log.warn("密码错误，输入密码: {}, 加密后: {}, 数据库密码: {}", 
                request.getPassword(), 
                userService.encodePassword(request.getPassword()), 
                user.getPassword());
            return Result.error("密码错误");
        }

        if (user.getStatus() != 1) {
            return Result.error("用户已被禁用");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUsername(user.getUsername());
        response.setName(user.getName());

        return Result.success(response);
    }

    @Operation(summary = "用户登出")
    @PostMapping("/logout")
    public Result<Void> logout(@RequestHeader("Authorization") String token) {
        // JWT是无状态的，客户端只需要删除token即可
        return Result.success();
    }
} 