/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package lb.examples.karaf.jpa.kundera.adapter;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.util.Hashtable;

/**
 *
 */
public class Activator  implements BundleActivator {

    private static BundleContext context = null;
    private static ServiceRegistration serviceReg = null;

    @Override
    public void start(BundleContext context) throws Exception {
        this.context = context;

        Hashtable<String,String> props = new Hashtable<String, String>();
        props.put(
            "javax.persistence.provider",
            "com.impetus.kundera.KunderaPersistence");
        props.put(
            "javax.persistence.spi.PersistenceProvider",
            "com.impetus.kundera.KunderaPersistence");
        props.put(
            "javax.persistence.PersistenceProvider",
            "com.impetus.kundera.KunderaPersistence");

        serviceReg = context.registerService(
            "javax.persistence.spi.PersistenceProvider",
            new com.impetus.kundera.KunderaPersistence(),
            props);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        if (this.serviceReg != null) {
            this.serviceReg.unregister();
            this.serviceReg = null;
        }

        this.context = null;
    }
}
