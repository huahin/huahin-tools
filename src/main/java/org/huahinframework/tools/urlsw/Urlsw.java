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
package org.huahinframework.tools.urlsw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.ParseException;
import org.apache.hadoop.mapreduce.lib.map.MultithreadedMapper;
import org.huahinframework.core.SimpleJob;
import org.huahinframework.core.lib.input.SimpleTextInputFormat;
import org.huahinframework.core.util.OptionUtil;
import org.huahinframework.tools.util.OptionsStringUtils;
import org.huahinframework.tools.util.ToolsOptionUtil;
import org.huahinframework.tools.util.ToolsTool;

/**
 *
 */
public class Urlsw extends ToolsTool {
    public static final String FIELDS = "FIELDS";
    public static final String MASTER = "MASTER";
    public static final String SEPARATOR = "SEPARATOR";

    private UrlswToolsOptionUtil tot;

    /* (non-Javadoc)
     * @see org.huahinframework.core.SimpleJobTool#setup()
     */
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
            job.setFilter(UrlswFilter.class);
        } else {
            job.setMapperClass(MultithreadedMapper.class);
            MultithreadedMapper.setMapperClass(job, UrlswFilter.class);
            MultithreadedMapper.setNumberOfThreads(job, opt.getThreadNumber());

            SimpleTextInputFormat.setMinInputSplitSize(job, opt.getSplitSize());
            SimpleTextInputFormat.setMaxInputSplitSize(job, opt.getSplitSize());
        }

        if (!tot.isFields()) {
            throw new RuntimeException("-f(fields) not found");
        }

        if (!tot.isMaster()) {
            throw new RuntimeException("-m(master) not found");
        }

        String[] fields = OptionsStringUtils.optionStrings(tot.getFields());
        job.setParameter(FIELDS, fields);

        BufferedReader br = new BufferedReader(new FileReader(tot.getMaster()));
        String line;
        List<String> l = new ArrayList<String>();
        while ((line = br.readLine()) != null) {
            l.add(line);
        }
        br.close();

        job.setParameter(MASTER, (String[]) l.toArray(new String[0]));
    }

    /* (non-Javadoc)
     * @see org.huahinframework.tools.util.ToolsTool#setToolsOptionUtil(org.huahinframework.core.util.OptionUtil)
     */
    @Override
    protected ToolsOptionUtil setToolsOptionUtil(OptionUtil opt)
            throws ParseException {
        tot = new UrlswToolsOptionUtil(opt.getArgs());
        return tot;
    }
}
