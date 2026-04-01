package pdl.backend;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import java.time.Duration;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${jwt.secret}")
    private String jwtKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/images/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/_nuxt/**", "/static/**", "/*.html", "/favicon.ico").permitAll()
                .anyRequest().authenticated()
            )
            // 1. Overriding Boot Auto Configuration: Wire the components into the DSL
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                    .decoder(jwtDecoder())
                    .jwtAuthenticationConverter(jwtAuthenticationConverter())
                )
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // 2. Trusting a Single Symmetric Key & Customizing Timestamp Validation
    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKeySpec secretKey = new SecretKeySpec(jwtKey.getBytes(), "HmacSHA256");
        
        // Trusting a Single Symmetric Key
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(secretKey).build();

        // Customizing Timestamp Validation to handle clock drift
        OAuth2TokenValidator<Jwt> withClockSkew = new DelegatingOAuth2TokenValidator<>(
            new JwtTimestampValidator(Duration.ofSeconds(60))
        );
        
        jwtDecoder.setJwtValidator(withClockSkew);

        return jwtDecoder;
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        // Use the same injected jwtKey from application.yaml
        SecretKeySpec secretKey = new SecretKeySpec(jwtKey.getBytes(), "HmacSHA256");
        JWKSource<SecurityContext> immutableSecret = new ImmutableSecret<>(secretKey);
        return new NimbusJwtEncoder(immutableSecret);
    }

    // 3. Extracting Authorities Manually
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        
        // Map the custom claim instead of the default 'scope' or 'scp'
        grantedAuthoritiesConverter.setAuthoritiesClaimName("role");
        
        // Remove the default "SCOPE_" prefix to match your database's "ROLE_ADMIN"
        grantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        
        return jwtAuthenticationConverter;
    }
}