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

/**
 *
 *
 * @author Belin Wu
 */
public final class Puppetry {

    /**
     * Private constructor.
     */
    private Puppetry() {
        throw new UnsupportedOperationException();
    }

    /**
     * The passive view.
     *
     * @author Belin Wu
     */
    public interface PassiveView {
    }

    /**
     * The presenter.
     *
     * @author Belin Wu
     */
    public interface Presenter {
    }

    /**
     * The presentation model.
     *
     * @author Belin Wu
     */
    public interface PresentationModel {

        /**
         * Gets the unique id of this model.
         *
         * @return The unique id.
         */
        long getId();
    }

    /**
     * @author Belin Wu
     */
    public interface Router {
    }
}
