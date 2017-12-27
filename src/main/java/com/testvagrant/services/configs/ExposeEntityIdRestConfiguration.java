package com.testvagrant.services.configs;

import com.testvagrant.services.models.Builds;
import com.testvagrant.services.models.Devices;
import com.testvagrant.services.models.Scenarios;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class ExposeEntityIdRestConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Builds.class, Devices.class, Scenarios.class);
    }
}
