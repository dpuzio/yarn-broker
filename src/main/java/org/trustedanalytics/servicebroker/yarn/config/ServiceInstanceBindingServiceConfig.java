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

package org.trustedanalytics.servicebroker.yarn.config;

import com.google.common.collect.ImmutableMap;
import org.trustedanalytics.hadoop.HadoopConfigurationHelper;
import org.trustedanalytics.cfbroker.store.api.BrokerStore;
import org.trustedanalytics.cfbroker.store.impl.ServiceInstanceBindingServiceStore;
import org.trustedanalytics.hadoop.HadoopConfigurationHelper;
import org.trustedanalytics.hadoop.config.ConfigConstants;
import org.trustedanalytics.servicebroker.yarn.service.YarnServiceInstanceBindingService;
import org.cloudfoundry.community.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.cloudfoundry.community.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Configuration
public class ServiceInstanceBindingServiceConfig {

    @Autowired
    private ExternalConfiguration configuration;

    @Autowired
    @Qualifier(value = Qualifiers.SERVICE_INSTANCE_BINDING)
    private BrokerStore<CreateServiceInstanceBindingRequest> store;

    @Bean
    public ServiceInstanceBindingService getServiceInstanceBindingService()
            throws IllegalArgumentException, IOException, LoginException {

        return new YarnServiceInstanceBindingService(
                new ServiceInstanceBindingServiceStore(store), getCredentials(), configuration);
    }

    private Map<String, Object> getCredentials() throws IOException {

        Optional<Map<String, String>> hadoopConf =
                HadoopConfigurationHelper.getHadoopConfFromJson(configuration.getYarnProvidedParams());

        return ImmutableMap.of(
                "kerberos", ImmutableMap.of(
                        "kerberos", ImmutableMap.of("kdc", configuration.getKerberosKdc(),
                        "krealm", configuration.getKerberosRealm()),
                        ConfigConstants.HADOOP_CONFIG_KEY_VALUE,
                        ImmutableMap.copyOf(hadoopConf.orElse(Collections.emptyMap()))
                ));
    }
}
