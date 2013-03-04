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

import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.huahinframework.tools.util.ToolsOptionUtil;

/**
 *
 */
public class DwcToolsOptionUtil extends ToolsOptionUtil {
    public DwcToolsOptionUtil() {
    }

    public DwcToolsOptionUtil(String[] args)
            throws ParseException {
        super(args);
    }

    public static final String CHARS = "c";
    public static final String WORDS = "w";
    public static final String LINES = "l";

    /* (non-Javadoc)
     * @see org.huahinframework.tools.util.ToolsOptionUtil#setMoreArgs(org.apache.commons.cli.Options)
     */
    @Override
    protected void setMoreArgs(Options options) {
        options.addOption(CHARS, false, "chars");
        options.addOption(WORDS, false, "words");
        options.addOption(LINES, false, "lines");
    }

    public boolean isChars() {
        return cli.hasOption(CHARS);
    }

    public String getWords() {
        return cli.getOptionValue(WORDS);
    }

    public boolean isLines() {
        return cli.hasOption(LINES);
    }

    public String getChars() {
        return cli.getOptionValue(CHARS);
    }

    public boolean isWords() {
        return cli.hasOption(WORDS);
    }

    public String getLines() {
        return cli.getOptionValue(LINES);
    }
}
