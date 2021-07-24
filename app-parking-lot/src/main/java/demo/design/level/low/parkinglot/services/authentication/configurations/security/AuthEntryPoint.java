package demo.design.level.low.parkinglot.services.authentication.configurations.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(final HttpServletRequest httpServletRequest,
                         final HttpServletResponse httpServletResponse,
                         final AuthenticationException e) throws IOException, ServletException {
        log.error("Responding with unauthorized error. Message - {}",
                "{\"status\":\"" + 401 + "\",\"message\":\"Access Denied\"}");
        httpServletResponse.setContentType("appplication/json");
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                        "{\"status\":\"" + 401 + "\",\"message\":\"Access Denied\"}");
    }

}