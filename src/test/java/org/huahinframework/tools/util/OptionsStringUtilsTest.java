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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *
 */
public class OptionsStringUtilsTest {
    @Test
    public void test1() {
        String str = "1,2,3";
        String[] receive = OptionsStringUtils.optionStrings(str);
        String[] out = { "1", "2", "3" };
        assertEquals(receive.length, out.length);
        for (int i = 0; i < out.length; i++) {
            assertEquals(receive[i], out[i]);
        }
    }

    @Test
    public void test2() {
        String str = "1,2-4";
        String[] receive = OptionsStringUtils.optionStrings(str);
        String[] out = { "1", "2", "3", "4" };
        assertEquals(receive.length, out.length);
        for (int i = 0; i < out.length; i++) {
            assertEquals(receive[i], out[i]);
        }
    }

    @Test
    public void test3() {
        String str = "1,2-4,6";
        String[] receive = OptionsStringUtils.optionStrings(str);
        String[] out = { "1", "2", "3", "4", "6" };
        assertEquals(receive.length, out.length);
        for (int i = 0; i < out.length; i++) {
            assertEquals(receive[i], out[i]);
        }
    }
}
