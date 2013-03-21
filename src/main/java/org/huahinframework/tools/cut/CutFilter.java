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
package org.huahinframework.tools.cut;

import java.io.IOException;

import org.huahinframework.core.Filter;
import org.huahinframework.core.io.Record;
import org.huahinframework.core.writer.Writer;
import org.huahinframework.tools.urlsw.Urlsw;

/**
 *
 */
public class CutFilter extends Filter {
    private String[] fileds;

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
        for (String s : fileds) {
            emitRecord.addGrouping(s, record.getValueString(s));
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
    }
}
