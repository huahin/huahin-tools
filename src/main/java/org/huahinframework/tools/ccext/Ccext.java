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
public class Ccext extends ToolsTool {
    public static final String NUMBER = "NUMBER";
    public static final String REVERSE = "REVERSE";
    public static final String SEPARATOR = "SEPARATOR";

    private CcextToolsOptionUtil tot;

    @Override
    protected void setup() throws Exception {
        String separator = "\t";
        if (tot.isDelimiter()) {
            if (!tot.getDelimiter().equals("\\t")) {
                separator = tot.getDelimiter();
                conf.set(SEPARATOR, separator);
            }
        }

        SimpleJob job = addJob(separator);
        if (!opt.isLocalMode()) {
            job.setFilter(CcextFilter.class);
        } else {
            job.setMapperClass(MultithreadedMapper.class);
            MultithreadedMapper.setMapperClass(job, CcextFilter.class);
            MultithreadedMapper.setNumberOfThreads(job, opt.getThreadNumber());

            SimpleTextInputFormat.setMinInputSplitSize(job, opt.getSplitSize());
            SimpleTextInputFormat.setMaxInputSplitSize(job, opt.getSplitSize());
        }

        if (!tot.isNumber()) {
            throw new RuntimeException("-n(number) not found");
        }

        int number = 0;
        try {
            number = Integer.valueOf(tot.getNumber());
        } catch (NumberFormatException e) {
            throw new RuntimeException("-n(number) is not integer");
        }

        job.setParameter(NUMBER, number);

        if (tot.isReverser()) {
            job.setParameter(REVERSE, true);
        }
    }

    @Override
    protected ToolsOptionUtil setToolsOptionUtil(OptionUtil opt)
            throws ParseException {
        tot = new CcextToolsOptionUtil(opt.getArgs());
        return tot;
    }
}
