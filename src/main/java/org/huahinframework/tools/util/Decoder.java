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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Decoder {
    private static List<Charset> decodeCharsets;
    static {
        decodeCharsets = new ArrayList<Charset>();
        decodeCharsets.add(Charset.forName("UTF-8"));
        decodeCharsets.add(Charset.forName("EUC-JP"));
        decodeCharsets.add(Charset.forName("SJIS"));
        decodeCharsets.add(Charset.forName("windows-31j"));
        decodeCharsets.add(Charset.forName("ISO-2022-JP"));
    }

    /**
     * @param url
     * @return String
     * @throws UnsupportedEncodingException
     */
    public static String getDecodeQuery(String url)
            throws UnsupportedEncodingException {
       String[] tmp = url.split("\\?", -1);
       if (tmp.length == 2) {
           return urlDecoder(tmp[1]);
       }

       return "";
    }

    /**
     * @param url
     * @return String
     * @throws UnsupportedEncodingException
     */
    public static String urlDecoder(String url)
            throws UnsupportedEncodingException {
        byte[] b = null;
        try {
            b = URLDecoder.decode(url, "iso-8859-1").getBytes("iso-8859-1");
        } catch (IllegalArgumentException e) {
            return url;
        }

        for (Charset charset : decodeCharsets) {
            CharsetDecoder decoder = charset.newDecoder();
            try {
                String tmp = decoder.decode(ByteBuffer.wrap(b)).toString();
                if (tmp.isEmpty() || tmp.equals(url)) {
                    continue;
                }
                return tmp;
            } catch (CharacterCodingException e) {
                continue;
            }
        }

        return url;
    }
}
