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
package com.github.lburgazzoli.examples.karaf.zookeeper.client.cmd;

import com.github.lburgazzoli.examples.karaf.zookeeper.client.IZKClient;
import com.github.lburgazzoli.examples.karaf.zookeeper.client.ZKPath;
import org.apache.commons.lang3.StringUtils;
import org.apache.karaf.shell.console.Completer;
import org.apache.karaf.shell.console.completer.StringsCompleter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 *
 */
public class ZKPathCompleter implements Completer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZKPathCompleter.class);

    private IZKClient m_client;

    public void setClient(IZKClient client) {
        m_client = client;
    }

    @Override
    public int complete(String buffer, int cursor, List<String> candidates) {
        StringsCompleter delegate = new StringsCompleter();
        String path = null;

        int index = buffer != null ? buffer.lastIndexOf(ZKPath.SEPARATOR) : -1;
        if(index <= 0) {
            path = ZKPath.SEPARATOR;
        } else {
            path = buffer.substring(0,index);

            if(!StringUtils.startsWith(path, ZKPath.SEPARATOR)) {
                path = ZKPath.SEPARATOR + path;
            }
        }

        if(StringUtils.isNotBlank(path)) {
            try {
                for(String item : m_client.list(path)) {
                    if(StringUtils.equals(path,ZKPath.SEPARATOR)) {
                        delegate.getStrings().add(ZKPath.SEPARATOR + item);
                    } else if(StringUtils.endsWith(path,ZKPath.SEPARATOR)) {
                        delegate.getStrings().add(path + item);
                    } else {
                        delegate.getStrings().add(path + ZKPath.SEPARATOR + item);
                    }
                }
            } catch(Exception e) {
                LOGGER.warn("Exception",e);
            }
        }

        return delegate.complete(buffer,cursor,candidates);
    }
}
