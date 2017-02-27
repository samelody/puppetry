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

import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

import com.samelody.puppetry.app.PassiveFragment;
import com.samelody.puppetry.core.Contract.Presenter;

/**
 * @author Belin Wu
 */
public interface LifecycleListener extends ActivityLifecycleCallbacks {

    void onFragmentCreated(PassiveFragment fragment, Bundle state);

    void onFragmentStarted(PassiveFragment fragment);

    void onFragmentResumed(PassiveFragment fragment, boolean isVisibleToUser);

    void onFragmentPaused(PassiveFragment fragment);

    void onFragmentStopped(PassiveFragment fragment);

    void onFragmentStateSaved(PassiveFragment fragment, Bundle state);

    void onFragmentDestroyed(PassiveFragment fragment);

    void onPresenterCreated(Presenter presenter);

    void onPresenterStarted(Presenter presenter);

    void onPresenterResumed(Presenter presenter, boolean isVisibleToUser);

    void onPresenterPaused(Presenter presenter);

    void onPresenterStopped(Presenter presenter);

    void onPresenterDestroyed(Presenter presenter);
}
