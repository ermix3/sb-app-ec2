package io.ermix.sbAppEc.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "application.cors")
public class CorsProperties {
    private List<String> origins;
    private List<String> methods;
    private List<String> headers;
    private boolean allowCredentials = true;
    private long maxAge = 3600;
}
