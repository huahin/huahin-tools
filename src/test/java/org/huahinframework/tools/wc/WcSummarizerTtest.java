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
package org.huahinframework.tools.wc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.huahinframework.core.Summarizer;
import org.huahinframework.core.io.Record;
import org.huahinframework.tools.wc.WcSummarizer;
import org.huahinframework.unit.SummarizerDriver;
import org.junit.Test;

/**
 *
 */
public class WcSummarizerTtest extends SummarizerDriver {
    @Test
    public void test1() throws IOException, URISyntaxException {
        List<Record> input = new ArrayList<Record>();
        Record ir1 = new Record();
        ir1.addGrouping("FILE_NAME", "text.txt");
        ir1.addValue("FILE_LENGTH", 13L);
        input.add(ir1);

        List<Record> output = new ArrayList<Record>();
        Record or1 = new Record();
        or1.addGrouping("LINES", 1);
        or1.addGrouping("FILE_NAME", "text.txt");
        or1.setValueNothing(true);
        output.add(or1);

        run(input, output);
    }

    @Test
    public void test2() throws IOException, URISyntaxException {
        List<Record> input = new ArrayList<Record>();
        Record ir1 = new Record();
        ir1.addGrouping("FILE_NAME", "text.txt");
        ir1.addValue("FILE_LENGTH", 13L);
        input.add(ir1);
        Record ir2 = new Record();
        ir2.addGrouping("FILE_NAME", "text.txt");
        ir2.addValue("FILE_LENGTH", 13L);
        input.add(ir2);

        List<Record> output = new ArrayList<Record>();
        Record or1 = new Record();
        or1.addGrouping("LINES", 2);
        or1.addGrouping("FILE_NAME", "text.txt");
        or1.setValueNothing(true);
        output.add(or1);

        run(input, output);
    }

    @Override
    public Summarizer getSummarizer() {
        return new WcSummarizer();
    }
}
