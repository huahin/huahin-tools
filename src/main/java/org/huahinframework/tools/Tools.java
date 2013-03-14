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
import org.huahinframework.tools.dccext.Dccext;
import org.huahinframework.tools.dcut.Dcut;
import org.huahinframework.tools.durldec.Durldec;
import org.huahinframework.tools.durlsw.Durlsw;
import org.huahinframework.tools.dwc.Dwc;
import org.huahinframework.tools.formatting.Formatting;

/**
 *
 */
public class Tools {
    public static final String APACHE_FORMATTING = "formatting";
    public static final String DWC = "dwc";
    public static final String DCCEXT = "dccext";
    public static final String DURLSW = "durlsw";
    public static final String DCUT = "dcut";
    public static final String DURLDEC = "durldec";

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args)
            throws ParseException {
        Runner runner = new Runner();
        runner.addJob(APACHE_FORMATTING, Formatting.class);
        runner.addJob(DWC, Dwc.class);
        runner.addJob(DCCEXT, Dccext.class);
        runner.addJob(DURLSW, Durlsw.class);
        runner.addJob(DCUT, Dcut.class);
        runner.addJob(DURLDEC, Durldec.class);

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
