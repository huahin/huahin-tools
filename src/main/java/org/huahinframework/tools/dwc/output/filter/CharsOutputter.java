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
package org.huahinframework.tools.dwc.output.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.huahinframework.core.io.Record;
import org.huahinframework.core.writer.Writer;
import org.huahinframework.tools.util.Outputter;

/**
 *
 */
public class CharsOutputter implements Outputter {
    private Map<String, Boolean> fileMap = new HashMap<String, Boolean>();

    /* (non-Javadoc)
     * @see org.huahinframework.tools.util.Outputter#output(org.huahinframework.core.writer.Writer, org.huahinframework.core.io.Record)
     */
    @Override
    public void output(Writer writer, Record record)
            throws IOException, InterruptedException {
        String fileName = record.getGroupingString("FILE_NAME");
        if (fileMap.get(fileName) != null) {
            return;
        }
        fileMap.put(fileName, true);

        Record emitRecord = new Record();
        emitRecord.addGrouping("FILE_NAME", record.getGroupingString("FILE_NAME"));
        emitRecord.addValue("FILE_LENGTH", record.getGroupingLong("FILE_LENGTH"));
        writer.write(emitRecord);
    }
}
