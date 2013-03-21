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
package org.huahinframework.tools.wc;

import java.io.IOException;

import org.huahinframework.core.Summarizer;
import org.huahinframework.core.io.Record;
import org.huahinframework.core.writer.Writer;
import org.huahinframework.tools.wc.output.summarizer.AllOutputter;
import org.huahinframework.tools.wc.output.summarizer.CharsLinesOutputter;
import org.huahinframework.tools.wc.output.summarizer.CharsOutputter;
import org.huahinframework.tools.wc.output.summarizer.CharsWordsOutputter;
import org.huahinframework.tools.wc.output.summarizer.LinesOutputter;
import org.huahinframework.tools.wc.output.summarizer.Outputter;
import org.huahinframework.tools.wc.output.summarizer.WordsLinesOutputter;
import org.huahinframework.tools.wc.output.summarizer.WordsOutputter;

/**
 *
 */
public class WcSummarizer extends Summarizer {
    private Outputter outputter;

    /* (non-Javadoc)
     * @see org.huahinframework.core.Summarizer#init()
     */
    @Override
    public void init() {
    }

    /* (non-Javadoc)
     * @see org.huahinframework.core.Summarizer#summarize(org.huahinframework.core.writer.Writer)
     */
    @Override
    public void summarize(Writer writer)
            throws IOException, InterruptedException {
        while(hasNext()) {
            Record r = next(writer);
            outputter.summarize(r);
        }
        outputter.output(writer);
    }

    /* (non-Javadoc)
     * @see org.huahinframework.core.Summarizer#summarizerSetup()
     */
    @Override
    public void summarizerSetup() {
        boolean chars = getBooleanParameter(Wc.CHARS);
        boolean words = getBooleanParameter(Wc.WORDS);
        boolean lines = getBooleanParameter(Wc.LINES);
        if (chars && words && lines) {
            outputter = new AllOutputter();
        } else if (!chars && !words && !lines) {
            outputter = new AllOutputter();
        } else if (chars && words) {
            outputter = new CharsWordsOutputter();
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
