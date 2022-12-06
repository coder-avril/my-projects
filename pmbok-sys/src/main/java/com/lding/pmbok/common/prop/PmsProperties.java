package com.lding.pmbok.common.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("cross")
@Component
@Data
public class PmsProperties {
    private String[] crossOrigins;
}
