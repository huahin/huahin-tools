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
package org.huahinframework.tools.ccext;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.huahinframework.core.Filter;
import org.huahinframework.core.io.Record;
import org.huahinframework.tools.ccext.Ccext;
import org.huahinframework.tools.ccext.CcextFilter;
import org.huahinframework.unit.FilterDriver;
import org.junit.Test;

/**
 *
 */
public class CcextFilterTest extends FilterDriver {
    @Test
    public void testNormalHit() throws IOException, URISyntaxException {
        String input = "a\tb\tc";

        List<Record> output = new ArrayList<Record>();
        Record r = new Record();
        r.addGrouping("VALUE", "a\tb\tc");
        r.setValueNothing(true);
        output.add(r);

        setParameter(Ccext.NUMBER, 3);

        run("\t", input, output);
    }

    @Test
    public void testNormalNotHit() throws IOException, URISyntaxException {
        String input = "a\tb\tc";

        setParameter(Ccext.NUMBER, 3);
        setParameter(Ccext.REVERSE, true);

        run("\t", input, null);
    }

    @Test
    public void testReverseHit() throws IOException, URISyntaxException {
        String input = "a\tb";

        List<Record> output = new ArrayList<Record>();
        Record r = new Record();
        r.addGrouping("VALUE", "a\tb");
        r.setValueNothing(true);
        output.add(r);

        setParameter(Ccext.NUMBER, 3);
        setParameter(Ccext.REVERSE, true);

        run("\t", input, output);
    }

    @Test
    public void testReverseNotHit() throws IOException, URISyntaxException {
        String input = "a\tb";

        setParameter(Ccext.NUMBER, 2);
        setParameter(Ccext.REVERSE, true);

        run("\t", input, null);
    }

    @Test
    public void testCommaHit() throws IOException, URISyntaxException {
        String input = "a,b,c";

        List<Record> output = new ArrayList<Record>();
        Record r = new Record();
        r.addGrouping("VALUE", "a,b,c");
        r.setValueNothing(true);
        output.add(r);

        setParameter(Ccext.NUMBER, 3);
        setParameter(Ccext.SEPARATOR, ",");

        run(",", input, output);
    }

    @Override
    public Filter getFilter() {
        return new CcextFilter();
    }
}
