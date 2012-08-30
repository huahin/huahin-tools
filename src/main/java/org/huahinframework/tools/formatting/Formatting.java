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

import java.util.regex.Pattern;

import org.apache.hadoop.mapreduce.lib.map.MultithreadedMapper;
import org.huahinframework.core.SimpleJob;
import org.huahinframework.core.lib.input.SimpleTextInputFormat;
import org.huahinframework.tools.util.ToolsTool;

/**
 *
 */
public class Formatting extends ToolsTool {
    public static final Pattern DEFAULT_APACHE_PATTERN =
            Pattern.compile("^(.*?) (.*?) (.*?) \\[(.*?)\\] \"(\\S+?)(?: +(.*?) +(\\S*?))?\" (.*?) (.*?) \"(.*?)\" \"(.*?)\"");

    public static final String OUTPUTS = "OUTPUTS";
    public static final String GROUP_NUMBER = "GROUP_NUMBER";

    @Override
    protected void setup() throws Exception {
        Pattern pattern = null;
        if (toolOptUtil.isSeparator()) {
            pattern = Pattern.compile(toolOptUtil.getSeparator());
        } else {
            pattern = DEFAULT_APACHE_PATTERN;
        }

        SimpleJob job = addJob(pattern);
        if (!opt.isLocalMode()) {
            job.setFilter(FormattingFilter.class);
        } else {
            job.setMapperClass(MultithreadedMapper.class);
            MultithreadedMapper.setMapperClass(job, FormattingFilter.class);
            MultithreadedMapper.setNumberOfThreads(job, opt.getThreadNumber());

            SimpleTextInputFormat.setMinInputSplitSize(job, opt.getSplitSize());
            SimpleTextInputFormat.setMaxInputSplitSize(job, opt.getSplitSize());
        }

        if (toolOptUtil.isOutpusNumber()) {
            job.setParameter(OUTPUTS, toolOptUtil.getOutputsNumber());
        }

        if (toolOptUtil.isGroupNumber()) {
            job.setParameter(GROUP_NUMBER, toolOptUtil.getGroupNumber());
        }
    }
}
