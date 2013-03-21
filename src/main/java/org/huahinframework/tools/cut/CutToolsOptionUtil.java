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
package org.huahinframework.tools.cut;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.huahinframework.tools.util.ToolsOptionUtil;

/**
 *
 */
public class CutToolsOptionUtil extends ToolsOptionUtil {
    public CutToolsOptionUtil() {
    }

    public CutToolsOptionUtil(String[] args)
            throws ParseException {
        super(args);
    }

    private static final String FIELDS = "f";
    private static final String DELIMITER = "d";

    /* (non-Javadoc)
     * @see org.huahinframework.tools.util.ToolsOptionUtil#setMoreArgs(org.apache.commons.cli.Options)
     */
    @Override
    protected void setMoreArgs(Options options) {
        options.addOption(FIELDS, true, "fields");
        options.addOption(DELIMITER, true, "delimiter");
    }

    public boolean isFields() {
        return cli.hasOption(FIELDS);
    }

    public String getFields() {
        return cli.getOptionValue(FIELDS);
    }

    public boolean isDelimiter() {
        return cli.hasOption(DELIMITER);
    }

    public String getDelimiter() {
        return cli.getOptionValue(DELIMITER);
    }
}
