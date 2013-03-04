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

import org.apache.commons.cli.ParseException;
import org.apache.hadoop.mapreduce.lib.map.MultithreadedMapper;
import org.huahinframework.core.SimpleJob;
import org.huahinframework.core.lib.input.SimpleTextInputFormat;
import org.huahinframework.core.util.OptionUtil;
import org.huahinframework.tools.util.ToolsOptionUtil;
import org.huahinframework.tools.util.ToolsTool;

/**
 *
 */
public class Dwc extends ToolsTool {
    public static final String CHARS = "CHARS";
    public static final String WORDS = "WORDS";
    public static final String LINES = "LINES";

    private DwcToolsOptionUtil tot;

    @Override
    protected void setup() throws Exception {
        SimpleJob job = addJob("\n");
        if (!opt.isLocalMode()) {
            job.setFilter(DwcFilter.class);
            job.setSummarizer(DwcSummarizer.class);
        } else {
            job.setMapperClass(MultithreadedMapper.class);
            MultithreadedMapper.setMapperClass(job, DwcFilter.class);
            MultithreadedMapper.setNumberOfThreads(job, opt.getThreadNumber());

            job.setSummarizer(DwcSummarizer.class);

            SimpleTextInputFormat.setMinInputSplitSize(job, opt.getSplitSize());
            SimpleTextInputFormat.setMaxInputSplitSize(job, opt.getSplitSize());
        }

        if (tot.isChars()) {
            job.setParameter(CHARS, true);
        }

        if (tot.isWords()) {
            job.setParameter(WORDS, true);
        }

        if (tot.isLines()) {
            job.setParameter(LINES, true);
        }
    }

    @Override
    protected ToolsOptionUtil setToolsOptionUtil(OptionUtil opt)
            throws ParseException {
        tot = new DwcToolsOptionUtil(opt.getArgs());
        return tot;
    }
}
