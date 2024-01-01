package Spike.JobDescription.web;

import Spike.JobDescription.converter.LocalDateToStringConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LocalDateToStringConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/logout",
                        "/css/**", "/*.ico", "/error", "/signUp", "/updateCharacterCount", "/jobs/api/**"
                );
    }
}
