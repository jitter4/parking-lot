package com.example.parkinglot.filters;

import com.example.parkinglot.config.auth.SecProps;
import com.example.parkinglot.security.UserPrincipal;
import com.example.parkinglot.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private JwtUtils jwtUtils;
    private SecProps secProps;

    public JWTAuthenticationFilter(JwtUtils jwtUtils, SecProps secProps) {
        this.jwtUtils = jwtUtils;
        this.secProps = secProps;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Acce" +
                "ss-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Authorization");
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            return;
        }

        String utid = request.getHeader(this.secProps.getUtid());
        if (StringUtils.isBlank(utid)) {
            sendErrorResponse(401, this.secProps.getFailureMessage(), response);
            return;
        }

        String uaidData = this.jwtUtils.getUTID(utid);
        if (Objects.isNull(uaidData)) {
            sendErrorResponse(401, this.secProps.getFailureMessage(), response);
        }

        int secret_Key_id = -1;
        try {
            secret_Key_id = Integer.parseInt(uaidData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (secret_Key_id == -1 || secret_Key_id > this.secProps.getSecret().size()) {
            sendErrorResponse(401, this.secProps.getFailureMessage(), response);
        }

        String secret = this.secProps.getSecret().get(secret_Key_id);

        String accessToken = accesstoken(request, response);
        if (StringUtils.isBlank(accessToken))
            return;

        Claims claims = this.jwtUtils.getClaimsFromAccessToken(accessToken, secret);
        if (claims == null) {
            sendErrorResponse(401, this.secProps.getFailureMessage(), response);
            return;
        }

        if (!this.jwtUtils.validateExpirationdate(claims)) {
            sendErrorResponse(HttpStatus.UNAUTHORIZED.value(), this.secProps.getFailureMessage(), response);
            return;
        }

        String role       = (String) claims.get("role");
        String remoteAddr = (String) claims.get("remoteAddr");
        if (StringUtils.isNotBlank(remoteAddr)) {
            if (!StringUtils.equals(remoteAddr, request.getRemoteAddr())) {
                sendErrorResponse(HttpStatus.FORBIDDEN.value(), this.secProps.getFailureMessage(), response);
                return;
            }
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
        authorities.add(authority);

        UserPrincipal authenticationUser = new UserPrincipal(claims.getSubject(), authorities);

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    authenticationUser, null, authenticationUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    private String headerAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String headerAuth = request.getHeader(this.secProps.getAccessToken());

        if (StringUtils.isBlank(headerAuth) || "null".equals(headerAuth)
                || !headerAuth.startsWith(this.secProps.getTokenType())) {
            sendErrorResponse(401, this.secProps.getFailureMessage(), response);
            return null;
        }
        return headerAuth;
    }

    private String accesstoken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String headerAuth = headerAuth(request, response);
        if (StringUtils.isBlank(headerAuth) || headerAuth.length() < 7) {
            sendErrorResponse(401, this.secProps.getFailureMessage(), response);
            return null;
        }
        return headerAuth.substring(7);
    }

    private void sendErrorResponse(int code, String msg, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write(String.format("{\"status\":\"" + code + "\",\"message\":\" %s \"}", msg));
        response.setStatus(code);
    }

}
