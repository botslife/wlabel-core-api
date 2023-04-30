package org.tm.api.core.common.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * SecurityConfigurer is to configure ResourceServer and HTTP Security.
 * <p>
 *   Please make sure you check HTTP Security configuration and change is as per your needs.
 * </p>
 *
 * Note: Use {@link org.tm.api.core.common.security.config.SecurityProperties} to configure required CORs configuration and enable or disable security of application.
 */
// TODO Review these settings before putting this live, lots of experimental stuff in here.
@Configuration
@EnableWebSecurity
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ConditionalOnProperty(prefix = "rest.security", value = "enabled", havingValue = "true")
@Import({org.tm.api.core.common.security.config.SecurityProperties.class})
public class SecurityConfigurer extends ResourceServerConfigurerAdapter {

  private String ADMIN_ROLE_NAME = "ADMIN";
  private String USER_ROLE_NAME = "USER";
  private final ResourceServerProperties resourceServerProperties;

  private final org.tm.api.core.common.security.config.SecurityProperties securityProperties;

  /* Using spring constructor injection, @Autowired is implicit */
  public SecurityConfigurer(ResourceServerProperties resourceServerProperties, org.tm.api.core.common.security.config.SecurityProperties securityProperties) {
    this.resourceServerProperties = resourceServerProperties;
    this.securityProperties = securityProperties;
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    resources.resourceId(resourceServerProperties.getResourceId());
  }

  @Override
  public void configure(final HttpSecurity http) throws Exception {
    http.cors()
        .configurationSource(corsConfigurationSource())
        .and()
        .headers()
        .frameOptions()
        .disable()
        .and()
        .csrf()/*
        .and()
        .httpBasic()*/
        .disable()
        .authorizeRequests()
            .antMatchers(HttpMethod.DELETE,securityProperties.getApiMatcher())
            .hasRole(ADMIN_ROLE_NAME)
            .antMatchers(HttpMethod.PUT, securityProperties.getApiMatcher())
            .hasRole(ADMIN_ROLE_NAME)
            .antMatchers(HttpMethod.POST, securityProperties.getApiMatcher())
            .hasAnyRole(ADMIN_ROLE_NAME,USER_ROLE_NAME)
            .antMatchers(HttpMethod.GET,securityProperties.getApiMatcher())
            .hasRole(ADMIN_ROLE_NAME)
            .anyRequest()
        .authenticated();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    if (null != securityProperties.getCorsConfiguration()) {
      source.registerCorsConfiguration("/**", securityProperties.getCorsConfiguration());
    }
    return source;
  }

  @Bean
  public org.tm.api.core.common.security.config.JwtAccessTokenCustomizer jwtAccessTokenCustomizer(ObjectMapper mapper) {
    return new org.tm.api.core.common.security.config.JwtAccessTokenCustomizer(mapper);
  }

}
