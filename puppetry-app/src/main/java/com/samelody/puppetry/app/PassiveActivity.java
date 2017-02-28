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
package com.samelody.puppetry.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.samelody.puppetry.core.Contract.PassiveView;
import com.samelody.puppetry.core.Contract.Presenter;
import com.samelody.puppetry.core.PresenterDelegate;
import com.samelody.puppetry.lifecycle.ActivityLifecycle;
import com.samelody.puppetry.lifecycle.LifecycleManager;
import com.samelody.puppetry.lifecycle.LifecycleController;

/**
 * The passive activity.
 *
 * @param <P> The type of the presenter
 * @author Belin Wu
 */
public abstract class PassiveActivity<P extends Presenter>
        extends AppCompatActivity
        implements PassiveView, LifecycleController<ActivityLifecycle, P> {

    /**
     * The presenter delegate.
     */
    private PresenterDelegate<P> delegate = new PresenterDelegate<>();

    /**
     * The lifecycle of this activity.
     */
    private ActivityLifecycle lifecycle;

    @Override
    public ActivityLifecycle getLifecycle() {
        if (lifecycle == null) {
            lifecycle = LifecycleManager.getInstance().createActivityLifecycle();
        }
        return lifecycle;
    }

    @Override
    public void setLifecycle(ActivityLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().onActivityCreate(this, savedInstanceState);
        delegate.onViewCreate(this, savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getLifecycle().onActivityStart(this);
        delegate.onViewStart(this, getRouter());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLifecycle().onActivityResume(this);
        delegate.onViewResume(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getLifecycle().onActivityPause(this);
        delegate.onViewPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getLifecycle().onActivityStop(this);
        delegate.onViewStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getLifecycle().onActivityStateSave(this, outState);
        delegate.onSaveState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().onActivityDestroy(this);
        delegate.onViewDestroy(this);
    }

    @Override
    public P getPresenter() {
        return delegate.getPresenter();
    }
}
