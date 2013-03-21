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
package org.huahinframework.tools.urlsw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.huahinframework.core.Filter;
import org.huahinframework.core.io.Record;
import org.huahinframework.core.util.StringUtil;
import org.huahinframework.core.writer.Writer;
import org.huahinframework.tools.util.ParseResult;
import org.huahinframework.tools.util.SearchEngine;
import org.huahinframework.tools.util.WordUtils;

/**
 *
 */
public class UrlswFilter extends Filter {
    private String[] fileds;
    private Map<String, List<SearchEngine>> searchEngineQueryMap = new HashMap<String, List<SearchEngine>>();

    /* (non-Javadoc)
     * @see org.huahinframework.core.Filter#init()
     */
    @Override
    public void init() {
    }

    /* (non-Javadoc)
     * @see org.huahinframework.core.Filter#filter(org.huahinframework.core.io.Record, org.huahinframework.core.writer.Writer)
     */
    @Override
    public void filter(Record record, Writer writer)
            throws IOException, InterruptedException {
        List<String> l = new ArrayList<String>();
        for (String s : fileds) {
            ParseResult result =
                    WordUtils.parseKeyWord(record.getValueString(s), searchEngineQueryMap);
            if (result.isSucseed()) {
                String[] split1 = StringUtil.split(result.getResult(), " ", true);
                for (String t : split1) {
                    String[] split2 = StringUtil.split(t, "　", true);
                    for (String u : split2) {
                        l.add(u);
                    }
                }
            }
        }

        Record emitRecord = new Record();
        emitRecord.setValueNothing(true);
        int i;
        for (i = 0; i < record.sizeValue(); i++) {
            emitRecord.addGrouping(String.valueOf(i), record.getValueString(String.valueOf(i)));
        }
        for (int j = 0; j < l.size(); j++) {
            emitRecord.addGrouping(String.valueOf(++i), l.get(j));
        }

        writer.write(emitRecord);
    }

    /* (non-Javadoc)
     * @see org.huahinframework.core.Filter#filterSetup()
     */
    @Override
    public void filterSetup() {
        String[] f = getStringsParameter(Urlsw.FIELDS);
        fileds = new String[f.length];
        for (int i = 0; i < f.length; i++) {
            fileds[i] = String.valueOf(Integer.valueOf(f[i]) - 1);
        }

        String[] m = getStringsParameter(Urlsw.MASTER);
        for (String s : m) {
            String[] line = StringUtil.split(s, StringUtil.TAB, true);
            List<SearchEngine> l = searchEngineQueryMap.get(line[0]);
            if (l == null) {
                l = new ArrayList<SearchEngine>();
                searchEngineQueryMap.put(line[0], l);
            }
            searchEngineQueryMap.get(line[0]).add(new SearchEngine(line[1], line[2]));
        }
    }
}
