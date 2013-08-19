/**
 *
 * Copyright 2013 lb
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.lburgazzoli.examples.karaf.zookeeper.client;


import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;

/**
 *
 */
public class ZKClientBuilder {

    private CuratorFrameworkFactory.Builder m_builder;

    /**
     * c-tor
     */
    public ZKClientBuilder() {
        m_builder = CuratorFrameworkFactory.builder();
    }

    /**
     *
     * @param connectStrin
     */
    public void setConnectString(String connectStrin) {
        m_builder.connectString(connectStrin);
    }

    /**
     *
     * @param retryPolicy
     */
    public void setRetryPolicy(RetryPolicy retryPolicy) {
        m_builder.retryPolicy(retryPolicy);
    }

    /**
     *
     * @param connectionTimeoutMs
     */
    public void setConnectionTimeoutMs(int connectionTimeoutMs) {
        m_builder.connectionTimeoutMs(connectionTimeoutMs);
    }

    /**
     *
     * @param sessionTimeoutMs
     */
    public void setSessionTimeoutMs(int sessionTimeoutMs) {
        m_builder.sessionTimeoutMs(sessionTimeoutMs);
    }

    /**
     *
     * @return
     */
    protected CuratorFramework build() {
        return m_builder.build();
    }

}

