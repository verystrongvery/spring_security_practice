package com.example.spring_security_practice.security.filter;

import com.example.spring_security_practice.security.constants.SecurityConstants;
import com.example.spring_security_practice.security.service.TokenProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final TokenProviderService tokenProviderService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // HTTP 요청 헤더에서 액세스 토큰 조회
        String accessTokenHeader = request.getHeader(SecurityConstants.JWT_TOKEN_HEADER);
        String accessToken = tokenProviderService.getTokenByRequestHeader(accessTokenHeader);
        // 액세스 토큰이 유효하지 않은경우, SecurityContext를 비움
        // 액세스 토큰이 유효한 경우, 인증객체를 생성하고 SecurityContext에 저장
        Authentication authentication = tokenProviderService.createAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
