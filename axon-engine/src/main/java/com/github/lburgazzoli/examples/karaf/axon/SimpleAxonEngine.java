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
package com.github.lburgazzoli.examples.karaf.axon;

import com.github.lburgazzoli.examples.karaf.axon.data.DataItem;
import com.google.common.collect.Sets;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.common.Subscribable;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventListener;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class SimpleAxonEngine implements IAxonEngine {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleAxonEngine.class);

    private CommandBus m_commandBus;
    private CommandGateway m_commandGateway;
    private EventStore m_eventStore;
    private EventBus m_eventBus;

    private EventSourcingRepository m_eventRepository;
    private Subscribable m_commandSubscriptionHandler;
    private Set<EventListener> m_eventListeners;

    /**

     * c-tor
     */
    public SimpleAxonEngine() {
        m_commandBus = null;
        m_commandGateway = null;
        m_eventStore = null;
        m_eventBus = null;
        m_eventRepository = null;
        m_commandSubscriptionHandler = null;
        m_eventListeners = Sets.newHashSet();
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     */
    public void init() {
        if(m_eventRepository == null) {
            m_eventRepository = new EventSourcingRepository(DataItem.class);
            m_eventRepository.setEventStore(m_eventStore);
            m_eventRepository.setEventBus(m_eventBus);
        }

        if(m_commandSubscriptionHandler == null) {
            m_commandSubscriptionHandler =
                AggregateAnnotationCommandHandler.subscribe(
                    DataItem.class,
                    m_eventRepository,
                    m_commandBus);
        }
    }

    /**
     *
     */
    public void destroy() {
        if(m_commandSubscriptionHandler != null) {
            m_commandSubscriptionHandler.unsubscribe();
            m_commandSubscriptionHandler = null;
        }

        for(EventListener listener : m_eventListeners) {
            m_eventBus.unsubscribe(listener);
        }

        m_eventListeners.clear();
        m_eventRepository = null;
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @param commandGateway
     */
    public void setCommandGateway(CommandGateway commandGateway) {
        m_commandGateway = commandGateway;
    }

    /**
     *
     * @param commandBus
     */
    public void setCommandBus(CommandBus commandBus) {
        m_commandBus = commandBus;
    }

    /**
     *
     * @param eventBus
     */
    public void setEventBus(EventBus eventBus) {
        m_eventBus = eventBus;
    }

    /**
     *
     * @param eventStore
     */
    public void setEventStore(EventStore eventStore) {
        m_eventStore = eventStore;
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @param eventListener
     */
    public void bind(EventListener eventListener,Map properties) {
        LOGGER.debug("<<<<  BIND  >>>> {},{}",eventListener,properties);
        if(m_eventListeners.add(eventListener)) {
            m_eventBus.subscribe(eventListener);
        }
    }

    /**
     *
     * @param eventListener
     */
    public void unbind(EventListener eventListener,Map properties) {
        LOGGER.debug("<<<< UNBIND >>>> {},{}",eventListener,properties);
        if(eventListener != null) {
            m_eventBus.unsubscribe(eventListener);
        }
    }

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public void send(Object command) {
        m_commandGateway.send(command);
    }

    @Override
    public <R> void send(Object command, CommandCallback<R> callback) {
        m_commandGateway.send(command,callback);
    }

    @Override
    public <R> R sendAndWait(Object command) {
        return m_commandGateway.sendAndWait(command);
    }

    @Override
    public <R> R sendAndWait(Object command, long timeout, TimeUnit unit) {
        return m_commandGateway.sendAndWait(command,timeout,unit);
    }
}
