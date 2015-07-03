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

package com.intel.tapaas.servicebroker.yarn.service;

import com.intel.tapaas.servicebroker.yarn.config.Application;
import com.intel.tapaas.servicebroker.yarn.config.ExternalConfiguration;
import com.intel.tapaas.servicebroker.yarn.service.utils.YarnTestUtils;

import org.apache.curator.test.TestingServer;
import org.cloudfoundry.community.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.cloudfoundry.community.servicebroker.model.CreateServiceInstanceRequest;
import org.cloudfoundry.community.servicebroker.model.ServiceDefinition;
import org.cloudfoundry.community.servicebroker.model.ServiceInstance;
import org.cloudfoundry.community.servicebroker.service.ServiceInstanceBindingService;
import org.cloudfoundry.community.servicebroker.service.ServiceInstanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, ConfigurationTest.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@IntegrationTest
@ActiveProfiles("test")
public class YarnServiceInstanceBindingServiceTest {

    @Autowired
    private TestingServer zkServer;

    @Autowired
    private ExternalConfiguration conf;

    @Autowired
    private ServiceInstanceService serviceBean;

    @Autowired
    private ServiceInstanceBindingService bindingBean;

    @Test
    public void testCreateServiceInstance() throws Exception {
//        ServiceInstance instance = getServiceInstance("id");
//        CreateServiceInstanceRequest request = new CreateServiceInstanceRequest(
//                getServiceDefinition().getId(),
//                instance.getPlanId(),
//                instance.getOrganizationGuid(),
//                instance.getSpaceGuid()).withServiceInstanceId(
//                instance.getServiceInstanceId()).withServiceDefinition(getServiceDefinition());
//
//        CreateServiceInstanceBindingRequest bindReq = new CreateServiceInstanceBindingRequest(
//                getServiceInstance("serviceId").getServiceDefinitionId(), "planId", "appGuid").
//                withBindingId("bindingId").withServiceInstanceId("serviceId");
//
//        serviceBean.createServiceInstance(request);
//
//        bindingBean.createServiceInstanceBinding(bindReq);
    }


    private ServiceInstance getServiceInstance(String id) {
        return new ServiceInstance(
                new CreateServiceInstanceRequest(getServiceDefinition().getId(), "planId", "organizationGuid", "spaceGuid")
                        .withServiceInstanceId(id));
    }


    private ServiceDefinition getServiceDefinition() {
        return new ServiceDefinition("def", "name", "desc", true, Collections.emptyList());
    }
}