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
package org.huahinframework.tools.urldec;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.huahinframework.core.Filter;
import org.huahinframework.core.io.Record;
import org.huahinframework.tools.urldec.Urldec;
import org.huahinframework.tools.urldec.UrldecFilter;
import org.huahinframework.unit.FilterDriver;
import org.junit.Test;

/**
 *
 */
public class DurldecTest extends FilterDriver {
    private String[] fileds;

    @Test
    public void testNormal() throws IOException, URISyntaxException {
        String input = "a" + "\t" +
                       "https://www.google.co.jp/search?num=100&hl=ja&safe=off&site=&source=hp&q=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88&oq=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88&gs_l=hp.12..0l10.926.926.0.2909.1.1.0.0.0.0.59.59.1.1.0...0.0...1c.2.5.hp.YQuUODoYa2U" + "\t" +
                       "b";

        List<Record> output = new ArrayList<Record>();
        Record r = new Record();
        r.addGrouping("0", "a");
        r.addGrouping("1", "https://www.google.co.jp/search?num=100&hl=ja&safe=off&site=&source=hp&q=チョコレート&oq=チョコレート&gs_l=hp.12..0l10.926.926.0.2909.1.1.0.0.0.0.59.59.1.1.0...0.0...1c.2.5.hp.YQuUODoYa2U");
        r.addGrouping("2", "b");
        r.setValueNothing(true);
        output.add(r);

        fileds = new String[] { "2" };
        setParameter(Urldec.FIELDS, fileds);

        run("\t", input, output);
    }

    @Test
    public void testPhrase1() throws IOException, URISyntaxException {
        String input = "a" + "\t" +
                       "https://www.google.co.jp/search?num=100&hl=ja&safe=off&q=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88+%E5%B7%A5%E5%A0%B4&oq=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88+%E5%B7%A5%E5%A0%B4&gs_l=serp.3..0l7.1504.4108.0.4861.8.8.0.0.0.2.116.587.7j1.8.0...0.0...1c.1j4.5.serp._uW1AGGspZE" + "\t" +
                       "b";

        List<Record> output = new ArrayList<Record>();
        Record r = new Record();
        r.addGrouping("0", "a");
        r.addGrouping("1", "https://www.google.co.jp/search?num=100&hl=ja&safe=off&q=チョコレート 工場&oq=チョコレート 工場&gs_l=serp.3..0l7.1504.4108.0.4861.8.8.0.0.0.2.116.587.7j1.8.0...0.0...1c.1j4.5.serp._uW1AGGspZE");
        r.addGrouping("2", "b");
        r.setValueNothing(true);
        output.add(r);

        fileds = new String[] { "2" };
        setParameter(Urldec.FIELDS, fileds);

        run("\t", input, output);
    }

    @Test
    public void testPhrase2() throws IOException, URISyntaxException {
        String input = "a" + "\t" +
                       "b" + "\t" +
                       "https://www.google.co.jp/search?num=100&hl=ja&safe=off&q=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88%E3%80%80%E5%B7%A5%E5%A0%B4&oq=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88%E3%80%80%E5%B7%A5%E5%A0%B4&gs_l=serp.3..0l7.1235494.1236462.0.1237514.2.2.0.0.0.0.122.176.1j1.2.0...0.0...1c.1.5.serp.eozXYaPEcvY";

        List<Record> output = new ArrayList<Record>();
        Record r = new Record();
        r.addGrouping("0", "a");
        r.addGrouping("1", "b");
        r.addGrouping("2", "https://www.google.co.jp/search?num=100&hl=ja&safe=off&q=チョコレート　工場&oq=チョコレート　工場&gs_l=serp.3..0l7.1235494.1236462.0.1237514.2.2.0.0.0.0.122.176.1j1.2.0...0.0...1c.1.5.serp.eozXYaPEcvY");
        r.setValueNothing(true);
        output.add(r);

        fileds = new String[] { "3" };
        setParameter(Urldec.FIELDS, fileds);

        run("\t", input, output);
    }

    @Override
    public Filter getFilter() {
        return new UrldecFilter();
    }
}
