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

import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.huahinframework.tools.util.ToolsOptionUtil;

/**
 *
 */
public class FormattingToolsOptionUtil extends ToolsOptionUtil {
    public FormattingToolsOptionUtil() {
    }

    public FormattingToolsOptionUtil(String[] args)
            throws ParseException {
        super(args);
    }

    /**
     * separator pattern
     */
    public static final String SEPARATOR_PATTERN = "p";

    /**
     * specific outputs number
     */
    public static final String SPECIFIC_OUTPUTS_NUMBER = "e";

    /**
     * group number of separator pattern
     */
    public static final String PATTERN_GROUP_NUMBER = "n";

    /* (non-Javadoc)
     * @see org.huahinframework.tools.util.ToolsOptionUtil#setMoreArgs(org.apache.commons.cli.Options)
     */
    @Override
    protected void setMoreArgs(Options options) {
        options.addOption(SEPARATOR_PATTERN, true, "separator pattern");
        options.addOption(SPECIFIC_OUTPUTS_NUMBER, true, "specific outputs number(comma separator)");
        options.addOption(PATTERN_GROUP_NUMBER, true, "group number of separator pattern");
    }

    /**
     * @return if true, separator pattern
     */
    public boolean isSeparator() {
        return cli.hasOption(SEPARATOR_PATTERN);
    }

    /**
     * @return separator pattern
     */
    public String getSeparator() {
        return cli.getOptionValue(SEPARATOR_PATTERN);
    }

    /**
     * @return if true, specific outputs number
     */
    public boolean isOutpusNumber() {
        return cli.hasOption(SPECIFIC_OUTPUTS_NUMBER);
    }

    /**
     * @return specific outputs number
     */
    public String getOutputsNumber() {
        return cli.getOptionValue(SPECIFIC_OUTPUTS_NUMBER);
    }

    /**
     * @return if true, group number of separator pattern
     */
    public boolean isGroupNumber() {
        return cli.hasOption(PATTERN_GROUP_NUMBER);
    }

    /**
     * @return group number of separator pattern
     */
    public String getGroupNumber() {
        return cli.getOptionValue(PATTERN_GROUP_NUMBER);
    }
}
