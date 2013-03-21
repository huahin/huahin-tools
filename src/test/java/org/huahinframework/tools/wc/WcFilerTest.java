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

import org.huahinframework.core.Filter;
import org.huahinframework.core.io.Record;
import org.huahinframework.tools.wc.WcFilter;
import org.huahinframework.unit.FilterDriver;
import org.junit.Test;

/**
 *
 */
public class WcFilerTest extends FilterDriver {
    private static final String INPUT = "This is a pen";

    @Test
    public void test() throws IOException, URISyntaxException {
        List<Record> output = new ArrayList<Record>();
        Record record = new Record();
        record.addGrouping("FILE_NAME", "text.txt");
        record.addValue("FILE_LENGTH", 13);
        output.add(record);

        setFileName("text.txt");
        setFileLength(13);

        run("", INPUT, output);
    }

    @Override
    public Filter getFilter() {
        return new WcFilter();
    }
}
