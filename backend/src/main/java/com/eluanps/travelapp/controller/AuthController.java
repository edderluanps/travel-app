package com.eluanps.travelapp.controller;

import com.eluanps.travelapp.entity.dto.EmailDTO;
import com.eluanps.travelapp.security.JWTUtil;
import com.eluanps.travelapp.security.UserSS;
import com.eluanps.travelapp.service.AuthService;
import com.eluanps.travelapp.service.UserService;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @ApiOperation(value = "Refresh Token")
    @GetMapping(value = "/refresh_token")
    public void refreshToken(HttpServletResponse httpServletResponse) {
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        httpServletResponse.addHeader("Authorization", "Bearer " + token);
        httpServletResponse.addHeader("access-control-expose-headers", "Authorization");
    }

    @ApiOperation(value = "Restaura senha")
    @GetMapping(value = "/forgot")
    public void forgot(@Valid @RequestBody EmailDTO emailDto) {
        authService.sendNewPassword(emailDto.getEmail());
    }
}
