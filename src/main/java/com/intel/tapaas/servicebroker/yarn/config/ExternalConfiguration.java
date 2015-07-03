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

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!Cloud")
@Configuration
public class ExternalConfiguration {

    @Value("${zk.cluster}")
    @NotNull
    private String zkClusterHosts;

    @Value("${cf.serviceid}")
    @NotNull
    private String cfServiceId;

    @Value("${cf.servicename}")
    @NotNull
    private String cfServiceName;

    @Value("${broker.store.node}")
    @NotNull
    private String brokerStoreNode;

    @Value("${broker.root.node}")
    @NotNull
    private String brokerRootNode;

    @Value("${yarn.brokerusername}")
    @NotNull
    private String zkBrokerUserName;

    @Value("${yarn.brokeruserpass}")
    @NotNull
    private String zkBrokerUserPass;

    @Value("${yarn.provided.params}")
    private String hadoopProvidedParams;

    public String getCfServiceName() {
        return cfServiceName;
    }

    public void setCfServiceName(String cfServiceName) {
        this.cfServiceName = cfServiceName;
    }

    public String getCfServiceId() {
        return cfServiceId;
    }

    public void setCfServiceId(String cfServiceId) {
        this.cfServiceId = cfServiceId;
    }

    public String getHadoopProvidedParams() {
        return hadoopProvidedParams;
    }

    public void setHadoopProvidedParams(String hadoopProvidedParams) {
        this.hadoopProvidedParams = hadoopProvidedParams;
    }

    public String getZkClusterHosts() {
        return zkClusterHosts;
    }

    public void setZkClusterHosts(String zkClusterHosts) {
        this.zkClusterHosts = zkClusterHosts;
    }

    public String getZkBrokerUserName() {
        return zkBrokerUserName;
    }

    public void setZkBrokerUserName(String zkBrokerUserName) {
        this.zkBrokerUserName = zkBrokerUserName;
    }

    public String getZkBrokerUserPass() {
        return zkBrokerUserPass;
    }

    public void setZkBrokerUserPass(String zkBrokerUserPass) {
        this.zkBrokerUserPass = zkBrokerUserPass;
    }

    public String getBrokerStoreNode() {
        return brokerStoreNode;
    }

    public void setBrokerStoreNode(String brokerStoreNode) {
        this.brokerStoreNode = brokerStoreNode;
    }

    public String getBrokerRootNode() {
        return brokerRootNode;
    }

    public void setBrokerRootNode(String brokerRootNode) {
        this.brokerRootNode = brokerRootNode;
    }

}