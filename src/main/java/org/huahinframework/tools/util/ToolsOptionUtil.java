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

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

/**
 * Tools option utils
 */
public abstract class ToolsOptionUtil {
    /**
     * data input path
     */
    public static final String INPUT_PATH = "i";

    /**
     * data output path
     */
    public static final String OUTPUT_PATH = "o";

    protected CommandLine cli;

    public ToolsOptionUtil() {
    }

    /**
     * Tool's make command line options
     * @param args command line args
     * @throws ParseException
     */
    public ToolsOptionUtil(String[] args) throws ParseException {
        setArgs(args);
    }

    /**
     * Tool's make command line options
     * @param args command line args
     * @throws ParseException
     */
    public void setArgs(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption(INPUT_PATH, true, "data input path");
        options.addOption(OUTPUT_PATH, true, "data output path");

        setMoreArgs(options);

        CommandLineParser parser = new PosixParser();
        cli = parser.parse(options, args, true);
    }

    protected abstract void setMoreArgs(Options options);

    /**
     * @return Returns input path
     */
    public String getInputPath() {
        return cli.getOptionValue(INPUT_PATH);
    }

    /**
     * @return returns output path
     */
    public String getOutputPath() {
        return cli.getOptionValue(OUTPUT_PATH);
    }

    /**
     * @return other args
     */
    public String[] getArgs() {
        return cli.getArgs();
    }
}
