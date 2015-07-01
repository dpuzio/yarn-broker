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

import com.intel.tapaas.hadoop.kerberos.KrbLoginManager;
import com.intel.tapaas.hadoop.kerberos.KrbLoginManagerFactory;
import com.intel.tapaas.servicebroker.yarn.service.YarnServiceInstanceBindingService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Profile(Profiles.CLOUD)
@org.springframework.context.annotation.Configuration
public class YarnConfiguration {

    private final static Logger LOGGER = LoggerFactory.getLogger(YarnConfiguration.class);

    private final static String AUTHENTICATION_METHOD = "kerberos";

    private final static String AUTHENTICATION_METHOD_PROPERTY = "hadoop.security.authentication";

    @Autowired
    private ExternalConfiguration configuration;

    @Autowired
    private Configuration hadoopConf;

    @Bean
    public FileSystem getFileSystem() throws InterruptedException,
            URISyntaxException, LoginException, IOException {

        if(AUTHENTICATION_METHOD.equals(hadoopConf.get(AUTHENTICATION_METHOD_PROPERTY))) {
            return getSecureFileSystem();
        } else {
            return getInsecureFileSystem();
        }
    }

    private FileSystem getSecureFileSystem() throws InterruptedException,
            URISyntaxException, LoginException, IOException {
        LOGGER.info("Trying kerberos auth");

        KrbLoginManager loginManager =
                KrbLoginManagerFactory.getInstance().getKrbLoginManagerInstance(
                        configuration.getKerberosKdc(),
                        configuration.getKerberosRealm());
        loginManager.loginInHadoop(loginManager.loginWithCredentials(
                configuration.getBrokerUserName(),
                configuration.getBrokerUserPassword().toCharArray()), hadoopConf);
        LOGGER.info("Creating filesytem with kerberos auth");
        return FileSystem.get(hadoopConf);
    }

    /**
     * TODO: This method instead of configuration.getBrokerUserName() should have something like :
     * OAuthTicket.getUserName()
     */
    private FileSystem getInsecureFileSystem() throws InterruptedException,
            URISyntaxException, LoginException, IOException {
        LOGGER.info("Creating filesytem without kerberos auth");
        return FileSystem.get(
                new URI(hadoopConf.getRaw(YarnServiceInstanceBindingService.HADOOP_DEFAULT_FS)),
                hadoopConf, configuration.getBrokerUserName());
    }

}
