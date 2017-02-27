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

import android.app.Activity;
import android.os.Bundle;

import com.samelody.puppetry.app.PassiveFragment;
import com.samelody.puppetry.core.Contract.Presenter;

/**
 * This adapter class provides empty implementations of the methods from {@link LifecycleListener}.
 * Any custom listener that cares only about a subset of the methods of this listener can
 * simply subclass this adapter class instead of implementing the interface directly.
 *
 * @author Belin Wu
 */
public class LifecycleListenerAdapter implements LifecycleListener {

    @Override
    public void onFragmentCreated(PassiveFragment fragment, Bundle state) {

    }

    @Override
    public void onFragmentStarted(PassiveFragment fragment) {

    }

    @Override
    public void onFragmentResumed(PassiveFragment fragment, boolean isVisibleToUser) {

    }

    @Override
    public void onFragmentPaused(PassiveFragment fragment) {

    }

    @Override
    public void onFragmentStopped(PassiveFragment fragment) {

    }

    @Override
    public void onFragmentStateSaved(PassiveFragment fragment, Bundle state) {

    }

    @Override
    public void onFragmentDestroyed(PassiveFragment fragment) {

    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    @Override
    public void onPresenterStarted(Presenter presenter) {

    }

    @Override
    public void onPresenterCreated(Presenter presenter) {

    }

    @Override
    public void onPresenterResumed(Presenter presenter, boolean isVisibleToUser) {

    }

    @Override
    public void onPresenterPaused(Presenter presenter) {

    }

    @Override
    public void onPresenterStopped(Presenter presenter) {

    }

    @Override
    public void onPresenterDestroyed(Presenter presenter) {

    }
}
