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
package org.huahinframework.tools.util;

import org.apache.commons.cli.ParseException;
import org.apache.hadoop.mapreduce.lib.map.MultithreadedMapper;
import org.huahinframework.core.Filter;
import org.huahinframework.core.SimpleJob;
import org.huahinframework.core.SimpleJobTool;
import org.huahinframework.core.lib.input.SimpleTextInputFormat;
import org.huahinframework.core.util.OptionUtil;

/**
 * Tools tool
 */
public abstract class ToolsTool extends SimpleJobTool {
    protected ToolsOptionUtil toolOptUtil;

    @Override
    protected String setInputPath(String[] args) {
        return toolOptUtil.getInputPath();
    }

    @Override
    protected String setOutputPath(String[] args) {
        return toolOptUtil.getOutputPath();
    }

    @Override
    public int run(String[] args) throws Exception {
        opt = new OptionUtil(args);
        toolOptUtil = setToolsOptionUtil(opt);
        return super.run(args);
    }

    protected void setFilter(SimpleJob job,
                             Class<? extends Filter> clazz) {
        if (!opt.isLocalMode()) {
            job.setFilter(clazz);
        } else {
            job.setMapperClass(MultithreadedMapper.class);
            MultithreadedMapper.setMapperClass(job, clazz);
            MultithreadedMapper.setNumberOfThreads(job, opt.getThreadNumber());

            SimpleTextInputFormat.setMinInputSplitSize(job, opt.getSplitSize());
            SimpleTextInputFormat.setMaxInputSplitSize(job, opt.getSplitSize());
        }
    }

    protected abstract ToolsOptionUtil setToolsOptionUtil(OptionUtil opt)
            throws ParseException;
}
