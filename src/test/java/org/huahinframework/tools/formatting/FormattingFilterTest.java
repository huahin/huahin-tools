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
package org.huahinframework.tools.formatting;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.huahinframework.core.DataFormatException;
import org.huahinframework.core.Filter;
import org.huahinframework.core.io.Record;
import org.huahinframework.tools.formatting.Formatting;
import org.huahinframework.tools.formatting.FormattingFilter;
import org.huahinframework.unit.FilterDriver;
import org.junit.Test;

/**
 *
 */
public class FormattingFilterTest extends FilterDriver {
    @Test
    public void testAll() throws DataFormatException {
        final String input = "1.1.1.1 - - [30/Jul/2012:17:22:34 +0900] \"GET / HTTP/1.1\" 200 317 \"-\" \"Mozilla/5.0\"";

        List<Record> output = new ArrayList<Record>();
        Record record = new Record();
        record.addGrouping("0", "1.1.1.1");
        record.addGrouping("1", "-");
        record.addGrouping("2", "-");
        record.addGrouping("3", "30/Jul/2012:17:22:34 +0900");
        record.addGrouping("4", "GET");
        record.addGrouping("5", "/");
        record.addGrouping("6", "HTTP/1.1");
        record.addGrouping("7", "200");
        record.addGrouping("8", "317");
        record.addGrouping("9", "-");
        record.addGrouping("10", "Mozilla/5.0");
        record.setValueNothing(true);
        output.add(record);

        run(Pattern.compile("^(.*?) (.*?) (.*?) \\[(.*?)\\] \"(\\S+?)(?: +(.*?) +(\\S*?))?\" (.*?) (.*?) \"(.*?)\" \"(.*?)\""), input, output);
    }

    @Test
    public void testNumer() throws DataFormatException {
        final String input = "1.1.1.1 - - [30/Jul/2012:17:22:34 +0900] \"GET / HTTP/1.1\" 200 317 \"-\" \"Mozilla/5.0\"";

        List<Record> output = new ArrayList<Record>();
        Record record = new Record();
        record.addGrouping("0", "1.1.1.1");
        record.addGrouping("3", "30/Jul/2012:17:22:34 +0900");
        record.addGrouping("5", "/");
        record.setValueNothing(true);
        output.add(record);

        setParameter(Formatting.OUTPUTS, "0,3,5");

        run(Pattern.compile("^(.*?) (.*?) (.*?) \\[(.*?)\\] \"(\\S+?)(?: +(.*?) +(\\S*?))?\" (.*?) (.*?) \"(.*?)\" \"(.*?)\""), input, output);
    }

    @Test
    public void testPatternNumer() throws DataFormatException {
        final String input = "1.1.1.1 - - [30/Jul/2012:17:22:34 +0900] \"GET / HTTP/1.1\" 200 317 \"-\" \"Mozilla/5.0\"";

        List<Record> output = new ArrayList<Record>();
        Record record = new Record();
        record.addGrouping("0", "1.1.1.1");
        record.addGrouping("1", "30/Jul/2012:17:22:34 +0900");
        record.addGrouping("3", "/");
        record.addGrouping("8", "Mozilla/5.0");
        record.setValueNothing(true);
        output.add(record);

        setParameter(Formatting.OUTPUTS, "0,1,3,8");

        run(Pattern.compile("^(.*?) - - \\[(.*?)\\] \"(\\S+?)(?: +(.*?) +(\\S*?))?\" (.*?) (.*?) \"(.*?)\" \"(.*?)\""), input, output);
    }

    @Override
    public Filter getFilter() {
        return new FormattingFilter();
    }
}
