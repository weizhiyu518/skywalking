/*
 * Copyright 2017, OpenSkywalking Organization All rights reserved.
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
 *
 * Project repository: https://github.com/OpenSkywalking/skywalking
 */

package org.skywalking.apm.collector.ui.jetty.handler;

import com.google.gson.JsonElement;
import javax.servlet.http.HttpServletRequest;
import org.skywalking.apm.collector.server.jetty.ArgumentsParseException;
import org.skywalking.apm.collector.server.jetty.JettyHandler;
import org.skywalking.apm.collector.storage.service.DAOService;
import org.skywalking.apm.collector.ui.service.CacheServiceManager;
import org.skywalking.apm.collector.ui.service.TraceStackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author peng-yongsheng
 */
public class TraceStackGetHandler extends JettyHandler {

    private final Logger logger = LoggerFactory.getLogger(TraceStackGetHandler.class);

    @Override public String pathSpec() {
        return "/traceStack/globalTraceId";
    }

    private final TraceStackService service;

    public TraceStackGetHandler(DAOService daoService, CacheServiceManager cacheServiceManager) {
        this.service = new TraceStackService(daoService, cacheServiceManager);
    }

    @Override protected JsonElement doGet(HttpServletRequest req) throws ArgumentsParseException {
        String globalTraceId = req.getParameter("globalTraceId");
        logger.debug("globalTraceId: {}", globalTraceId);

        return service.load(globalTraceId);
    }

    @Override protected JsonElement doPost(HttpServletRequest req) throws ArgumentsParseException {
        throw new UnsupportedOperationException();
    }
}