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

import java.util.ArrayList;
import java.util.List;

import org.huahinframework.core.util.StringUtil;

/**
 *
 */
public class OptionsStringUtils {
    /**
     * @param s
     * @return String[]
     */
    public static String[] optionStrings(String str) {
        List<String> l = new ArrayList<String>();
        String[] fields = StringUtil.split(str, ",", true);
        for (String s : fields) {
            if (s.indexOf("-") != -1) {
                String[] tmp = StringUtil.split(s, "-", true);
                int start = Integer.valueOf(tmp[0]);
                int end = Integer.valueOf(tmp[1]);
                for (int i = start; i <= end; i++) {
                    l.add(String.valueOf(i));
                }
            } else {
                l.add(s);
            }
        }

        return (String[]) l.toArray(new String[0]);
    }
}
