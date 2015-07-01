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

@Profile(Profiles.CLOUD)
@Configuration
public class ExternalConfiguration {

    @Value("${yarn.brokerusername}")
    @NotNull
    private String brokerUserName;

    @Value("${yarn.brokeruserpass}")
    @NotNull
    private String brokerUserPassword;

    @Value("${yarn.binding.xattr}")
    @NotNull
    private String bindingXattr;

    @Value("${yarn.instance.xattr}")
    @NotNull
    private String instanceXattr;

    @Value("${yarn.binding.chroot}")
    @NotNull
    private String bindingChroot;

    @Value("${yarn.instance.chroot}")
    @NotNull
    private String instanceChroot;

    @Value("${kerberos.kdc}")
    @NotNull
    private String kerberosKdc;

    @Value("${kerberos.realm}")
    @NotNull
    private String kerberosRealm;

    @Value("${cf.servicename}")
    @NotNull
    private String cfServiceName;

    @Value("${cf.serviceid}")
    @NotNull
    private String cfServiceId;

    @Value("${yarn.provided.params}")
    private String hadoopProvidedParams;
    public String getBrokerUserName() {
        return brokerUserName;
    }

    public void setBrokerUserName(String brokerUserName) {
        this.brokerUserName = brokerUserName;
    }

    public String getBindingXattr() {
        return bindingXattr;
    }

    public void setBindingXattr(String bindingXattr) {
        this.bindingXattr = bindingXattr;
    }

    public String getInstanceXattr() {
        return instanceXattr;
    }

    public void setInstanceXattr(String instanceXattr) {
        this.instanceXattr = instanceXattr;
    }

    public String getBindingChroot() {
        return bindingChroot;
    }

    public void setBindingChroot(String bindingChroot) {
        this.bindingChroot = bindingChroot;
    }

    public String getInstanceChroot() {
        return instanceChroot;
    }

    public void setInstanceChroot(String instanceChroot) {
        this.instanceChroot = instanceChroot;
    }

    public String getKerberosKdc() {
        return kerberosKdc;
    }

    public void setKerberosKdc(String kerberosKdc) {
        this.kerberosKdc = kerberosKdc;
    }

    public String getKerberosRealm() {
        return kerberosRealm;
    }

    public void setKerberosRealm(String kerberosRealm) {
        this.kerberosRealm = kerberosRealm;
    }

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

    public String getBrokerUserPassword() {
        return brokerUserPassword;
    }

    public void setBrokerUserPassword(String brokerUserPassword) {
        this.brokerUserPassword = brokerUserPassword;
    }
}