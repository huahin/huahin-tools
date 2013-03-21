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

import org.apache.commons.cli.ParseException;
import org.huahinframework.core.SimpleJob;
import org.huahinframework.core.util.OptionUtil;
import org.huahinframework.tools.util.ToolsOptionUtil;
import org.huahinframework.tools.util.ToolsTool;

/**
 *
 */
public class Formatting extends ToolsTool {
    public static final Pattern DEFAULT_APACHE_PATTERN =
            Pattern.compile("^(.*?) (.*?) (.*?) \\[(.*?)\\] \"(\\S+?)(?: +(.*?) +(\\S*?))?\" (.*?) (.*?) \"(.*?)\" \"(.*?)\"");

    public static final String OUTPUTS = "OUTPUTS";
    public static final String GROUP_NUMBER = "GROUP_NUMBER";

    private FormattingToolsOptionUtil tot;

    @Override
    protected void setup() throws Exception {
        Pattern pattern = null;
        if (tot.isSeparator()) {
            pattern = Pattern.compile(tot.getSeparator());
        } else {
            pattern = DEFAULT_APACHE_PATTERN;
        }

        SimpleJob job = addJob(pattern);
        setFilter(job, FormattingFilter.class);

        if (tot.isOutpusNumber()) {
            job.setParameter(OUTPUTS, tot.getOutputsNumber());
        }

        if (tot.isGroupNumber()) {
            job.setParameter(GROUP_NUMBER, tot.getGroupNumber());
        }
    }

    @Override
    protected ToolsOptionUtil setToolsOptionUtil(OptionUtil opt)
            throws ParseException {
        tot = new FormattingToolsOptionUtil(opt.getArgs());
        return tot;
    }
}
