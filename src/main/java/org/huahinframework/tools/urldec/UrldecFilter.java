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
package org.huahinframework.tools.urldec;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.huahinframework.core.Filter;
import org.huahinframework.core.io.Record;
import org.huahinframework.core.writer.Writer;
import org.huahinframework.tools.cut.Cut;
import org.huahinframework.tools.urlsw.Urlsw;
import org.huahinframework.tools.util.Decoder;

/**
 *
 */
public class UrldecFilter extends Filter {
    private Map<String, Boolean> filedsMap = new HashMap<String, Boolean>();
    private String separator;

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
        Record emitRecord = new Record();
        emitRecord.setValueNothing(true);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < record.sizeValue(); i++) {
            String label = String.valueOf(i);
            String value = record.getValueString(label);
            if (filedsMap.get(label) != null) {
                sb.append(Decoder.urlDecoder(value)).append(separator);
            } else {
                sb.append(value).append(separator);
            }
        }

        emitRecord.addGrouping("VALUE", sb.substring(0, sb.length() - separator.length()));
        writer.write(emitRecord);
    }

    /* (non-Javadoc)
     * @see org.huahinframework.core.Filter#filterSetup()
     */
    @Override
    public void filterSetup() {
        String[] f = getStringsParameter(Urlsw.FIELDS);
        for (String s : f) {
            int i = Integer.valueOf(s);
            filedsMap.put(String.valueOf(--i), true);
        }

        separator = getStringParameter(Cut.SEPARATOR);
        if (separator == null) {
            separator = "\t";
        }
    }
}
