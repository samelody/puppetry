/*
 * Copyright (c) 2016-present Samelody.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package com.samelody.puppetry;

import static java.lang.System.currentTimeMillis;

/**
 * This presentation model.
 *
 * @author Belin Wu
 */
public class Model {

    public static final Model VOID_MODEL = new Model();

    /**
     * The unique id of this model.
     */
    private long id = currentTimeMillis();

    public long getId() {
        return id;
    }
}
