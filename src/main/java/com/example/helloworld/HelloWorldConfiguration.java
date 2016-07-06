package com.example.helloworld;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by vagrant on 7/6/16.
 */
public class HelloWorldConfiguration extends Configuration {
    @Valid
    @NotNull
    public KatharsisConfiguration katharsis = new KatharsisConfiguration();

    public class KatharsisConfiguration {
        @Valid
        @NotNull
        public String host;

        @Valid
        @NotNull
        public String searchPackage;
    }


}
