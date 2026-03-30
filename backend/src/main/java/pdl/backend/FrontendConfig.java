package pdl.backend;

import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter configuration to support Single Page Application (SPA) routing.
 * Any non-API, non-static traffic requested directly via URL is intercepted
 * and redirected to Vue.js's index.html.
 */
@Configuration
public class FrontendConfig {

  /**
   * Registers the custom SPA redirect filter into the application servlet chain.
   */
  @Bean
  public FilterRegistrationBean<OncePerRequestFilter> spaRedirectFiler() {
    FilterRegistrationBean<OncePerRequestFilter> registration = new FilterRegistrationBean<>();
    registration.setFilter(createRedirectFilter());
    registration.addUrlPatterns("/*");
    registration.setName("frontendRedirectFiler");
    registration.setOrder(1);
    return registration;
  }

  private OncePerRequestFilter createRedirectFilter() {
    return new OncePerRequestFilter() {
      // Regex ignores standard API routes, actuator, raw images, and built frontend assets.
      private final String REGEX =
        "(?!/actuator|/images|/_nuxt|/static|/index\\.html|/200\\.html|/favicon\\.ico|/sw\\.js).*$";
      private Pattern pattern = Pattern.compile(REGEX);

      @Override
      protected void doFilterInternal(
        HttpServletRequest req,
        HttpServletResponse res,
        FilterChain chain
      ) throws ServletException, IOException {
        
        // If the path matches our regex and isn't root, forward it to the frontend index
        if (pattern.matcher(req.getRequestURI()).matches() && !req.getRequestURI().equals("/")) {
          RequestDispatcher rd = req.getRequestDispatcher("/");
          rd.forward(req, res);
        } else {
          // Normal Spring API processing continues
          chain.doFilter(req, res);
        }
      }
    };
  }
}