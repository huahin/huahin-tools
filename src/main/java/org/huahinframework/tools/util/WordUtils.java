/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.huahinframework.tools.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.huahinframework.tools.util.ParseResult.State;

/**
 *
 */
public class WordUtils {
    private static final String SPACE = " ";

    /**
     * @param url
     * @param searchEngineQueryMap
     * @return
     */
    public static ParseResult parseKeyWord(String url, Map<String, List<SearchEngine>> searchEngineQueryMap) {
        URI uri = null;
        try {
            uri = URI.create(url);
        } catch (Exception e) {
            return new ParseResult(State.INVALID_URI, null);
        }

        String host = uri.getHost();
        List<SearchEngine> ses = searchEngineQueryMap.get(host);
        if (ses == null) {
            return new ParseResult(State.URL_NOT_FOUND, null);
        }

        List<String> querys = new ArrayList<String>();
        for (SearchEngine se : ses) {
            if (se == null ||
                (!se.getPath().isEmpty() && !se.getPath().equals(uri.getPath()))) {
                continue;
            }
            querys.add(se.getQuery());
        }

        if (querys.isEmpty()) {
            return new ParseResult(State.URL_NOT_FOUND, null);
        }

        if (URI.create(url).getQuery() == null) {
            return new ParseResult(State.KEYWORD_NOT_FOUND, null);
        }

        String keyword = null;
        for (String query : querys) {
            Map<String, String> keywordMap = null;
            try {
                keywordMap =
                    uriParameters(Decoder.getDecodeQuery(url));
            } catch (UnsupportedEncodingException e) {
                return new ParseResult(State.DECODE_FAILER, null);
            }

            keyword = keywordMap.get(query);
            if (keyword != null) {
                break;
            }
        }

        if (keyword == null) {
            return new ParseResult(State.KEYWORD_NOT_FOUND, null);
        }

        return new ParseResult(State.SUCESS, keyword);
    }

    /**
     * @param value
     * @return Map<String, String>
     */
    public static Map<String, String> uriParameters(String value) {
        Map<String, String> m = new HashMap<String, String>();
        String[] querys = value.split("&", -1);
        for (String t : querys) {
            String[] u = t.split("=", -1);
            if (u.length != 2) {
                continue;
            }

            String s = m.get(u[0]);
            if (s != null) {
                s = s + SPACE + u[1];
            } else {
                s = u[1];
            }

            m.put(u[0], s);
        }

        return m;
    }
}
