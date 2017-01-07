/*
 * Copyright (c) 2016 Samelody.
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

import com.samelody.puppetry.Puppetry.PresentationModel;

import static java.lang.System.currentTimeMillis;

/**
 * This class provides a skeletal implementation of the {@link PresentationModel}.
 *
 * @author Belin Wu
 */
public abstract class AbstractModel implements PresentationModel {
    /**
     * The unique id of this model.
     */
    private long id = currentTimeMillis();

    @Override
    public long getId() {
        return id;
    }
}
