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

import java.util.Map;

import static com.samelody.puppetry.Model.VOID_MODEL;

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
    private Map<String, Contract.Presenter> presenters = new ArrayMap<>();

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
    <P extends Contract.Presenter> PresenterWrapper getPresenter(String id, Controller<P> factory) {
        P p = (P) presenters.get(id);
        if (p != null) {
            return new PresenterWrapper((AbstractPresenter) p, false);
        }
        p = factory.createPresenter();
        if (p == null) {
            p = (P) VoidPresenter.getInstance();
        }
        if (!(p instanceof AbstractPresenter)) {
            throw new IllegalStateException("Your presenter must be a AbstractPresenter.");
        }
        AbstractPresenter presenter = (AbstractPresenter) p;
        if (presenter.getModel() == null) {
            presenter.setModel(VOID_MODEL);
        }
        presenters.put(id, presenter);
        return new PresenterWrapper(presenter, true);
    }
}
