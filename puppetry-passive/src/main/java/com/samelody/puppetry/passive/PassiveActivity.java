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
package com.samelody.puppetry.passive;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.samelody.puppetry.Contract.Presenter;
import com.samelody.puppetry.Controller;
import com.samelody.puppetry.PresenterDelegate;
import com.samelody.puppetry.lifecycle.ActivityLifecycle;
import com.samelody.puppetry.lifecycle.LifecycleManager;

import static com.samelody.puppetry.Puppetry.newPresenterDelegate;

/**
 * The passive activity.
 *
 * @param <P> The type of the presenter
 * @author Belin Wu
 */
public abstract class PassiveActivity<P extends Presenter>
        extends AppCompatActivity
        implements Controller<P> {

    /**
     * The presenter delegate.
     */
    private PresenterDelegate<P> delegate = newPresenterDelegate(this);

    /**
     * The lifecycle of this activity.
     */
    private ActivityLifecycle lifecycle;

    /**
     * Gets the lifecycle of this activity.
     *
     * @return The lifecycle of this activity.
     */
    private ActivityLifecycle getLifecycle() {
        if (lifecycle == null) {
            lifecycle = LifecycleManager.getInstance().createActivityLifecycle();
        }
        return lifecycle;
    }

    /**
     * Sets the lifecycle of this activity.
     *
     * @param lifecycle The lifecycle of this activity.
     */
    protected void setLifecycle(ActivityLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().onCreate(this, savedInstanceState);
        delegate.onViewCreate(this, savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getLifecycle().onStart(this);
        delegate.attachView(this, true);
        delegate.onViewStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLifecycle().onResume(this);
        delegate.onViewResume(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getLifecycle().onPause(this);
        delegate.onViewPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getLifecycle().onStop(this);
        delegate.detachView();
        delegate.onViewStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getLifecycle().onSaveState(this, outState);
        delegate.onSaveState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().onDestroy(this);
        delegate.onViewDestroy(this);
    }

    @Override
    public P getPresenter() {
        return delegate.getPresenter();
    }
}
