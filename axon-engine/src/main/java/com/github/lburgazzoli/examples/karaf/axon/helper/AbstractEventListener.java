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
package com.github.lburgazzoli.examples.karaf.axon.helper;


import org.axonframework.domain.EventMessage;
import org.axonframework.eventhandling.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public abstract class AbstractEventListener<T> implements EventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractEventListener.class);

    private final Class<T> m_type;

    /**
     *
     * @param type
     */
    public AbstractEventListener(Class<T> type) {
        m_type = type;
    }

    /**
     *
     * @param event
     */
    @Override
    public void handle(EventMessage event) {
        if(m_type.equals(event.getPayloadType())) {
            doHandle(m_type.cast(event.getPayload()));
        }
    }

    /**
     *
     * @param data
     */
    protected abstract void doHandle(T data);
}
