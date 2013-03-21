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

import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.huahinframework.tools.util.ToolsOptionUtil;

/**
 *
 */
public class UrlswToolsOptionUtil extends ToolsOptionUtil {
    public UrlswToolsOptionUtil() {
    }

    public UrlswToolsOptionUtil(String[] args)
            throws ParseException {
        super(args);
    }

    private static final String FIELDS = "f";
    private static final String DELIMITER = "d";
    private static final String MASTER = "m";

    /* (non-Javadoc)
     * @see org.huahinframework.tools.util.ToolsOptionUtil#setMoreArgs(org.apache.commons.cli.Options)
     */
    @Override
    protected void setMoreArgs(Options options) {
        options.addOption(FIELDS, true, "fields");
        options.addOption(DELIMITER, true, "delimiter");
        options.addOption(MASTER, true, "master");
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

    public boolean isMaster() {
        return cli.hasOption(MASTER);
    }

    public String getMaster() {
        return cli.getOptionValue(MASTER);
    }
}
