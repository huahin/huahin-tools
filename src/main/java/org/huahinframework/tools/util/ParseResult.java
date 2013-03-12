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

/**
 *
 */
public class ParseResult {
    public static enum State {
        SUCESS, INVALID_URI, URL_NOT_FOUND, PARAMETER_NOT_FOUND, KEYWORD_NOT_FOUND, DECODE_FAILER, DEFAULT;
    }

    public static final ParseResult FAILED_VALUE = new ParseResult(State.DEFAULT, null);

    private State state = State.DEFAULT;
    private String result;

    public ParseResult(State state, String result) {
        this.state = state;
        this.result = result;
    }

    public boolean isSucseed() {
        return this.state == State.SUCESS;
    }

    public State getState() {
        return state;
    }

    public String getResult() {
        return result;
    }
}
