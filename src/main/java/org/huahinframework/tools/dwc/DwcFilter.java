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
package org.huahinframework.tools.dwc;

import java.io.IOException;

import org.huahinframework.core.Filter;
import org.huahinframework.core.io.Record;
import org.huahinframework.core.writer.Writer;
import org.huahinframework.tools.dwc.output.filter.AllOutputter;
import org.huahinframework.tools.dwc.output.filter.CharsLinesOutputter;
import org.huahinframework.tools.dwc.output.filter.CharsOutputter;
import org.huahinframework.tools.dwc.output.filter.LinesOutputter;
import org.huahinframework.tools.dwc.output.filter.WordsLinesOutputter;
import org.huahinframework.tools.dwc.output.filter.WordsOutputter;
import org.huahinframework.tools.util.Outputter;

/**
 *
 */
public class DwcFilter extends Filter {
    private Outputter outputter;

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
        outputter.output(writer, record);
    }

    /* (non-Javadoc)
     * @see org.huahinframework.core.Filter#filterSetup()
     */
    @Override
    public void filterSetup() {
        boolean chars = getBooleanParameter(Dwc.CHARS);
        boolean words = getBooleanParameter(Dwc.WORDS);
        boolean lines = getBooleanParameter(Dwc.LINES);
        if (chars && words && lines) {
            outputter = new AllOutputter();
        } else if (!chars && !words && !lines) {
            outputter = new AllOutputter();
        } else if (chars && words) {
            outputter = new AllOutputter();
        } else if (chars && lines) {
            outputter = new CharsLinesOutputter();
        } else if (words && lines) {
            outputter = new WordsLinesOutputter();
        } else if (chars) {
            outputter = new CharsOutputter();
        } else if (words) {
            outputter = new WordsOutputter();
        } else if (lines) {
            outputter = new LinesOutputter();
        }
    }
}
