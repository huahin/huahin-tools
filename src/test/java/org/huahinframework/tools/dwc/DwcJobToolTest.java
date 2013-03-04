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
package org.huahinframework.tools.dwc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.huahinframework.core.SimpleJob;
import org.huahinframework.core.io.Record;
import org.huahinframework.unit.JobDriver;
import org.junit.Test;

/**
 *
 */
public class DwcJobToolTest extends JobDriver {
    private static final String INPUT = "This is a pen";

    @Test
    public void testAll1()
            throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, URISyntaxException {
        addJob("\n").setFilter(DwcFilter.class)
                  .setSummarizer(DwcSummarizer.class);

        List<String> input = new ArrayList<String>();
        input.add(INPUT);

        List<Record> output = new ArrayList<Record>();
        Record record = new Record();
        record.addGrouping(Dwc.LINES, 1);
        record.addGrouping(Dwc.WORDS, 4);
        record.addGrouping(Dwc.CHARS, 13L);
        record.addGrouping("FILE_NAME", "text.txt");
        record.addValue("", null);
        output.add(record);

        setFileName("text.txt");
        setFileLength(13L);

        run(input, output, true);
    }

    @Test
    public void testAll2()
            throws IOException, URISyntaxException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        SimpleJob j = addJob("\n").setFilter(DwcFilter.class)
                                  .setSummarizer(DwcSummarizer.class);

        List<String> input = new ArrayList<String>();
        input.add(INPUT);

        List<Record> output = new ArrayList<Record>();
        Record record = new Record();
        record.addGrouping(Dwc.LINES, 1);
        record.addGrouping(Dwc.WORDS, 4);
        record.addGrouping(Dwc.CHARS, 13L);
        record.addGrouping("FILE_NAME", "text.txt");
        record.addValue("", null);
        output.add(record);

        setFileName("text.txt");
        setFileLength(13L);

        j.setParameter(Dwc.CHARS, true);
        j.setParameter(Dwc.WORDS, true);
        j.setParameter(Dwc.LINES, true);

        run(input, output, true);
    }

    @Test
    public void testCharsWords()
            throws IOException, URISyntaxException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        SimpleJob j = addJob("\n").setFilter(DwcFilter.class)
                                  .setSummarizer(DwcSummarizer.class);

        List<String> input = new ArrayList<String>();
        input.add(INPUT);

        List<Record> output = new ArrayList<Record>();
        Record record = new Record();
        record.addGrouping(Dwc.WORDS, 4);
        record.addGrouping(Dwc.CHARS, 13L);
        record.addGrouping("FILE_NAME", "text.txt");
        record.addValue("", null);
        output.add(record);

        setFileName("text.txt");
        setFileLength(13);

        j.setParameter(Dwc.CHARS, true);
        j.setParameter(Dwc.WORDS, true);

        run(input, output, true);
    }

    @Test
    public void testCharsLines()
            throws IOException, URISyntaxException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        SimpleJob j = addJob("\n").setFilter(DwcFilter.class)
                                  .setSummarizer(DwcSummarizer.class);

        List<String> input = new ArrayList<String>();
        input.add(INPUT);

        List<Record> output = new ArrayList<Record>();
        Record record = new Record();
        record.addGrouping(Dwc.LINES, 1);
        record.addGrouping(Dwc.CHARS, 13L);
        record.addGrouping("FILE_NAME", "text.txt");
        record.addValue("", null);
        output.add(record);

        setFileName("text.txt");
        setFileLength(13);

        j.setParameter(Dwc.CHARS, true);
        j.setParameter(Dwc.LINES, true);

        run(input, output, true);
    }

    @Test
    public void testWordsLines()
            throws IOException, URISyntaxException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        SimpleJob j = addJob("\n").setFilter(DwcFilter.class)
                                  .setSummarizer(DwcSummarizer.class);

        List<String> input = new ArrayList<String>();
        input.add(INPUT);

        List<Record> output = new ArrayList<Record>();
        Record record = new Record();
        record.addGrouping(Dwc.LINES, 1);
        record.addGrouping(Dwc.WORDS, 4);
        record.addGrouping("FILE_NAME", "text.txt");
        record.addValue("", null);
        output.add(record);

        setFileName("text.txt");
        setFileLength(13);

        j.setParameter(Dwc.WORDS, true);
        j.setParameter(Dwc.LINES, true);

        run(input, output, true);
    }

    @Test
    public void testChars()
            throws IOException, URISyntaxException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        SimpleJob j = addJob("\n").setFilter(DwcFilter.class)
                                  .setSummarizer(DwcSummarizer.class);

        List<String> input = new ArrayList<String>();
        input.add(INPUT);

        List<Record> output = new ArrayList<Record>();
        Record record = new Record();
        record.addGrouping(Dwc.CHARS, 13L);
        record.addGrouping("FILE_NAME", "text.txt");
        record.addValue("", null);
        output.add(record);

        setFileName("text.txt");
        setFileLength(13);

        j.setParameter(Dwc.CHARS, true);

        run(input, output, true);
    }

    @Test
    public void testWords()
            throws IOException, URISyntaxException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        SimpleJob j = addJob("\n").setFilter(DwcFilter.class)
                                  .setSummarizer(DwcSummarizer.class);

        List<String> input = new ArrayList<String>();
        input.add(INPUT);

        List<Record> output = new ArrayList<Record>();
        Record record = new Record();
        record.addGrouping(Dwc.WORDS, 4);
        record.addGrouping("FILE_NAME", "text.txt");
        record.addValue("", null);
        output.add(record);

        setFileName("text.txt");
        setFileLength(13);

        j.setParameter(Dwc.WORDS, true);

        run(input, output, true);
    }

    @Test
    public void testLiness()
            throws IOException, URISyntaxException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        SimpleJob j = addJob("\n").setFilter(DwcFilter.class)
                                  .setSummarizer(DwcSummarizer.class);

        List<String> input = new ArrayList<String>();
        input.add(INPUT);

        List<Record> output = new ArrayList<Record>();
        Record record = new Record();
        record.addGrouping(Dwc.LINES, 1);
        record.addGrouping("FILE_NAME", "text.txt");
        record.addValue("", null);
        output.add(record);

        setFileName("text.txt");
        setFileLength(13);

        j.setParameter(Dwc.LINES, true);

        run(input, output, true);
    }
}
