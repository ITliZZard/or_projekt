package fer.project.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class Auth0AccessDeniedHandler implements AccessDeniedHandler {

    private final String errorPageUrl;

    public Auth0AccessDeniedHandler(String errorPageUrl) {
        this.errorPageUrl = errorPageUrl;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        // Redirect to the error page URL
        response.sendRedirect(errorPageUrl);
    }
}
