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
package com.samelody.puppetry.lifecycle;

import com.samelody.puppetry.core.AbstractPresenter;
import com.samelody.puppetry.core.Contract.PassiveView;
import com.samelody.puppetry.core.Contract.PresentationModel;
import com.samelody.puppetry.core.Contract.Router;

import static com.samelody.puppetry.lifecycle.LifecycleManager.getLifecycleListener;

/**
 * @author Belin Wu
 */
public abstract class LifecyclePresenter
        <V extends PassiveView, M extends PresentationModel, R extends Router>
        extends AbstractPresenter<V, M, R> {

    @Override
    protected final void create() {
        super.create();
        getLifecycleListener().onPresenterCreated(this);
    }

    @Override
    protected final void start() {
        super.start();
        getLifecycleListener().onPresenterStarted(this);
    }

    @Override
    protected final void resume(boolean isVisibleToUser) {
        super.resume(isVisibleToUser);
        getLifecycleListener().onPresenterResumed(this, isVisibleToUser);
    }

    @Override
    protected final void pause() {
        super.pause();
        getLifecycleListener().onPresenterPaused(this);
    }

    @Override
    protected final void stop() {
        super.stop();
        getLifecycleListener().onPresenterStopped(this);
    }

    @Override
    protected final void destroy() {
        super.destroy();
        getLifecycleListener().onPresenterDestroyed(this);
    }
}
