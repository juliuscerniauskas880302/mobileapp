package com.juliuscerniauskas.app.ws.feature.impl.v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.spi.FeatureProvider;
import org.togglz.core.user.NoOpUserProvider;
import org.togglz.core.user.UserProvider;

@Configuration
public class EnumFeatureConfiguration {
    @Bean
    public FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(VCatchEnumFeatures.class);
    }

    @Bean
    UserProvider userProvider() {
        return new NoOpUserProvider();
    }
}


