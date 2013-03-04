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
package org.huahinframework.tools.formatting;

import java.io.IOException;

import org.huahinframework.core.io.Record;
import org.huahinframework.core.writer.Writer;
import org.huahinframework.tools.util.Outputter;

/**
 *
 */
public class NumberOutputter implements Outputter {
    private int[] number;

    public NumberOutputter(int[] number) {
        this.number = number;
    }

    @Override
    public void output(Writer writer, Record record)
            throws IOException, InterruptedException {
        try {
            Record emitRecord = new Record();
            for (int i : number) {
                String s = null;
                try {
                    s = record.getValueString(String.valueOf(i));
                } catch (ClassCastException e) {
                }
                emitRecord.addGrouping(String.valueOf(i), s);
            }
            emitRecord.setValueNothing(true);
            writer.write(emitRecord);
        } catch (ClassCastException e) {
            return;
        }
    }
}
