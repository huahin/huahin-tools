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
package org.huahinframework.tools.cut;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.huahinframework.core.Filter;
import org.huahinframework.core.io.Record;
import org.huahinframework.tools.cut.CutFilter;
import org.huahinframework.unit.FilterDriver;
import org.junit.Test;

/**
 *
 */
public class CutFilterTest extends FilterDriver {
    @Test
    public void test1() throws IOException, URISyntaxException {
        String input = "a" + "\t" + "b" + "\t" + "c";

        List<Record> output = new ArrayList<Record>();
        Record r = new Record();
        r.addGrouping("0", "a");
        r.setValueNothing(true);
        output.add(r);

        String[] fileds = new String[] { "1" };
        setParameter(Cut.FIELDS, fileds);

        run("\t", input, output);
    }

    @Test
    public void test2() throws IOException, URISyntaxException {
        String input = "a" + "\t" + "b" + "\t" + "c";

        List<Record> output = new ArrayList<Record>();
        Record r = new Record();
        r.addGrouping("VALUE", "a\tb");
        r.setValueNothing(true);
        output.add(r);

        String[] fileds = new String[] { "1", "2" };
        setParameter(Cut.FIELDS, fileds);

        run("\t", input, output);
    }

    @Test
    public void test3() throws IOException, URISyntaxException {
        String input = "a" + "\t" + "b" + "\t" + "c";

        List<Record> output = new ArrayList<Record>();
        Record r = new Record();
        r.addGrouping("VALUE", "a\tc");
        r.setValueNothing(true);
        output.add(r);

        String[] fileds = new String[] { "1", "3" };
        setParameter(Cut.FIELDS, fileds);

        run("\t", input, output);
    }

    @Test
    public void test4() throws IOException, URISyntaxException {
        String input = "a,b,c";

        List<Record> output = new ArrayList<Record>();
        Record r = new Record();
        r.addGrouping("VALUE", "a,c");
        r.setValueNothing(true);
        output.add(r);

        String[] fileds = new String[] { "1", "3" };
        setParameter(Cut.FIELDS, fileds);
        setParameter(Cut.SEPARATOR, ",");

        run(",", input, output);
    }

    @Override
    public Filter getFilter() {
        return new CutFilter();
    }
}
