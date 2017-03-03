/*
 * Copyright (c) 2017-present Samelody.com
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

import android.support.v4.util.ArrayMap;

import com.samelody.puppetry.Contract.Presenter;

import java.util.Map;

import static com.samelody.puppetry.Model.VOID_MODEL;
import static com.samelody.stathod.util.Objects.requireNonNull;

/**
 *
 * @author Belin Wu
 */
class PresenterManager {
    /**
     * The singleton.
     */
    private static final PresenterManager instance = new PresenterManager();

    /**
     * The managed presenters.
     */
    private Map<String, Presenter> presenters = new ArrayMap<>();

    /**
     * Gets the singleton instance.
     *
     * @return The singleton instance.
     */
    static PresenterManager getInstance() {
        return instance;
    }

    /**
     * Remove the presenter.
     *
     * @param id The id mapping to presenter.
     */
    void remove(String id) {
        presenters.remove(id);
    }

    @SuppressWarnings("unchecked")
    <P extends Presenter> AbstractPresenter getPresenter(String id, Controller<P> controller) {
        AbstractPresenter presenter = (AbstractPresenter) presenters.get(id);
        if (presenter != null) {
            presenter.setFresh(false);
            return presenter;
        }
        P p = controller.createPresenter();
        requireNonNull(p, "the presenter must not be null");
        if (!(p instanceof AbstractPresenter)) {
            throw new IllegalStateException("the presenter is not a AbstractPresenter");
        }
        presenter = (AbstractPresenter) p;
        if (presenter.getModel() == null) {
            presenter.setModel(VOID_MODEL);
        }
        presenter.setFresh(true);
        presenters.put(id, presenter);
        return presenter;
    }
}
