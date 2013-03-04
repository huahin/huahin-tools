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

import org.huahinframework.core.Filter;
import org.huahinframework.core.io.Record;
import org.huahinframework.core.util.StringUtil;
import org.huahinframework.core.writer.Writer;
import org.huahinframework.tools.util.Outputter;

/**
 *
 */
public class FormattingFilter extends Filter {
    private Outputter outputter;
    private boolean isGroupNumber;
    private int groupNumber;

    @Override
    public void filter(Record record, Writer writer)
            throws IOException, InterruptedException {
        if (isGroupNumber) {
            if (groupNumber != record.sizeValue()) {
                return;
            }
        }

        outputter.output(writer, record);
    }

    @Override
    public void filterSetup() {
        String o = getStringParameter(Formatting.OUTPUTS);
        if (o != null) {
            String[] s = StringUtil.split(o);
            int[] number = new int[s.length];
            for (int i = 0; i < s.length; i++) {
                number[i] = Integer.valueOf(s[i]);
            }

            outputter = new NumberOutputter(number);
        } else {
            outputter = new AllOutputter();
        }

        String g = getStringParameter(Formatting.GROUP_NUMBER);
        if (g != null) {
            isGroupNumber = true;
            groupNumber = Integer.valueOf(g);
        }
    }

    @Override
    public void init() {
    }
}
