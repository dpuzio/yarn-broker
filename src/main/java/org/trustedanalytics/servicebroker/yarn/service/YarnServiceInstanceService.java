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

package org.trustedanalytics.servicebroker.yarn.service;

import org.trustedanalytics.cfbroker.store.impl.ForwardingServiceInstanceServiceStore;
import org.trustedanalytics.servicebroker.yarn.paramstest.TestClass;

import java.util.Map;

import org.cloudfoundry.community.servicebroker.exception.ServiceBrokerException;
import org.cloudfoundry.community.servicebroker.exception.ServiceInstanceExistsException;
import org.cloudfoundry.community.servicebroker.model.CreateServiceInstanceRequest;
import org.cloudfoundry.community.servicebroker.model.ServiceInstance;
import org.cloudfoundry.community.servicebroker.service.ServiceInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YarnServiceInstanceService extends ForwardingServiceInstanceServiceStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(YarnServiceInstanceService.class);
    
    public YarnServiceInstanceService(ServiceInstanceService delegate) {
        super(delegate);
    }
    
    /* (non-Javadoc)
     * @see org.trustedanalytics.cfbroker.store.impl.ForwardingServiceInstanceServiceStore#createServiceInstance(org.cloudfoundry.community.servicebroker.model.CreateServiceInstanceRequest)
     */
    @Override
    public ServiceInstance createServiceInstance(CreateServiceInstanceRequest request)
            throws ServiceInstanceExistsException, ServiceBrokerException {
        
        Map<String, Object> parameters = request.getParameters();
        LOGGER.info("PARAMETERS (using Map<String, Object>): " + parameters);
        
        TestClass params = request.getParameters(TestClass.class);
        LOGGER.info("PARAMETERS (using custom class): " + params);
        
        return super.createServiceInstance(request);
    }
    
}
