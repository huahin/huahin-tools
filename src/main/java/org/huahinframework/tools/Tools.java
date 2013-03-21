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
package org.huahinframework.tools;

import org.apache.commons.cli.ParseException;
import org.huahinframework.core.Runner;
import org.huahinframework.core.util.OptionUtil;
import org.huahinframework.tools.ccext.Ccext;
import org.huahinframework.tools.cut.Cut;
import org.huahinframework.tools.formatting.Formatting;
import org.huahinframework.tools.urldec.Urldec;
import org.huahinframework.tools.urlsw.Urlsw;
import org.huahinframework.tools.wc.Wc;

/**
 *
 */
public class Tools {
    public static final String APACHE_FORMATTING = "formatting";
    public static final String WC = "wc";
    public static final String CCEXT = "ccext";
    public static final String URLSW = "urlsw";
    public static final String CUT = "cut";
    public static final String URLDEC = "urldec";

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args)
            throws ParseException {
        Runner runner = new Runner();
        runner.addJob(APACHE_FORMATTING, Formatting.class);
        runner.addJob(WC, Wc.class);
        runner.addJob(CCEXT, Ccext.class);
        runner.addJob(URLSW, Urlsw.class);
        runner.addJob(CUT, Cut.class);
        runner.addJob(URLDEC, Urldec.class);

        if (args.length < 3) {
            System.err.println("[jobName] args...");
            System.err.println("jobName");
            for (String s : runner.getJobList()) {
                System.err.println("  " + s);
            }
            System.exit(-1);
        }

        OptionUtil opt = new OptionUtil(args);
        String jobName = opt.getJobName();

        int status = runner.run(jobName, args);
        System.exit(status);
    }
}
