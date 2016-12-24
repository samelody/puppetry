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

package com.samelody.puppetry.core;

/**
 * @author Belin Wu
 */
public interface Contract {

    /**
     * The passive view.
     *
     * @author Belin Wu
     *
     * @param <P> The type of presenter.
     */
    interface View<P extends Presenter> {

        /**
         * Sets the presenter of this view.
         *
         * @param presenter The presenter.
         */
        void setPresenter(P presenter);

        /**
         * Obtains the presenter of this view.
         *
         * @return The presenter.
         */
        P getPresenter();
    }

    /**
     *
     * @author Belin Wu
     *
     * @param <V>
     * @param <M>
     */
    interface Presenter<V extends View, M extends Model> {

        /**
         * Obtains the passive view of this presenter.
         *
         * @return The passive view.
         */
        V getView();

        /**
         * Obtains the presentation model of this presenter.
         *
         * @return The presentation model.
         */
        M getModel();
    }
}
