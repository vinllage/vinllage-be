package xyz.vinllage.member.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import xyz.vinllage.global.exceptions.UnAuthorizedException;
import xyz.vinllage.global.rests.JSONError;

import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class LoginFilter extends GenericFilterBean {

    private final TokenService tokenService;
    private final ObjectMapper om;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            tokenService.authenticate(request);
        } catch (UnAuthorizedException e) {
            HttpServletResponse res = (HttpServletResponse) response;
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.setContentType(MediaType.APPLICATION_JSON_VALUE);
            res.setCharacterEncoding("UTF-8");
            PrintWriter out = res.getWriter();
            JSONError error = new JSONError();
            error.setStatus(HttpStatus.UNAUTHORIZED);
            error.setMessages(e.getMessage());
            out.write(om.writeValueAsString(error));

            e.printStackTrace();
            return;
        }
        chain.doFilter(request, response);
    }
}