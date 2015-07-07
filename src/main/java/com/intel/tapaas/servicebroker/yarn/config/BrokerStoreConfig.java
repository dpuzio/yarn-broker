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

package com.intel.tapaas.servicebroker.yarn.config;

import com.intel.tapaas.cfbroker.store.api.BrokerStore;
import com.intel.tapaas.cfbroker.store.serialization.RepositoryDeserializer;
import com.intel.tapaas.cfbroker.store.serialization.RepositorySerializer;
import com.intel.tapaas.cfbroker.store.zookeeper.service.ZookeeperClient;
import com.intel.tapaas.cfbroker.store.zookeeper.service.ZookeeperClientBuilder;
import com.intel.tapaas.cfbroker.store.zookeeper.service.ZookeeperStore;
import org.cloudfoundry.community.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.cloudfoundry.community.servicebroker.model.ServiceInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

@Configuration
public class BrokerStoreConfig {

    @Autowired
    private ExternalConfiguration config;

    private FactoryHelper helper;

    @Autowired
    @Qualifier(Qualifiers.SERVICE_INSTANCE)
    private RepositorySerializer<ServiceInstance> instanceSerializer;

    @Autowired
    @Qualifier(Qualifiers.SERVICE_INSTANCE)
    private RepositoryDeserializer<ServiceInstance> instanceDeserializer;

    @Autowired
    @Qualifier(Qualifiers.SERVICE_INSTANCE_BINDING)
    private RepositorySerializer<CreateServiceInstanceBindingRequest> bindingSerializer;

    @Autowired
    @Qualifier(Qualifiers.SERVICE_INSTANCE_BINDING)
    private RepositoryDeserializer<CreateServiceInstanceBindingRequest> bindingDeserializer;

    public BrokerStoreConfig() {
        this.helper = new FactoryHelper();
    }

    BrokerStoreConfig(ExternalConfiguration config, FactoryHelper helper) {
        this.config = config;
        this.helper = helper;
    }

    @Bean
    @Qualifier(Qualifiers.SERVICE_INSTANCE)
    public BrokerStore<ServiceInstance> getServiceInstanceStore(ZookeeperClient zkClient)
            throws IOException {
        BrokerStore<ServiceInstance> brokerStore =
                new ZookeeperStore<>(zkClient, instanceSerializer, instanceDeserializer);
        return brokerStore;
    }

    @Bean
    @Qualifier(Qualifiers.SERVICE_INSTANCE_BINDING)
    public BrokerStore<CreateServiceInstanceBindingRequest>
    getServiceInstanceBindingStore(ZookeeperClient zkClient) throws IOException {
        BrokerStore<CreateServiceInstanceBindingRequest> brokerStore =
                new ZookeeperStore<>(zkClient, bindingSerializer, bindingDeserializer);
        return brokerStore;
    }

    @Bean
    @Profile("cloud")
    public ZookeeperClient getZKClient() throws  IOException {
        ZookeeperClient zkClient = helper.getZkClientInstance(config.getZkClusterHosts(),
                config.getZkBrokerUserName(),
                config.getZkBrokerUserPass(),
                config.getBrokerStoreNode());
        zkClient.init();
        return zkClient;
    }

    final static class FactoryHelper {
        ZookeeperClient getZkClientInstance(String zkCluster,
                                            String user,
                                            String pass,
                                            String zkNode) throws IOException {
            ZookeeperClient client = new ZookeeperClientBuilder(zkCluster, user, pass, zkNode).build();
            return client;
        }
    }
}