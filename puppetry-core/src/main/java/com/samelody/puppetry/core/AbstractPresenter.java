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

import com.samelody.puppetry.core.Contract.Presenter;
import com.samelody.puppetry.core.Contract.View;

/**
 * The abstract implementation of the presenter.
 *
 * @author Belin Wu
 *
 * @param <V> The type of the passive view.
 * @param <M> The type of the presentation model.
 */
public abstract class AbstractPresenter<V extends View, M extends Model>
        implements Presenter<V, M> {

    /**
     * The passive view.
     */
    private V view;

    /**
     * The presentation model.
     */
    private M model;

    @Override
    public V getView() {
        return view;
    }

    @Override
    public M getModel() {
        return model;
    }
}
