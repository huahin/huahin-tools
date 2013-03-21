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
package org.huahinframework.tools.wc.output.summarizer;

import java.io.IOException;

import org.huahinframework.core.io.Record;
import org.huahinframework.core.writer.Writer;
import org.huahinframework.tools.wc.Wc;

/**
 *
 */
public class CharsOutputter implements Outputter {
    private String fileName;
    private long chars;

    /* (non-Javadoc)
     * @see org.huahinframework.tools.dwc.output.summarizer.Outputter#summarize(org.huahinframework.core.io.Record)
     */
    @Override
    public void summarize(Record record) {
        fileName = record.getGroupingString("FILE_NAME");
        chars = record.getValueLong("FILE_LENGTH");
    }

    /* (non-Javadoc)
     * @see org.huahinframework.tools.dwc.output.summarizer.Outputter#output(org.huahinframework.core.writer.Writer)
     */
    @Override
    public void output(Writer writer)
            throws IOException, InterruptedException {
        Record r = new Record();
        r.addGrouping(Wc.CHARS, chars);
        r.addGrouping(Wc.CHARS, fileName);
        r.setValueNothing(true);
        writer.write(r);
    }
}
