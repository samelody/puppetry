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

import com.samelody.puppetry.Contract.PassiveView;
import com.samelody.puppetry.Contract.Presenter;
import com.samelody.puppetry.Contract.Router;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * This class provides a skeletal implementation of the {@link Presenter}.
 *
 * @author Belin Wu
 *
 * @param <V> The type of the passive view.
 * @param <M> The type of the presentation model.
 */
@SuppressWarnings("unchecked")
public abstract class AbstractPresenter
        <V extends PassiveView, M extends Model, R extends Router>
        implements Presenter {

    /**
     * The presentation model.
     */
    private M model;

    /**
     * The target proxy of view.
     */
    private TargetProxy<V> viewProxy = new TargetProxy<>();

    /**
     * The target proxy of router.
     */
    private TargetProxy<R> routerProxy = new TargetProxy<>();

    /**
     * The class of model.
     */
    private Class<M> modelClass;

    /**
     *
     */
    private boolean fresh;

    {
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] typeArgs = superClass.getActualTypeArguments();
        viewProxy.setTargetInterface((Class<V>) typeArgs[0]);
        modelClass = (Class<M>) typeArgs[1];
        routerProxy.setTargetInterface((Class<R>) typeArgs[2]);
    }

    void createModel() {
        try {
            model = modelClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    void attachView(PassiveView view, Router router) {
        try {
            viewProxy.setTarget((V) view);
        } catch (ClassCastException e) {
            throw new IllegalStateException(e);
        }
        try {
            routerProxy.setTarget((R) router);
        } catch (ClassCastException e) {
            throw new IllegalStateException(e);
        }
    }

    void reattachView(PassiveView view, Router router) {
        attachView(view, router);
    }

    void detachView() {
        viewProxy.setTarget(null);
        routerProxy.setTarget(null);
    }

    /**
     * Create the presenter.
     */
    void create() {
        onCreate();
    }

    protected void onCreate() {}

    /**
     * Start the presenter.
     */
    void start() {
        onStart();
    }

    protected void onStart() {}

    /**
     * Resume the presenter.
     *
     * @param visible
     */
    void resume(boolean visible) {
        onResume();
    }

    protected void onResume() {}

    /**
     * Pause the presenter.
     */
    void pause() {
        onPause();
    }

    protected void onPause() {}

    /**
     * Stop the presenter.
     */
    void stop() {
        onStop();
    }

    protected void onStop() {}

    /**
     * Destroy the presenter.
     */
    void destroy() {
        onDestroy();
    }

    protected void onDestroy() {}

    /**
     * Gets the passive view.
     *
     * @return The passive view.
     */
    protected V getView() {
        return viewProxy.getTarget();
    }

    /**
     * Gets the router.
     *
     * @return The router.
     */
    protected R getRouter() {
        return routerProxy.getTarget();
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

    void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public boolean isFresh() {
        return fresh;
    }
}
