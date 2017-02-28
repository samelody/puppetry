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

import android.support.annotation.CallSuper;

import com.samelody.puppetry.core.AbstractPresenter;
import com.samelody.puppetry.core.Contract.PassiveView;
import com.samelody.puppetry.core.Contract.PresentationModel;
import com.samelody.puppetry.core.Contract.Router;

/**
 * @author Belin Wu
 */
public abstract class LifecyclePresenter
        <V extends PassiveView, M extends PresentationModel, R extends Router>
        extends AbstractPresenter<V, M, R>
        implements LifecycleAware<PresenterLifecycle> {

    /**
     * The lifecycle of this presenter.
     */
    private PresenterLifecycle lifecycle;

    @Override
    public void setLifecycle(PresenterLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    @Override
    public PresenterLifecycle getLifecycle() {
        if (lifecycle == null) {
            lifecycle = LifecycleManager.getInstance().createPresenterLifecycle();
        }
        return lifecycle;
    }

    @CallSuper
    @Override
    protected void onCreate() {
        super.onCreate();
        getLifecycle().onPresenterCreate(this);
    }

    @CallSuper
    @Override
    protected void onStart() {
        super.onStart();
        getLifecycle().onPresenterStart(this);
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();
        getLifecycle().onPresenterResume(this);
    }

    @CallSuper
    @Override
    protected void onPause() {
        super.onPause();
        getLifecycle().onPresenterPause(this);
    }

    @CallSuper
    @Override
    protected void onStop() {
        super.onStop();
        getLifecycle().onPresenterStop(this);
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().onPresenterDestroy(this);
    }
}
