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

package com.samelody.puppetry.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.samelody.puppetry.core.Contract.PassiveView;
import com.samelody.puppetry.core.Contract.Presenter;
import com.samelody.puppetry.core.PresenterDelegate;
import com.samelody.puppetry.core.ViewController;

import static com.samelody.puppetry.lifecycle.LifecycleManager.getLifecycleListener;

/**
 * The passive fragment.
 *
 * @author Belin Wu
 */
public abstract class PassiveFragment<P extends Presenter>
        extends DialogFragment
        implements PassiveView, ViewController<P> {

    /**
     * The presenter delegate.
     */
    private PresenterDelegate<P> delegate = new PresenterDelegate<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getLifecycleListener().onFragmentCreated(this, savedInstanceState);
        delegate.onViewCreate(this, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getLifecycleListener().onFragmentStarted(this);
        delegate.onViewStart(this, createRouter());
    }

    @Override
    public void onStop() {
        super.onStop();
        getLifecycleListener().onFragmentStopped(this);
        delegate.onViewStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        getLifecycleListener().onFragmentResumed(this, getUserVisibleHint());
        delegate.onViewResume(getUserVisibleHint());
    }

    @Override
    public void onPause() {
        super.onPause();
        getLifecycleListener().onFragmentPaused(this);
        delegate.onViewPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getLifecycleListener().onFragmentDestroyed(this);
        delegate.onViewDestroy(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getLifecycleListener().onFragmentStateSaved(this, outState);
        delegate.onSaveState(outState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        delegate.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public P getPresenter() {
        return delegate.getPresenter();
    }
}
