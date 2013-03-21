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
package org.huahinframework.tools.urlsw;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.huahinframework.core.Filter;
import org.huahinframework.core.io.Record;
import org.huahinframework.tools.urlsw.Urlsw;
import org.huahinframework.tools.urlsw.UrlswFilter;
import org.huahinframework.unit.FilterDriver;
import org.junit.Test;

/**
 *
 */
public class UrlswFilterTest extends FilterDriver {
    private String[] fileds;
    private String[] master;

    @Test
    public void testNormal() throws IOException, URISyntaxException {
        String input = "a" + "\t" +
                       "https://www.google.co.jp/search?num=100&hl=ja&safe=off&site=&source=hp&q=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88&oq=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88&gs_l=hp.12..0l10.926.926.0.2909.1.1.0.0.0.0.59.59.1.1.0...0.0...1c.2.5.hp.YQuUODoYa2U" + "\t" +
                       "b";

        List<Record> output = new ArrayList<Record>();
        Record r = new Record();
        r.addGrouping("VALUE", "a" + "\t" +
                               "https://www.google.co.jp/search?num=100&hl=ja&safe=off&site=&source=hp&q=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88&oq=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88&gs_l=hp.12..0l10.926.926.0.2909.1.1.0.0.0.0.59.59.1.1.0...0.0...1c.2.5.hp.YQuUODoYa2U" + "\t" +
                               "b" + "\t" +
                               "チョコレート");
        r.setValueNothing(true);
        output.add(r);

        fileds = new String[] { "2" };
        setParameter(Urlsw.FIELDS, fileds);

        master = new String[]
                {
                    "www.google.co.jp\t/search\tq",
                    "search.yahoo.co.jp\t/search\tp",
                    "www.bing.com\t/search\tq"
                };
        setParameter(Urlsw.MASTER, master);

        run("\t", input, output);
    }

    @Test
    public void testPhrase1() throws IOException, URISyntaxException {
        String input = "a" + "\t" +
                       "https://www.google.co.jp/search?num=100&hl=ja&safe=off&q=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88+%E5%B7%A5%E5%A0%B4&oq=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88+%E5%B7%A5%E5%A0%B4&gs_l=serp.3..0l7.1504.4108.0.4861.8.8.0.0.0.2.116.587.7j1.8.0...0.0...1c.1j4.5.serp._uW1AGGspZE" + "\t" +
                       "b";

        List<Record> output = new ArrayList<Record>();
        Record r = new Record();
        r.addGrouping("VALUE", "a" + "\t" +
                               "https://www.google.co.jp/search?num=100&hl=ja&safe=off&q=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88+%E5%B7%A5%E5%A0%B4&oq=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88+%E5%B7%A5%E5%A0%B4&gs_l=serp.3..0l7.1504.4108.0.4861.8.8.0.0.0.2.116.587.7j1.8.0...0.0...1c.1j4.5.serp._uW1AGGspZE" + "\t" +
                               "b"  + "\t" +
                               "チョコレート" + "\t" +
                               "工場");
        r.setValueNothing(true);
        output.add(r);

        fileds = new String[] { "2" };
        setParameter(Urlsw.FIELDS, fileds);

        master = new String[]
                {
                    "www.google.co.jp\t/search\tq",
                    "search.yahoo.co.jp\t/search\tp",
                    "www.bing.com\t/search\tq"
                };
        setParameter(Urlsw.MASTER, master);

        run("\t", input, output);
    }

    @Test
    public void testPhrase2() throws IOException, URISyntaxException {
        String input = "a" + "\t" +
                       "https://www.google.co.jp/search?num=100&hl=ja&safe=off&q=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88%E3%80%80%E5%B7%A5%E5%A0%B4&oq=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88%E3%80%80%E5%B7%A5%E5%A0%B4&gs_l=serp.3..0l7.1235494.1236462.0.1237514.2.2.0.0.0.0.122.176.1j1.2.0...0.0...1c.1.5.serp.eozXYaPEcvY" + "\t" +
                       "b";

        List<Record> output = new ArrayList<Record>();
        Record r = new Record();
        r.addGrouping("0", "a" + "\t" +
                      "https://www.google.co.jp/search?num=100&hl=ja&safe=off&q=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88%E3%80%80%E5%B7%A5%E5%A0%B4&oq=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88%E3%80%80%E5%B7%A5%E5%A0%B4&gs_l=serp.3..0l7.1235494.1236462.0.1237514.2.2.0.0.0.0.122.176.1j1.2.0...0.0...1c.1.5.serp.eozXYaPEcvY" + "\t" +
                      "b" + "\t" +
                      "チョコレート" + "\t" +
                      "工場");
        r.setValueNothing(true);
        output.add(r);

        fileds = new String[] { "2" };
        setParameter(Urlsw.FIELDS, fileds);

        master = new String[]
                {
                    "www.google.co.jp\t/search\tq",
                    "search.yahoo.co.jp\t/search\tp",
                    "www.bing.com\t/search\tq"
                };
        setParameter(Urlsw.MASTER, master);

        run("\t", input, output);
    }

    @Test
    public void testNotHit1() throws IOException, URISyntaxException {
        String input = "a" + "\t" +
                       "http://search.goo.ne.jp/web.jsp?MT=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88&bt_search.x=-807&bt_search.y=-97&bt_search=%E6%A4%9C%E7%B4%A2&STYPE=web&SH=1&IE=UTF-8&OE=UTF-8&from=gootop" + "\t" +
                       "b";

        List<Record> output = new ArrayList<Record>();
        Record r = new Record();
        r.addGrouping("VALUE", "a" + "\t" +
                               "http://search.goo.ne.jp/web.jsp?MT=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88&bt_search.x=-807&bt_search.y=-97&bt_search=%E6%A4%9C%E7%B4%A2&STYPE=web&SH=1&IE=UTF-8&OE=UTF-8&from=gootop" + "\t" +
                               "b");
        r.setValueNothing(true);
        output.add(r);

        fileds = new String[] { "2" };
        setParameter(Urlsw.FIELDS, fileds);

        master = new String[]
                {
                    "www.google.co.jp\t/search\tq",
                    "search.yahoo.co.jp\t/search\tp",
                    "www.bing.com\t/search\tq"
                };
        setParameter(Urlsw.MASTER, master);

        run("\t", input, output);
    }

    @Test
    public void testCommaNormal() throws IOException, URISyntaxException {
        String input = "a" + "," +
                       "https://www.google.co.jp/search?num=100&hl=ja&safe=off&site=&source=hp&q=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88&oq=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88&gs_l=hp.12..0l10.926.926.0.2909.1.1.0.0.0.0.59.59.1.1.0...0.0...1c.2.5.hp.YQuUODoYa2U" + "," +
                       "b";

        List<Record> output = new ArrayList<Record>();
        Record r = new Record();
        r.addGrouping("VALUE", "a" + "," +
                               "https://www.google.co.jp/search?num=100&hl=ja&safe=off&site=&source=hp&q=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88&oq=%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88&gs_l=hp.12..0l10.926.926.0.2909.1.1.0.0.0.0.59.59.1.1.0...0.0...1c.2.5.hp.YQuUODoYa2U" + "," +
                               "b" + "," +
                               "チョコレート");
        r.setValueNothing(true);
        output.add(r);

        fileds = new String[] { "2" };
        setParameter(Urlsw.FIELDS, fileds);

        master = new String[]
                {
                    "www.google.co.jp\t/search\tq",
                    "search.yahoo.co.jp\t/search\tp",
                    "www.bing.com\t/search\tq"
                };
        setParameter(Urlsw.MASTER, master);
        setParameter(Urlsw.SEPARATOR, master);

        run(",", input, output);
    }

    @Override
    public Filter getFilter() {
        return new UrlswFilter();
    }
}
