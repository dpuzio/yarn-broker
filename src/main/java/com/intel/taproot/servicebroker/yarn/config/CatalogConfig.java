/**
 * Copyright (c) 2015 Intel Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.intel.taproot.servicebroker.yarn.config;

import com.google.common.collect.Lists;
import org.cloudfoundry.community.servicebroker.model.Catalog;
import org.cloudfoundry.community.servicebroker.model.Plan;
import org.cloudfoundry.community.servicebroker.model.ServiceDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Configuration
public class CatalogConfig {

    @Autowired
    private ExternalConfiguration configuration;

    private String SYSLOG_DRAIN = "syslog_drain";

    @Bean
    public Catalog catalog() {

        return new Catalog(Arrays.asList(new ServiceDefinition(configuration.getCfServiceId(), configuration.getCfServiceName(),
                "A simple yarn broker", true, true, getSharedPlans(), null, null, Arrays.asList(SYSLOG_DRAIN), null)));
    }


    private List<Plan> getSharedPlans() {
        return Lists.newArrayList(new Plan(UUID.randomUUID().toString() + "-shared-plan", "shared",
                "This is a default yarn plan.", null, true));
    }

}
