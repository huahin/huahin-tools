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
package org.huahinframework.tools.ccext;

import java.io.IOException;

import org.huahinframework.core.io.Record;
import org.huahinframework.core.writer.Writer;
import org.huahinframework.tools.util.Outputter;

/**
 *
 */
public class NormalOutputter implements Outputter {
    private String separator;
    private int number;

    public NormalOutputter(String separator, int number) {
        this.number = number;
        this.separator = separator;
    }

    /* (non-Javadoc)
     * @see org.huahinframework.tools.util.Outputter#output(org.huahinframework.core.writer.Writer, org.huahinframework.core.io.Record)
     */
    @Override
    public void output(Writer writer, Record record)
            throws IOException, InterruptedException {
        if (record.sizeValue() != number) {
            return;
        }

        Record emitRecord = new Record();
        emitRecord.setValueNothing(true);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < record.sizeValue(); i++) {
            String s = null;
            try {
                s = record.getValueString(String.valueOf(i));
            } catch (ClassCastException e) {
            }
            sb.append(s).append(separator);
        }

        emitRecord.addGrouping("VALUE", sb.substring(0, sb.length() - separator.length()));
        writer.write(emitRecord);
    }
}
