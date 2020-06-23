package cn.trasen.mcpc.transform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 解决跨域问题.
 *
 * @author zhaozq
 * @date: 2020-05-21 13:37:36
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Configuration
public class CorsConfig {

  public CorsConfiguration buildConfig() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();

    corsConfiguration.addAllowedOrigin("*");

    corsConfiguration.addAllowedHeader("*");

    corsConfiguration.addAllowedMethod("*");
    return corsConfiguration;
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

    source.registerCorsConfiguration("/**", buildConfig());
    return new CorsFilter(source);
  }
}