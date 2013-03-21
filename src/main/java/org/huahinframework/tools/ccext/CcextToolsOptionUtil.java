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

import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.huahinframework.tools.util.ToolsOptionUtil;

/**
 *
 */
public class CcextToolsOptionUtil extends ToolsOptionUtil {
    public CcextToolsOptionUtil() {
    }

    public CcextToolsOptionUtil(String[] args)
            throws ParseException {
        super(args);
    }

    public static final String NUMBER = "n";
    public static final String REVERSE = "v";
    public static final String DELIMITER = "d";

    /* (non-Javadoc)
     * @see org.huahinframework.tools.util.ToolsOptionUtil#setMoreArgs(org.apache.commons.cli.Options)
     */
    @Override
    protected void setMoreArgs(Options options) {
        options.addOption(NUMBER, true, "number");
        options.addOption(REVERSE, false, "reverse");
        options.addOption(DELIMITER, true, "delimiter");
    }

    public boolean isNumber() {
        return cli.hasOption(NUMBER);
    }

    public String getNumber() {
        return cli.getOptionValue(NUMBER);
    }

    public boolean isReverser() {
        return cli.hasOption(REVERSE);
    }

    public boolean isDelimiter() {
        return cli.hasOption(DELIMITER);
    }

    public String getDelimiter() {
        return cli.getOptionValue(DELIMITER);
    }
}
