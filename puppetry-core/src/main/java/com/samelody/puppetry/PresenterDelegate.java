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

import android.os.Bundle;

import com.samelody.puppetry.Contract.PassiveView;
import com.samelody.puppetry.Contract.Presenter;

/**
 * Represents the presenter delegate.
 *
 * @author Belin Wu
 */
public interface PresenterDelegate<P extends Presenter> {

    void onViewCreate(Controller<P> controller, Bundle state);

    void onViewDestroy(PassiveView view);

    void attachView(Controller<P> controller, boolean visible);

    void detachView();

    void onSaveState(Bundle state);

    void onViewStart();

    void onViewResume(boolean visible);

    void onViewPause();

    void onViewStop();

    /**
     * Creates the presenter and presentation model.
     *
     * @return The created presenter.
     */
    P createPresenterAndModel();

    /**
     * Gets the presenter.
     *
     * @return The presenter
     */
    P getPresenter();
}
