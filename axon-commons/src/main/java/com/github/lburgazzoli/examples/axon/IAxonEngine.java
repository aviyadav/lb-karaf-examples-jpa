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
package com.github.lburgazzoli.examples.axon;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventListener;
import org.axonframework.eventsourcing.EventSourcedAggregateRoot;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.repository.Repository;

import java.util.Collection;

/**
 *
 */
public interface IAxonEngine extends CommandGateway {

    /**
     *
     * @return
     */
    public EventStore getEventStore();

    /**
     *
     * @return
     */
    public EventBus getEventBus();

    /**
     *
     * @return
     */
    public CommandBus getCommandBus();

    /**
     *
     * @return
     */
    public Collection<EventSourcingRepository<?>> getRepositories();

    /**
     *
     * @param eventHandler
     */
    public void addEventHandler(Object eventHandler);

    /**
     *
     * @param eventHandler
     */
    public void removeEventHandler(Object eventHandler);

    /**
     *
     * @param eventListener
     */
    public void addEventListener(EventListener eventListener);

    /**
     *
     * @param eventListener
     */
    public void removeEventListener(EventListener eventListener);

    /**
     *
     * @param aggregateType
     */
    public void addAggregateType(Class<? extends EventSourcedAggregateRoot> aggregateType);

    /**
     *
     * @param aggregateType
     */
    public void removeAggregateType(Class<? extends EventSourcedAggregateRoot> aggregateType);
}
