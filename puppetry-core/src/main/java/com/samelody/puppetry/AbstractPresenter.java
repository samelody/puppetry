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

import com.samelody.puppetry.Puppetry.PassiveView;
import com.samelody.puppetry.Puppetry.PresentationModel;
import com.samelody.puppetry.Puppetry.Presenter;
import com.samelody.puppetry.Puppetry.Router;

/**
 * This class provides a skeletal implementation of the {@link Presenter}.
 *
 * @author Belin Wu
 *
 * @param <V> The type of the passive view.
 * @param <M> The type of the presentation model.
 */
public abstract class AbstractPresenter
        <V extends PassiveView, M extends PresentationModel, R extends Router>
        implements Presenter {

    /**
     * The passive view.
     */
    private V view;

    /**
     * The presentation model.
     */
    private M model;

    /**
     * The router.
     */
    private R router;

    @SuppressWarnings("unchecked")
    void attachView(PassiveView view, Router router) {
        try {
            this.view = (V) view;
        } catch (ClassCastException e) {
            throw new IllegalStateException(e);
        }
        try {
            this.router = (R) router;
        } catch (ClassCastException e) {
            throw new IllegalStateException(e);
        }
    }

    void reattachView(PassiveView view, Router router) {
        attachView(view, router);
    }

    void detachView() {
        this.view = null;
        this.router = null;
    }

    /**
     * Create the presenter.
     */
    void create() {

    }

    /**
     * Start the presenter.
     */
    void start() {

    }

    /**
     * Resume the presenter.
     */
    void resume() {

    }

    /**
     * Pause the presenter.
     */
    void pause() {

    }

    /**
     * Stop the presenter.
     */
    void stop() {

    }

    /**
     * Destroy the presenter.
     */
    void destroy() {

    }

    /**
     * Gets the passive view.
     *
     * @return The passive view.
     */
    protected V getView() {
        return view;
    }

    /**
     * Gets the router.
     *
     * @return The router.
     */
    protected R getRouter() {
        return router;
    }

    /**
     * Sets the presentation model.
     *
     * @param model The presentation model.
     */
    public void setModel(M model) {
        this.model = model;
    }

    /**
     * Gets the presentation model.
     *
     * @return The presentation model.
     */
    protected M getModel() {
        return model;
    }
}
